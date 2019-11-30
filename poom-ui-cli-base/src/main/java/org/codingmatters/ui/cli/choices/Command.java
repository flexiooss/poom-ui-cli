package org.codingmatters.ui.cli.choices;

import java.util.Optional;

@FunctionalInterface
public interface Command<V> {
    Optional<Choice<V>> executeWith(V value, ChoiceContext<V> context) throws CommandExecutionFailure;
}
