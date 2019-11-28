package org.codingmatters.ui.cli.choices;

public class CommandExecutionFailure extends Exception {
    public CommandExecutionFailure(String s) {
        super(s);
    }

    public CommandExecutionFailure(String s, Throwable throwable) {
        super(s, throwable);
    }

    public <V> void alert(ChoiceContext<V> context) {
        context.alert(ChoiceContext.AlertType.ERROR, this);
    }
}
