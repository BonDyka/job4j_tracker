package ru.job4j.tracker.start;

import org.junit.Test;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.store.UserStore;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing the Tracker class.
 *
 * @author abondarev.
 * @since 22.07.2017.
 */
public class TrackerTest {

	/**
	 * The method tests add() method from the Tracker class.
	 */
	@Test
	public void whenAddItemThenItMustBeAdded() {
		Tracker tracker = new Tracker(new UserStore());
		Item expected = new Item("test1", "testDesc", 123L);
		tracker.add(expected);
		assertThat(tracker.findAll().get(0), is(expected));
	}

	/**
	 * The method tests update() method from the Tracker class.
	 */
	@Test
	public void whenUpdateItemThenItMustBeUpdated() {
		Tracker tracker = new Tracker(new UserStore());
		Item item = tracker.add(new Item("test1", "testDesc", 123L));
		Item expected = new Item("test2", "testDesc2", 256L);
		expected.setId(item.getId());
		tracker.update(expected);
		assertThat(tracker.findAll().get(0), is(expected));
	}


	/**
	 * The method tests delete() method from the Tracker class.
	 */
	@Test
	public void whenDeleteItemThenItMustBeDeleted() {
		Tracker tracker = new Tracker(new UserStore());
		Item item = tracker.add(new Item("test1", "testDesc", 123L));
		tracker.delete(item);
		int expectedLength = 0;
		assertThat(tracker.findAll().size(), is(expectedLength));
	}

	/**
	 * The method tests findByName() method from the Tracker class when item
	 * with its name is exist.
	 */
	@Test
	public void whenFindByNameItemThatExistThenItReturnListWithIt() {
		Tracker tracker = new Tracker(new UserStore());
		Item item = tracker.add(new Item("test1", "testDesc", 123L));
		List<Item> result = tracker.findByName(item.getName());
		assertThat(result, is(tracker.findAll()));
	}

	/**
	 * The method tests findByName() method from the Tracker class when item
	 * with its name is not exist.
	 */
	@Test
	public void whenFindByNameItemThatNotExistThenItReturnEmptyList() {
		Tracker tracker = new Tracker(new UserStore());
		List<Item> result = tracker.findByName("Test");
		assertThat(result, is(new ArrayList<>()));
	}

	/**
	 * The method tests findById() method from the Tracker class when item
	 * with its id is exist.
	 */
	@Test
	public void whenFindByIdWithExistIdThenReturnItem() {
		Tracker tracker = new Tracker(new UserStore());
		Item item = tracker.add(new Item("test1", "testDesc", 123L));
		Item result = tracker.findById(item.getId());
		assertThat(result, is(item));
	}

	/**
	 * The method tests findById() method from the Tracker class when item
	 * with its id is exist.
	 */
	@Test
	public void whenFindByIdWithNoExistIdThenReturnNull() {
		Tracker tracker = new Tracker(new UserStore());
		Item result = tracker.findById("ad 2341");
		assertThat(result, nullValue());
	}
}