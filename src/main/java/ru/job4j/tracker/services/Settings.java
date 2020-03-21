package ru.job4j.tracker.services;

import java.util.Objects;
import java.util.Properties;

/**
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 05.01.2018.
 */
public class Settings {

    private static final Settings INST = new Settings();

    private final Properties prs = new Properties();

    private Settings() {
        try {
            this.prs.load(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("db.properties")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance() {
        return INST;
    }

    public String getValue(String key) {
        return this.prs.getProperty(key);
    }
}
