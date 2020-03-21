package ru.job4j.tracker.start;

/**
 * The class represents exception when user selected item out of range of
 * the menu.
 *
 * @author abondarev.
 * @since 26.07.2017
 */
public class MenuOutException extends RuntimeException {

	/**
	 * The constructor.
	 *
	 * @param msg is a message exception about.
	 */
	public MenuOutException(String msg) {
		super(msg);
	}
}