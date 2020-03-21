package ru.job4j.tracker.store;

import ru.job4j.tracker.models.Item;

import java.util.List;

/**
 * General interface for CRUD work with items.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 05.01.2018.
 */
public interface Storage {

    /**
     * Adds item specified as parameter to the storage.
     *
     * @param item for adding
     */
    void add(Item item);

    /**
     * Update item in storage found bi id of item specified as parameter.
     *
     * @param item the item with updated data.
     */
    void update(Item item);

    /**
     * Delete item from storage if it exist.
     *
     * @param item the item for removing.
     */
    void delete(Item item);

    /**
     * Returns list of all items in storage.
     *
     * @return list of all items in storage.
     */
    List<Item> findAll();

    /**
     * Returns list of items that have name like specified as parameter
     * or empty list.
     *
     * @param name the parameter for searching items.
     * @return list of items that have name like specified as parameter
     *         or empty list.
     */
    List<Item> findByName(String name);

    /**
     * Returns item with id like specified as parameter or null.
     *
     * @param id the id for search item.
     * @return item with id like specified as parameter or null.
     */
    Item findById(String id);

    /**
     * Closes connection to the storage.
     *
     * @throws UnsupportedOperationException if operation not supported.
     */
    void close() throws UnsupportedOperationException;
}
