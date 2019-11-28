package org.codingmatters.ui.cli.choices;

import org.codingmatters.ui.cli.choices.test.TestChoice;
import org.codingmatters.ui.cli.choices.test.TestCommand;
import org.codingmatters.ui.cli.choices.test.TestContext;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ChoiceMakerTest {

    private TestContext context = new TestContext();
    private TestCommand command = new TestCommand();
    private TestChoice nextChoice = new TestChoice(() -> "yop", Validation.valid(), this.command);

    @Before
    public void setUp() throws Exception {
        command.nextChoice(this.nextChoice);
    }

    @Test
    public void givenChoiceIsValid__whenCommandProcessesWithoutException__thenNextChoiceReturned_andCommandCalledWithUserInput_andNoAlertRaised() throws Exception {
        TestChoice choice = new TestChoice(() -> "gimme your name", Validation.valid(), this.command);
        this.context.nextUserInput("after midnight");

        assertThat(
                new ChoiceMaker<String>(){}.makeAChoice(choice, this.context).get(),
                is(this.nextChoice)
        );
        assertThat(this.command.lastValue(), is("after midnight"));

        assertThat(this.context.lastPrompt(), is("PLAIN :: gimme your name"));
        assertThat(this.context.lastAlert(), is(nullValue()));
    }

    @Test
    public void whenChoiceIsInvalid__thenCurrentChoiceIsReturned_andCommandIsNotCalled_andInputValidationAlertRaised() throws Exception {
        TestChoice choice = new TestChoice(() -> "gimme your name", Validation.invalid("not quite"), this.command);
        this.context.nextUserInput("after midnight");

        assertThat(
                new ChoiceMaker<String>(){}.makeAChoice(choice, this.context).get(),
                is(choice)
        );
        assertThat(this.command.lastValue(), is(nullValue()));

        assertThat(this.context.lastPrompt(), is("PLAIN :: gimme your name"));
        assertThat(this.context.lastAlert(), is("INPUT_VALIDATION :: not quite"));
    }

    @Test
    public void givenChoiceIsValid__whenExceptionRaisedDuringCommand__thenCurrentChoiceIsReturned_andCommandIsCalled_andErrorAlertRaised() throws Exception {
        TestChoice choice = new TestChoice(() -> "gimme your name", Validation.valid(), this.command);
        this.context.nextUserInput("after midnight");
        this.command.nextFailure(new CommandExecutionFailure("Huston ??"));

        assertThat(
            new ChoiceMaker<String>(){}.makeAChoice(choice, this.context).get(),
            is(choice)
        );

        assertThat(this.context.lastPrompt(), is("PLAIN :: gimme your name"));
        assertThat(this.command.lastValue(), is("after midnight"));
        assertThat(this.context.lastAlert(), is("ERROR :: Huston ??"));
    }
}