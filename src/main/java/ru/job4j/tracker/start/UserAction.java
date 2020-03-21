package ru.job4j.tracker.start;

/**
 * The main interface for all actions.
 *
 * @author abondarev.
 * @since 24.07.2017.
 */
public interface UserAction {

	/**
	 * The method return key of action.
	 *
	 * @return the key of the action.
	 */
	int key();

	/**
	 * The method executes an action.
	 *
	 * @param input an input instance.
	 * @param tracker an tracker instance.
	 */
	void execute(Input input, Tracker tracker);

	/**
	 * The method describes the action.
	 *
	 * @return a string that describes the action.
	 */
	String info();
}