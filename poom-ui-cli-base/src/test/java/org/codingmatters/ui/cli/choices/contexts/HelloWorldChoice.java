package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.*;

import java.util.Optional;

public class HelloWorldChoice implements Choice<String> {
    @Override
    public Prompt<String> prompt() {
        return () -> "what's your name ?";
    }

    @Override
    public Validation<String> validate(String value) {
        return Validation.valid();
    }

    @Override
    public Command<String> commandFor(String value) {
        return new Command<String>() {
            @Override
            public Optional<Choice<String>> executeWith(String value, ChoiceContext<String> context) throws CommandExecutionFailure {
                context.info("Hello " + value);
                return Optional.of(new Choice<String>() {
                    @Override
                    public Prompt<String> prompt() {
                        return new Prompt<String>() {
                            @Override
                            public String message() {
                                return "are you ok ?";
                            }

                            @Override
                            public Optional<String[]> options() {
                                return Optional.of(new String[] {"yes", "no"});
                            }
                        };
                    }

                    @Override
                    public Validation<String> validate(String value) {
                        return "yes".equalsIgnoreCase(value) || "no".equalsIgnoreCase(value) ? Validation.valid() : Validation.invalid("value must be yes or no");
                    }

                    @Override
                    public Command<String> commandFor(String value) {
                        return new Command<String>() {
                            @Override
                            public Optional<Choice<String>> executeWith(String value, ChoiceContext<String> context) throws CommandExecutionFailure {
                                if("no".equalsIgnoreCase(value)) {
                                    throw new CommandExecutionFailure("how come can you not be ok !!!");
                                } else {
                                    context.info("fine, have a nice day.");
                                }
                                return Optional.empty();
                            }
                        };
                    }
                });
            }
        };
    }
}
