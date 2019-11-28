package org.codingmatters.ui.cli.choices.test;

import org.codingmatters.ui.cli.choices.Choice;
import org.codingmatters.ui.cli.choices.Command;
import org.codingmatters.ui.cli.choices.Prompt;
import org.codingmatters.ui.cli.choices.Validation;

public class TestChoice implements Choice<String> {
    private final Prompt<String> prompt;
    private final Validation<String> validation;
    private final Command<String> command;

    public TestChoice(Prompt<String> prompt, Validation<String> validation, Command<String> command) {
        this.prompt = prompt;
        this.validation = validation;
        this.command = command;
    }

    @Override
    public Prompt<String> prompt() {
        return this.prompt;
    }

    @Override
    public Validation<String> validate(String value) {
        return this.validation;
    }

    @Override
    public Command<String> commandFor(String value) {
        return this.command;
    }
}
