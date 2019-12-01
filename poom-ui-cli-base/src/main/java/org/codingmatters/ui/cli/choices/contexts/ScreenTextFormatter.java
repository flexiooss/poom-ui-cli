package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.screen.Screen;

public class ScreenTextFormatter {

    static public String centered(String header, int width) {
        int zoneLength = width - 2;
        int before = (zoneLength - header.length()) / 2;
        int after = zoneLength - before - header.length();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < before; i++) {
            result.append(" ");
        }

        result.append(header);

        if(after > 0) {
            for (int i = 0; i < after; i++) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    private final int width;
    private final int height;

    public ScreenTextFormatter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String format(Screen screen) {
        StringBuilder result = new StringBuilder();

        this.wipeScreen(result);

        this.appendHR(result).append("\n");

        if(screen.header() != null) {
            result.append("-").append(this.centered(screen.header(), this.width)).append("-").append("\n");
            this.appendHR(result).append("\n");
        }

        result.append("\n");
        this.appendMain(screen, result);
        result.append("\n");

        if(screen.footer() != null) {
            this.appendHR(result).append("\n");
            result.append("-").append(this.centered(screen.footer(), this.width)).append("-").append("\n");
        }

        this.appendHR(result);
        return result.toString();
    }

    private void appendMain(Screen screen, StringBuilder result) {
        int lines = 0;
        if(screen.main() != null) {
            int from = 0;

            for(int index = screen.main().indexOf('\n', from) ; index != -1 ; index = screen.main().indexOf('\n', from)) {
                lines++;
                from = index + 1;
            }

            result.append(screen.main());
        }
        for (int i = 0; i < this.mainSize(screen) - lines; i++) {
            result.append("\n");
        }
    }

    private StringBuilder appendHR(StringBuilder result) {
        for (int i = 0; i < this.width; i++) {
            result.append("-");
        }
        return result;
    }

    private void wipeScreen(StringBuilder result) {
        for (int i = 0; i < this.height * 2; i++) {
            result.append("\n");
        }
    }

    private int mainSize(Screen screen) {
        return this.height - this.mainPadding() - this.headerHeight(screen) - this.footerHeight(screen) - this.promptSize();
    }

    private int mainPadding() {
        return 2;
    }

    private int headerHeight(Screen screen) {
        return screen.header() == null ? 1 : 3;
    }

    private int footerHeight(Screen screen) {
        return screen.footer() == null ? 1 : 3;
    }

    private int promptSize() {
        return 1;
    }
}
