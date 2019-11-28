package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.ChoiceContext;
import org.codingmatters.ui.cli.choices.CommandExecutionFailure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

public class BufferedIOChoiceContext implements ChoiceContext<String> {

    private final BufferedReader reader;

    public BufferedIOChoiceContext(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String prompt(PromptType type, String message, Optional<String[]> options) {
        System.out.println(TextContextHelper.promptMessage(message, options));

        try {
            return this.reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("failed prompting", e);
        }
    }

    @Override
    public void alert(AlertType type, String message) {
        switch (type) {
            case ERROR:
                System.err.println(message);
            default:
                System.out.println(message);
        }
    }

    @Override
    public void alert(AlertType type, CommandExecutionFailure commandExecutionFailure) {
        PrintStream stream;
        switch (type) {
            case ERROR:
                stream = System.err;
            default:
                stream = System.out;
        }
        stream.println(commandExecutionFailure.getMessage());
        stream.println("wan't to see stack trace ? [y/N]");
        try {
            if("y".equalsIgnoreCase(reader.readLine())) {
                commandExecutionFailure.printStackTrace(stream);
            }
        } catch (IOException e) {
            throw new RuntimeException("failed prompting", e);
        }
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }
}
