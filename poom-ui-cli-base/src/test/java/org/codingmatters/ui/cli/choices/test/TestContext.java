package org.codingmatters.ui.cli.choices.test;

import org.codingmatters.ui.cli.choices.ChoiceContext;
import org.codingmatters.ui.cli.choices.CommandExecutionFailure;
import org.codingmatters.ui.cli.choices.screen.Screen;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class TestContext implements ChoiceContext<String> {

    private final AtomicReference<String> nextUserInput = new AtomicReference<>();
    private final AtomicReference<String> lastPrompt = new AtomicReference<>();
    private final AtomicReference<String> lastAlert = new AtomicReference<>();
    private final AtomicReference<String> lastInfo = new AtomicReference<>();
    private final AtomicReference<Screen> lastScreen = new AtomicReference<>();

    public TestContext nextUserInput(String value) {
        this.nextUserInput.set(value);
        return this;
    }

    public String lastPrompt() {
        return this.lastPrompt.get();
    }

    public String lastAlert() {
        return this.lastAlert.get();
    }

    @Override
    public String prompt(PromptType type, String message, Optional<String[]> options) {
        this.lastPrompt.set(String.format("%s :: %s", type, message));
        return this.nextUserInput.get();
    }

    @Override
    public void alert(AlertType type, String message) {
        this.lastAlert.set(String.format("%s :: %s", type, message));
    }

    @Override
    public void alert(AlertType type, CommandExecutionFailure commandExecutionFailure) {
        this.lastAlert.set(String.format("%s :: %s", type, commandExecutionFailure.getMessage()));
    }

    @Override
    public void info(String message) {
        this.lastInfo.set(message);
    }

    @Override
    public void screen(Screen screen) {
        this.lastScreen.set(screen);
    }

    public Screen lastScreen() {
        return lastScreen.get();
    }

    @Override
    public Screen.Builder currentScreen() {
        return this.lastScreen.get() == null ? Screen.builder() : Screen.from(this.lastScreen.get());
    }
}
