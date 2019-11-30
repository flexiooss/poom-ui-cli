package org.codingmatters.ui.cli.choices.contexts;

import org.codingmatters.ui.cli.choices.screen.Screen;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ScreenTextFormatterTest {
    @Test
    public void given__whenHeaderAndFooter__then() throws Exception {
        assertThat(
                new ScreenTextFormatter(100).format(Screen.builder()
                        .header("This is my header.")
                        .main("Main content.\nMultiple lines.\nVery nice.")
                        .footer("The footer.")
                        .build()),
                is(
                        "----------------------------------------------------------------------------------------------------\n" +
                        "-                                        This is my header.                                        -\n" +
                        "----------------------------------------------------------------------------------------------------\n" +
                        "\n" +
                        "Main content.\nMultiple lines.\nVery nice." +
                        "\n" +
                        "----------------------------------------------------------------------------------------------------\n" +
                        "-                                           The footer.                                            -\n" +
                        "----------------------------------------------------------------------------------------------------"
                ));
    }

    @Test
    public void given__whenNoHeader__then() throws Exception {
        assertThat(
                new ScreenTextFormatter(100).format(Screen.builder()
                        .header(null)
                        .main("Main content.\nMultiple lines.\nVery nice.")
                        .footer("The footer.")
                        .build()),
                is(
                        "----------------------------------------------------------------------------------------------------\n" +
                        "\n" +
                        "Main content.\nMultiple lines.\nVery nice." +
                        "\n" +
                        "----------------------------------------------------------------------------------------------------\n" +
                        "-                                           The footer.                                            -\n" +
                        "----------------------------------------------------------------------------------------------------"
                ));
    }

    @Test
    public void given__whenNoFooter__then() throws Exception {
        assertThat(
                new ScreenTextFormatter(100).format(Screen.builder()
                        .header("This is my header.")
                        .main("Main content.\nMultiple lines.\nVery nice.")
                        .footer(null)
                        .build()),
                is(
                        "----------------------------------------------------------------------------------------------------\n" +
                                "-                                        This is my header.                                        -\n" +
                                "----------------------------------------------------------------------------------------------------\n" +
                                "\n" +
                                "Main content.\nMultiple lines.\nVery nice." +
                                "\n" +
                                "----------------------------------------------------------------------------------------------------"
                ));
    }

    @Test
    public void given__whenNoMainNoHeaderNoFooter__then() throws Exception {
        assertThat(
                new ScreenTextFormatter(100).format(Screen.builder()
                        .header(null)
                        .main(null)
                        .footer(null)
                        .build()),
                is(
                        "----------------------------------------------------------------------------------------------------\n" +
                                "\n" +
                                "\n" +
                                "----------------------------------------------------------------------------------------------------"
                ));
    }


    @Test
    public void given__whenHeaderLongerThanLine__then() throws Exception {
        assertThat(
                new ScreenTextFormatter(100).format(Screen.builder()
                        .header("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                        .main(null)
                        .footer(null)
                        .build()),
                is(
                        "----------------------------------------------------------------------------------------------------\n" +
                                "-Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" +
                                "----------------------------------------------------------------------------------------------------\n" +
                                "\n" +
                                "\n" +
                                "----------------------------------------------------------------------------------------------------"
                ));
    }

    @Test
    public void given__whenFooterLongerThanLine__then() throws Exception {
        assertThat(
                new ScreenTextFormatter(100).format(Screen.builder()
                        .header(null)
                        .main(null)
                        .footer("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                        .build()),
                is(
                        "----------------------------------------------------------------------------------------------------\n" +
                                "\n" +
                                "\n" +
                                "----------------------------------------------------------------------------------------------------\n" +
                                "-Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" +
                                "----------------------------------------------------------------------------------------------------"
                ));
    }

}