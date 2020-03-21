package ru.job4j.tracker.start;

/**
 * The class represent template for actions.
 *
 * @author abondarev.
 * @since 27.07.2017.
 */
public abstract class BaseAction implements UserAction {

	/**
	 * The variable holds key of the action.
	 */
	private int key;

	/**
	 * The variable contains name of the action.
	 */
	private String name;

	/**
	 * The constructor takes as parameter key and name of action.
	 *
	 * @param key of action.
	 * @param name of action.
	 */
	public BaseAction(int key, String name) {
		this.key = key;
		this.name = name;
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @return the key of the action.
	 */
	public int key() {
		return this.key;
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @return a string that describes the action.
	 */
	public String info() {
		return this.name;
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @param input an input instance.
	 * @param tracker an tracker instance.
	 */
	public abstract void execute(Input input, Tracker tracker);
}