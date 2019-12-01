package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.ChoiceContext;
import org.codingmatters.ui.cli.choices.CommandExecutionFailure;
import org.codingmatters.ui.cli.choices.screen.Screen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

public abstract class AbstractTextContext implements ChoiceContext<String> {
    private Screen screen = Screen.builder().build();
    private int screenWidth;
    private int screenHeight;

    public AbstractTextContext(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }

    protected abstract void readEmptyInput();
    protected abstract String readInput();

    protected abstract void printout(String format);

    @Override
    public void screen(Screen screen) {
        this.screen = screen;
        this.printout(new ScreenTextFormatter(this.screenWidth, this.screenHeight).format(screen));
    }

    @Override
    public Screen.Builder currentScreen() {
        return this.screen == null ? Screen.builder() : Screen.from(this.screen);
    }


    @Override
    public void alert(AlertType type, String message) {
        Screen previous = this.screen;

        this.screen(this.currentScreen().main(this.dialog(type.name(), message)).build());
        this.prompt(PromptType.PLAIN, "type enter to continue.", Optional.empty());

        this.screen(previous);
    }

    @Override
    public void alert(AlertType type, CommandExecutionFailure commandExecutionFailure) {
        Screen previous = this.screen;

        try {
            this.screen(this.currentScreen().main(this.dialog(type.name(), commandExecutionFailure.getMessage())).build());

            if("y".equalsIgnoreCase(this.prompt(PromptType.PLAIN, "wan't to see stack trace ? [y/N]", Optional.empty()))) {
                try(ByteArrayOutputStream out = new ByteArrayOutputStream(); PrintStream stream = new PrintStream(out)) {
                    commandExecutionFailure.printStackTrace(stream);
                    stream.flush();
                    stream.close();

                    this.screen(this.currentScreen().main(this.dialog(type.name(), out.toString())).build());

                    this.prompt(PromptType.PLAIN, "type enter to continue.", Optional.empty());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("failed prompting", e);
        }

        this.screen(previous);
    }

    @Override
    public void info(String message) {
        Screen previous = this.screen;

        this.screen(this.currentScreen().main(this.dialog("INFO", message)).build());
        this.prompt(PromptType.PLAIN, "type enter to continue.", Optional.empty());

        this.screen(previous);
    }

    private String dialog(String title, String content) {
        content = content.replaceAll("\t", "   ");
        StringBuilder result = new StringBuilder();

        result.append(ScreenTextFormatter.centered(this.dialogFilledLine("="), this.screenWidth)).append("\n");
        title = "=" + ScreenTextFormatter.centered(title, this.dialogWidth()) + "=";
        result.append(ScreenTextFormatter.centered(title, this.screenWidth)).append("\n");
        result.append(ScreenTextFormatter.centered(this.dialogFilledLine("="), this.screenWidth)).append("\n");

        result.append(ScreenTextFormatter.centered(this.dialogEmptyLine(), this.screenWidth)).append("\n");
        int from = 0;
        for(int index = content.indexOf('\n', from) ; index != -1; index = content.indexOf('\n', from)) {
            String line = content.substring(from, index);
            result.append(ScreenTextFormatter.centered(this.dialogBodyLine(line), this.screenWidth)).append("\n");
            from = index + 1;
        }
        result.append(ScreenTextFormatter.centered(this.dialogBodyLine(content.substring(from)), this.screenWidth)).append("\n");

        result.append(ScreenTextFormatter.centered(this.dialogEmptyLine(), this.screenWidth)).append("\n");
        result.append(ScreenTextFormatter.centered(this.dialogFilledLine("="), this.screenWidth)).append("\n");

        return result.toString();
    }

    private String dialogFilledLine(String fill) {
        String result = "";
        while(result.length() < this.dialogWidth()) {
            result += fill;
        }
        return result;
    }

    private String dialogEmptyLine() {
        String result = "=";
        while(result.length() < this.dialogWidth() - 1) {
            result += " ";
        }
        return result + "=";
    }

    private String dialogBodyLine(String line) {
        for (int i = line.length(); i < this.dialogWidth() - 4; i++) {
            line += " ";
        }
        line = "= " + line + " =";
        return line;
    }

    private int dialogWidth() {
        return 2 * this.screenWidth / 3;
    }


}
