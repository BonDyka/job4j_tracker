package ru.job4j.tracker.start;

import java.util.Scanner;

/**
 * The class created for reading user input from console.
 *
 * @author abondarev.
 * @since 22.07.2017.
 */
public class ConsoleInput implements Input {

	/**
	 * The instance of input from standard java tools.
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	 * <@inheritedDoc>.
	 *
	 * @param question is a string for output for user.
	 * @return a string that represent input to console from user.
	 */
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @param question is string for output for user.
	 * @param from is start limit of range.
	 * @param to is end limit of range.
	 * @return an integer from user input.
	 */
	public int ask(String question, int from, int to) {
		int value = Integer.parseInt(this.ask(question));
		if (value >= from && value <= to) {
			return value;
		} else {
			throw new MenuOutException("Out of range.");
		}
	}
}