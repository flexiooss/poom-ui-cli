package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.screen.Screen;

public class ScreenTextFormatter {
    private final int width;

    public ScreenTextFormatter(int width) {
        this.width = width;
    }

    public String format(Screen screen) {
        StringBuilder result = new StringBuilder();

        this.appendHR(result).append("\n");

        if(screen.header() != null) {
            result.append(this.centered(screen.header())).append("\n");
            this.appendHR(result).append("\n");
        }

        result.append("\n");
        if(screen.main() != null) {
            result.append(screen.main());
        }
        result.append("\n");

        if(screen.footer() != null) {
            this.appendHR(result).append("\n");
            result.append(this.centered(screen.footer())).append("\n");
        }

        this.appendHR(result);
        return result.toString();
    }

    private String centered(String header) {
        int zoneLength = this.width - 2;
        int before = (zoneLength - header.length()) / 2;
        int after = zoneLength - before - header.length();

        StringBuilder result = new StringBuilder().append("-");
        for (int i = 0; i < before; i++) {
            result.append(" ");
        }

        result.append(header);

        if(after > 0) {
            for (int i = 0; i < after; i++) {
                result.append(" ");
            }
            result.append("-");
        }
        return result.toString();
    }

    private StringBuilder appendHR(StringBuilder result) {
        for (int i = 0; i < this.width; i++) {
            result.append("-");
        }
        return result;
    }
}
