package org.codingmatters.ui.cli.choices;

import java.util.Optional;

public interface Prompt<V> {

    String message();
    default ChoiceContext.PromptType type() {
        return ChoiceContext.PromptType.PLAIN;
    }
    default Optional<String[]> options() {
        return Optional.empty();
    }

    default V promptForValue(ChoiceContext<V> context) {
        return context.prompt(this.type(), this.message(), this.options());
    }
}
