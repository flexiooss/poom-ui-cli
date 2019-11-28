package org.codingmatters.ui.cli.choices.contexts;

public class ConsoleContextTestApp {
    /**
     * To run from maven:
     * mvn exec:java -Dexec.mainClass="org.codingmatters.ui.cli.choices.contexts.ConsoleContextTestApp" -Dexec.classpathScope="test"
     * @param args
     */
    public static void main(String[] args) {
        ConsoleContext context = new ConsoleContext();
        context.run(new HelloWorldChoice());
    }
}
