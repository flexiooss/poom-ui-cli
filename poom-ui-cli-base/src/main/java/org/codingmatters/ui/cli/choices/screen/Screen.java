package org.codingmatters.ui.cli.choices.screen;

public class Screen {

    static public Builder builder() {
        return new Builder();
    }

    static public Builder from(Screen screen) {
        return new Builder()
                .header(screen.header())
                .main(screen.main())
                .footer(screen.footer());
    }

    static public class Builder {
        private String header;
        private String main;
        private String footer;

        public Builder header(String header) {
            this.header = header;
            return this;
        }

        public Builder main(String main) {
            this.main = main;
            return this;
        }

        public Builder footer(String footer) {
            this.footer = footer;
            return this;
        }

        public Screen build() {
            return new Screen(this.header, this.main, this.footer);
        }

    }

    private final String header;
    private final String main;
    private final String footer;

    private Screen(String header, String main, String footer) {
        this.header = header;
        this.main = main;
        this.footer = footer;
    }

    public String header() {
        return header;
    }

    public String main() {
        return main;
    }

    public String footer() {
        return footer;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "header='" + header + '\'' +
                ", main='" + main + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }
}
