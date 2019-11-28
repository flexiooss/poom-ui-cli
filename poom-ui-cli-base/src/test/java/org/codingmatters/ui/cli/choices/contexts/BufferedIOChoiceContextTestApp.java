package org.codingmatters.ui.cli.choices.contexts;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedIOChoiceContextTestApp {

    /**
     * To run from maven :
     * mvn exec:java -Dexec.mainClass="org.codingmatters.ui.cli.choices.contexts.BufferedIOChoiceContextTestApp" -Dexec.classpathScope="test"
     * @param args
     */
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedIOChoiceContext context = new BufferedIOChoiceContext(reader);
            context.run(new HelloWorldChoice());
        } catch (IOException e) {
            throw new RuntimeException("error running choices...", e);
        }
    }
}