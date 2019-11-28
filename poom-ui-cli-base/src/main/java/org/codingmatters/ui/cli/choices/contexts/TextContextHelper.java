package org.codingmatters.ui.cli.choices.contexts;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextContextHelper {
    static public String promptMessage(String message, Optional<String[]> options) {
        String formatted = message;
        if(options.isPresent()) {
            formatted += " [" + Arrays.stream(options.get()).collect(Collectors.joining(", ")) + "]";
        }
        return formatted;
    }
}
