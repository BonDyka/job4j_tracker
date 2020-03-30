package ru.job4j.tracker.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.job4j.tracker.start.Input;
import ru.job4j.tracker.start.ValidateInput;

@Configuration
public class TrackerConfig {

    @Bean
    public Input getInput() {
        return new ValidateInput();
    }
}
