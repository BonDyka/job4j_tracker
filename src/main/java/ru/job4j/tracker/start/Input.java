package ru.job4j.tracker.start;

/**
 * The interface represent main contract for all input for the application.
 *
 * @author abondarev.
 * @since 22.07.2017.
 */
public interface Input {

	/**
	 * The method outputs question, specified as parameter, for user and return
	 * a string that represent answer.
	 *
	 * @param question is string for output.
	 * @return a string that represent the input data.
	 */
	String ask(String question);

	/**
	 * The method for asking an integer in range from user.
	 *
	 * @param question is string for output for user.
	 * @param from is start limit of range.
	 * @param to is end limit of range.
	 * @return an integer from user input.
	 */
	int ask(String question, int from, int to);
}