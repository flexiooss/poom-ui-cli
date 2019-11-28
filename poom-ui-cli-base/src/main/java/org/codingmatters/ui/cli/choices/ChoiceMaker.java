package org.codingmatters.ui.cli.choices;


import java.util.Optional;

public interface ChoiceMaker<V> {

    default Optional<Choice<V>> makeAChoice(Choice<V> choice, ChoiceContext<V> context) {
        V value = choice.prompt().promptForValue(context);

        Validation<V> validation = choice.validate(value);
        if(! validation.isValid()) {
            validation.alert(context);
            return Optional.of(choice);
        }

        Command<V> command = choice.commandFor(value);
        try {
            return command.executeWith(value, context);
        } catch (CommandExecutionFailure commandExecutionFailure) {
            commandExecutionFailure.alert(context);
            return Optional.of(choice);
        }
    }
}
