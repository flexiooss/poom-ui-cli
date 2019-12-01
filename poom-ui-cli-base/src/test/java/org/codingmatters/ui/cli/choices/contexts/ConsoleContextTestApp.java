package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.screen.Screen;

public class ConsoleContextTestApp {
    /**
     * To run from maven:
     * export COLUMNS && export LINES && mvn exec:java -Dexec.mainClass="org.codingmatters.ui.cli.choices.contexts.ConsoleContextTestApp" -Dexec.classpathScope="test"
     * @param args
     */
    public static void main(String[] args) {
        ConsoleContext context = new ConsoleContext(TextCli.screenWidth(), TextCli.screenHeight());
        context.screen(Screen.builder()
                .header("Hello Woold Choice App !")
                .main("Welcome to this trumendous Hello WorldApp !\n" +
                        "Get ready to be bluffed out !!")
                .footer("Let's go Marco !")
                .build());
        context.run(new HelloWorldChoice());
    }
}
