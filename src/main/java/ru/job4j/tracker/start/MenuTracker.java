package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents menu.
 *
 * @author abondarev.
 * @since 24.07.2017.
 */
public class MenuTracker {

	/**
	 * The variable contains value of menu size.
	 */
	public static final int SIZE = 7;

	/**
	 * The variable contains number of first item menu.
	 */
	public static final int FIRST = 1;

	/**
	 * The list of all action in menu.
	 */
	private List<UserAction> actions = new ArrayList<>();

	/**
	 * The input system instance.
	 */
	private Input input;

	/**
	 * The tracker instance.
	 */
	private Tracker tracker;

	/**
	 * The constructor.
	 *
	 * @param input is an input system instance.
	 * @param tracker is the tracker instance.
	 */
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 * The method fill actions of the menu.
	 */
	public void fillActions() {
		this.actions.add(this.new AddAction(1, "Add an item."));
		this.actions.add(new MenuTracker.ShowAllAction(2, "Show all items."));
		this.actions.add(this.new EditAction(3, "Edit an item."));
		this.actions.add(this.new DeleteAction(4, "Delete an action."));
		this.actions.add(this.new FindByIdAction(5, "Find the item by Id."));
		this.actions.add(new FindByNameAction(6, "Find items by name."));
		this.actions.add(this.new ExitAction(7, "Exit."));
	}

	/**
	 * The method selects an menu action for execution.
	 *
	 * @param selected is an action key.
	 */
	public void select(int selected) {
		for (UserAction action : this.actions) {
			if (action != null && selected == action.key()) {
				action.execute(this.input, this.tracker);
				break;
			}
		}
	}

	/**
	 * The method shows te menu items for users.
	 */
	public void show() {
		String lineSeparator = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		builder.append("******************************").append(lineSeparator)
			   .append("*            MENU            *").append(lineSeparator)
			   .append("******************************").append(lineSeparator);
		for (UserAction action : this.actions) {
			if (action != null) {
				builder.append(String.format(
								"%s. %s", action.key(), action.info()))
					   .append(lineSeparator);
			}
		}
		builder.append("******************************").append(lineSeparator);
		System.out.println(builder);
	}

	/**
	 * The class implements UserAction for addition an item.
	 */
	private class AddAction extends BaseAction {

		/**
		 * The constructor takes as parameter key and name of action.
		 *
		 * @param key of action.
		 * @param name of action.
		 */
		AddAction(int key, String name) {
			super(key, name);
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			Item item = tracker.add(
								new Item(input.ask("Please enter name of item: "),
										 input.ask("Please enter description of item: "),
										 System.currentTimeMillis()
								));

			System.out.println(String.format("Id of your item: %s", item.getId()));
		}
	}

	/**
	 * The class implements UserAction for show all items.
	 */
	private class ShowAllAction extends BaseAction {

		/**
		 * The constructor takes as parameter key and name of action.
		 *
		 * @param key of action.
		 * @param name of action.
		 */
		ShowAllAction(int key, String name) {
			super(key, name);
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			List<Item> items = tracker.findAll();
			if (items.size() != 0) {
				for (Item item : items) {
					System.out.println(item);
				}
			} else {
				System.out.println("List is empty");
			}
			System.out.println("Done.");
		}
	}

	/**
	 * The class implements UserAction for updating an item.
	 */
	private class EditAction extends BaseAction {

		/**
		 * The constructor takes as parameter key and name of action.
		 *
		 * @param key of action.
		 * @param name of action.
		 */
		EditAction(int key, String name) {
			super(key, name);
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please enter yours item Id: ");
			Item existed = tracker.findById(id);
			if (existed != null) {
				Item updated = new Item(input.ask("Please enter name of item: "),
										input.ask("Please enter description of item: "),
										existed.getCreate());
				updated.setId(existed.getId());
				tracker.update(updated);
				System.out.println("Operation complete.");
			} else {
				System.out.println("Operation failure! Invalid id!");
			}
		}
	}

	/**
	 * The class implements UserAction for removing an item.
	 */
	private class DeleteAction extends BaseAction {

		/**
		 * The constructor takes as parameter key and name of action.
		 *
		 * @param key of action.
		 * @param name of action.
		 */
		DeleteAction(int key, String name) {
			super(key, name);
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please enter yours item Id: ");
			Item item = tracker.findById(id);
			if (item != null) {
				tracker.delete(item);
			}
			System.out.println("Operation complete.");
		}
	}

	/**
	 * The class implements UserAction for searching the item by id.
	 */
	private class FindByIdAction extends BaseAction {

		/**
		 * The constructor takes as parameter key and name of action.
		 *
		 * @param key of action.
		 * @param name of action.
		 */
		FindByIdAction(int key, String name) {
			super(key, name);
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please enter items Id: ");
			Item item = tracker.findById(id);
			if (item != null) {
				System.out.println(item);
			} else {
				System.out.println("Not found");
			}
		}
	}

	/**
	 * The class implements UserAction for exit.
	 */
	private class ExitAction extends BaseAction {

		/**
		 * The constructor takes as parameter key and name of action.
		 *
		 * @param key of action.
		 * @param name of action.
		 */
		ExitAction(int key, String name) {
			super(key, name);
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			System.out.println("Good bye.");
		}
	}
}

/**
 * The class implements UserAction for searching items by name.
 */
class FindByNameAction extends BaseAction {

	/**
	 * The constructor takes as parameter key and name of action.
	 *
	 * @param key of action.
	 * @param name of action.
	 */
	FindByNameAction(int key, String name) {
		super(key, name);
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @param input an input instance.
	 * @param tracker an tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		String name = input.ask("Please enter name of item: ");
		List<Item> items = tracker.findByName(name);
		if (items.size() != 0) {
			for (Item item : items) {
				System.out.println(item);
			}
		} else {
			System.out.println("Not found");
		}
		System.out.println("Done.");
	}
}