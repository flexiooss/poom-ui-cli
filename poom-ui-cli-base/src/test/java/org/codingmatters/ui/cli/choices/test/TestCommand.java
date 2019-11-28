package org.codingmatters.ui.cli.choices.test;

import org.codingmatters.ui.cli.choices.Choice;
import org.codingmatters.ui.cli.choices.ChoiceContext;
import org.codingmatters.ui.cli.choices.Command;
import org.codingmatters.ui.cli.choices.CommandExecutionFailure;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class TestCommand implements Command<String> {

    private final AtomicReference<String> lastValue = new AtomicReference<>();
    private final AtomicReference<Choice<String>> nextChoice = new AtomicReference<>();
    private final AtomicReference<CommandExecutionFailure> nextFailure = new AtomicReference<>();

    public String lastValue() {
        return this.lastValue.get();
    }

    public TestCommand nextChoice(Choice<String> choice) {
        this.nextChoice.set(choice);
        return this;
    }

    public TestCommand nextFailure(CommandExecutionFailure commandExecutionFailure) {
        this.nextFailure.set(commandExecutionFailure);
        return this;
    }

    @Override
    public Optional<Choice<String>> executeWith(String value, ChoiceContext<String> context) throws CommandExecutionFailure {
        this.lastValue.set(value);
        if(this.nextFailure.get() != null) {
            throw this.nextFailure.get();
        }
        return Optional.ofNullable(this.nextChoice.get());
    }
}
