package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.ChoiceContext;
import org.codingmatters.ui.cli.choices.CommandExecutionFailure;
import org.codingmatters.ui.cli.choices.screen.Screen;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

public class BufferedIOChoiceContext extends AbstractTextContext {

    private final BufferedReader reader;

    public BufferedIOChoiceContext(BufferedReader reader, int width, int height) {
        super(width, height);
        this.reader = reader;
    }

    protected void readEmptyInput() {
        try {
            this.reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("failed reading input", e);
        }
    }

    @Override
    protected String readInput() {
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("failed reading input", e);
        }
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
    protected void printout(String format) {
        System.out.println(format);
    }

}
