package ru.job4j.tracker.store;

import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements {@link Storage} based on {@link ArrayList}.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 05.01.2018.
 */
public class UserStore implements Storage {

    /**
     * The list of items.
     */
    private ArrayList<Item> items = new ArrayList<>(100);

    /**
     * {@inheritDoc}.
     *
     * @param item for adding
     */
    @Override
    public void add(Item item) {
        this.items.add(item);
    }

    /**
     * {@inheritDoc}.
     *
     * @param item the item with updated data.
     */
    @Override
    public void update(Item item) {
        for (int index = 0; index < this.items.size(); index++) {
            if ((this.items.get(index).getId()).equals(item.getId())) {
                this.items.set(index, item);
                break;
            }
        }
    }

    /**
     * {@inheritDoc}.
     *
     * @param item the item for removing.
     */
    @Override
    public void delete(Item item) {
        for (int index = 0; index < this.items.size(); index++) {
            if ((this.items.get(index).getId()).equals(item.getId())) {
                this.items.remove(index);
                break;
            }
        }
    }

    /**
     * {@inheritDoc}.
     *
     * @return list of all items in storage.
     */
    @Override
    public List<Item> findAll() {
        return new ArrayList<>(this.items);
    }

    /**
     * {@inheritDoc}.
     *
     * @param name the parameter for searching items.
     * @return list of items that have name like specified as parameter
     *         or empty list.
     */
    @Override
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if ((item.getName()).equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}.
     *
     * @param id the id for search item.
     * @return item with id like specified as parameter or null.
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}.
     * @throws UnsupportedOperationException
     */
    @Override
    public void close() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not support.");
    }
}
