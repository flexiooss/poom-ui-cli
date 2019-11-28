package org.codingmatters.ui.cli.choices;

public interface Choice<V> {
    Prompt<V> prompt();
    Validation<V> validate(V value);
    Command<V> commandFor(V value);
}
