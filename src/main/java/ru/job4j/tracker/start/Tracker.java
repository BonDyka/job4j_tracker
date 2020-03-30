package ru.job4j.tracker.start;

import org.springframework.stereotype.Component;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.store.Storage;

import java.util.List;
import java.util.Random;

/**
 * The class represent container for items.
 *
 * @author abondarev.
 * @since 22.07.2017.
 */
@Component
public class Tracker {

	/**
	 * The inner Storage.
	 */
	private Storage storage;

	public Tracker(Storage storage) {
		this.storage = storage;
	}

	/**
	 * The method adds specified as parameter item to the list of items.
	 * Return Item that was add.
	 *
	 * @param item is item for adding.
	 * @return Item that was add.
	 */
	public Item add(Item item) {
		item.setId(this.generateId());
		this.storage.add(item);
		return item;
	}

	/**
	 * The method get item as parameter, search in list of items na item with
	 * the same id and rewrite on its position specified item.
	 *
	 * @param item is item for replace.
	 */
	public void update(Item item) {
		this.storage.update(item);
	}

	/**
	 * The method get item as parameter and remove its from list of items if
	 * it exist.
	 *
	 * @param item is item for removing.
	 */
	public void delete(Item item) {
		this.storage.delete(item);
	}

	/**
	 * The method returns list of all items that was contain.
	 *
	 * @return List of all items.
	 */
	public List<Item> findAll() {
		return this.storage.findAll();
	}

	/**
	 * The method search Item by its name (specified as parameter) and return
	 * array of items if it found. Else return an empty array.
	 *
	 * @param name is name of find out item.
	 * @return list of items with specified name or empty array.
	 */
	public List<Item> findByName(String name) {
		return this.storage.findByName(name);
	}

	/**
	 * The method search Item by its id (specified as parameter) and return
	 * item if it found. Else return <i>null</i>.
	 *
	 * @param id is id of find out item.
	 * @return item with specified id or null.
	 */
	public Item findById(String id) {
		return this.storage.findById(id);
	}

	/**
	 * The method generates id for an item.
	 *
	 * @return a string that represent id.
	 */
	private String generateId() {
		Random rn = new Random();
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
						   'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
						   'u', 'v', 'w', 'x', 'y', 'z'};

		int fLetter = rn.nextInt(26);
		int sLetter = rn.nextInt(26);
		int code = rn.nextInt(10_000);

		return String.format("%s%s %s", alphabet[fLetter],
							 alphabet[sLetter], code);
	}
}