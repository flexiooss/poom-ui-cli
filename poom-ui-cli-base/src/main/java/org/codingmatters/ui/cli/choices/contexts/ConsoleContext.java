package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.ChoiceContext;
import org.codingmatters.ui.cli.choices.CommandExecutionFailure;

import java.io.Console;
import java.util.Optional;

public class ConsoleContext implements ChoiceContext<String> {
    private final Console console;

    public ConsoleContext() {
        this.console = System.console();
    }

    @Override
    public String prompt(PromptType type, String message, Optional<String[]> options) {
        this.console.printf(TextContextHelper.promptMessage(message, options) + "\n");
        switch (type) {
            case PASSWORD:
                return new String(this.console.readPassword());
            default:
                return this.console.readLine();
        }
    }

    @Override
    public void alert(AlertType type, String message) {
        String prefix;
        switch (type) {
            case ERROR:
                prefix = "ERROR : ";
                break;
            case INPUT_VALIDATION:
                prefix = "Invalid input : ";
                break;
            default:
                prefix = "";
        }
        this.console.printf(prefix + message + "\n");
    }

    @Override
    public void alert(AlertType type, CommandExecutionFailure commandExecutionFailure) {
        String prefix;
        switch (type) {
            case ERROR:
                prefix = "ERROR : ";
                break;
            case INPUT_VALIDATION:
            default:
                prefix = "";
        }
        this.console.printf(prefix + commandExecutionFailure.getMessage() + "\n");

        this.console.printf(prefix + "wan't to see stack trace ? [y/N]" + "\n");
        if("y".equalsIgnoreCase(this.console.readLine())) {
            commandExecutionFailure.printStackTrace(this.console.writer());
        }

    }

    @Override
    public void info(String message) {
        this.console.printf(message + "\n");
    }
}
