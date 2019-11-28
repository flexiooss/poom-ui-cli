package org.codingmatters.ui.cli.choices;

import java.util.Optional;

public interface ChoiceContext<V> {
    enum PromptType {
        PLAIN,
        PASSWORD
    }
    V prompt(PromptType type, String message, Optional<String[]> options);

    enum AlertType {
        INPUT_VALIDATION,
        ERROR
    }
    void alert(AlertType type, String message);
    void alert(AlertType type, CommandExecutionFailure commandExecutionFailure);

    void info(String message);

    default void run(Choice<V> start) {
        ChoiceMaker<V> choiceMaker = new ChoiceMaker<V>() {};
        Optional<Choice<V>> next = Optional.of(start);
        do {
            next = choiceMaker.makeAChoice(next.get(), this);
        } while (next.isPresent());
    }
}
