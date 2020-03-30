package ru.job4j.tracker.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.start.Tracker;
import ru.job4j.tracker.store.DBStorage;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TrackerConfig.class);
        context.register(DBStorage.class);
        context.register(Tracker.class);
        context.register(StartUI.class);

        StartUI ui = context.getBean(StartUI.class);
        ui.init();
    }
}
