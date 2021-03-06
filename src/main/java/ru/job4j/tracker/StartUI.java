package ru.job4j.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.job4j.tracker.di.Context;
import ru.job4j.tracker.start.Input;
import ru.job4j.tracker.start.MenuTracker;
import ru.job4j.tracker.start.Tracker;
import ru.job4j.tracker.start.ValidateInput;
import ru.job4j.tracker.store.DBStorage;

/**
 *
 */
@Component
@Scope("prototype")
public class StartUI {

	/**
	 * The variable contain value for exit from app.
	 */
	private static final int EXIT = 7;

	/**
	 * The instance of input system.
	 */
	@Autowired
	private Input input;

	/**
	 * The instance of Tracker class.
	 */
	@Autowired
	private Tracker tracker;

//	/**
//	 * The constructor.
//	 *
//	 * @param input is an instance of input system.
//	 * @param tracker is container under that execute action.
//	 */
//	public StartUI(Input input, Tracker tracker) {
//		this.input = input;
//		this.tracker = tracker;
//	}

	/**
	 * The enter point.
	 *
	 * @param args list of arguments.
	 */
	public static void main(String[] args) {
		Context context = new Context();
		context.reg(ValidateInput.class);
		context.reg(DBStorage.class);
		context.reg(Tracker.class);
		context.reg(StartUI.class);

		StartUI ui = context.get(StartUI.class);

		ui.init();
	}

	/**
	 * The methods initializes main loop of application.
	 */
	public void init() {
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();
		int key = 0;
		while (key != EXIT) {
			menu.show();
			key = this.input.ask("Select: ", MenuTracker.FIRST,	MenuTracker.SIZE);
			menu.select(key);
		}
	}
}