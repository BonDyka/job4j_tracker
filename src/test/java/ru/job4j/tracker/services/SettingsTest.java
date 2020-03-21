package ru.job4j.tracker.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class for {@link Settings}.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 05.01.2018.
 */
public class SettingsTest {

    @Test
    public void whenLoadFileThenGotIt() {
        Settings settings = Settings.getInstance();

        String expected = "postgres";
        String result = settings.getValue("jdbc.username");

        assertThat(result, is(expected));
    }

}