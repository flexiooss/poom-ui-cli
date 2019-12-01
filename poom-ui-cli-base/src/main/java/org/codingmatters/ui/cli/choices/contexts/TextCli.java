package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.Choice;
import org.codingmatters.ui.cli.choices.ChoiceContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextCli {
    static public void run(Choice<String> start) {
        if(System.console() == null) {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                BufferedIOChoiceContext context = new BufferedIOChoiceContext(reader, screenWidth(), screenHeight());
                context.run(start);
            } catch (IOException e) {
                throw new RuntimeException("error running choices...", e);
            }
        } else {
            ConsoleContext context = new ConsoleContext(screenWidth(), screenHeight());
            context.run(start);
        }
    }

    static public int screenWidth() {
        String val = System.getenv("COLUMNS");
        if(val != null) {
            return Integer.parseInt(val);
        } else {
            return 100;
        }
    }

    static public int screenHeight() {
        String val = System.getenv("LINES");
        if(val != null) {
            return Integer.parseInt(val);
        } else {
            return 40;
        }
    }
}
