package org.codingmatters.ui.cli.choices;

public interface Validation<V> {
    static <V> Validation<V> valid() {
        return new Validation<V>() {};
    }
    static <V> Validation<V> invalid(String message) {
        return new Validation<V>() {
            @Override
            public boolean isValid() {
                return false;
            }

            @Override
            public String message() {
                return message;
            }
        };
    }

    default boolean isValid() {
        return true;
    }
    default String message() {
        return "";
    }

    default void alert(ChoiceContext<V> context) {
        context.alert(ChoiceContext.AlertType.INPUT_VALIDATION, this.message());
    }
}
