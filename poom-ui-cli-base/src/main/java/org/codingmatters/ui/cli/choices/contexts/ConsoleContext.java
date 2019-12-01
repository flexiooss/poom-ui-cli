package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.ChoiceContext;
import org.codingmatters.ui.cli.choices.CommandExecutionFailure;
import org.codingmatters.ui.cli.choices.screen.Screen;

import java.io.Console;
import java.util.Optional;

public class ConsoleContext extends AbstractTextContext {
    private final Console console;

    public ConsoleContext(int width, int height) {
        super(width, height);
        this.console = System.console();
    }

    @Override
    protected void readEmptyInput() {
        this.console.readLine();
    }

    @Override
    protected String readInput() {
        return this.console.readLine();
    }

    @Override
    public String prompt(PromptType type, String message, Optional<String[]> options) {
        this.console.printf(TextContextHelper.promptMessage(message, options) + " ");
        switch (type) {
            case PASSWORD:
                return new String(this.console.readPassword());
            default:
                return this.console.readLine();
        }
    }

    @Override
    protected void printout(String format) {
        this.console.printf(format);
    }
}
