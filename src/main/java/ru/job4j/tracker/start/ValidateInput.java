package ru.job4j.tracker.start;

/**
 * The class represents valid input from user with preparing exceptions.
 *
 * @author abondarev.
 * @since 26.07.2017.
 */
public class ValidateInput extends ConsoleInput {

	/**
	 * <@inheritedDoc>.
	 *
	 * @param question is string for output for user.
	 * @param from is start limit of range.
	 * @param to is end limit of range.
	 * @return an integer from user input.
	 */
	public int ask(String question, int from, int to) {
		int value = -1;
		boolean invalid = true;
		do {
			try {
				value = super.ask(question, from, to);
				invalid = false;
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter valid data.");
			} catch (MenuOutException moe) {
				System.out.println("Please select item from menu.");
			}
		} while (invalid);
		return value;
	}
}