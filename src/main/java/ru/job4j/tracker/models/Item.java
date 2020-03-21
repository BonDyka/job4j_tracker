package ru.job4j.tracker.models;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represent main form of order for task project.
 *
 * @author abondarev.
 * @since 22.07.2017.
 */
public class Item {

	/**
	 * The variable holds the id of the task.
	 */
	private String id;

	/**
	 * The variable holds the name of the task.
	 */
	private String name;

	/**
	 * The variable holds the description of the task.
	 */
	private String desc;

	/**
	 * The variable holds the create time of the task.
	 */
	private final long create;

	/**
	 * The variable holds all comments for the task.
	 */
	private List<Comment> comments = new ArrayList<>(100);

	/**
	 * The pointer for comment storage.
	 */
	@SuppressWarnings("FieldCanBeLocal")
	private int pointer = 0;

	/**
	 * The constructor takes four parameters and based on them created instance
	 * of Item class.
	 *
	 * @param name is a name of the item.
	 * @param desc is a description of the item.
	 * @param create is a create time of the item.
	 */
	public Item(String name, String desc, long create) {
		this.name = name;
		this.desc = desc;
		this.create = create;
	}

	/**
	 * The method set up id specified as parameter.
	 * The id SETS JUST ONE TIME.
	 *
	 * @param id is id for the item.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * The method return the id of the item.
	 *
	 * @return id of the item.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * The method return the name of the item.
	 *
	 * @return name of the item.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * The method return the description of the item.
	 *
	 * @return description of the item.
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * The method return the create time of the item.
	 *
	 * @return create time of the item.
	 */
	public long getCreate() {
		return this.create;
	}

	/**
	 * The method return all comments if they exist.
	 *
	 * @return all comments.
	 */
	public List<Comment> getComments() {
		return new ArrayList<>(this.comments);
	}

	public void setComments(List<Comment> comments) {
		this.comments.addAll(comments);
	}

	/**
	 * The method takes two arguments and add them, in string format, to
	 * the comments. Return <i>true</i> if comment was add.
	 *
	 * @param comment is comment.
	 * @return <i>true</i> if comment was add and <i>false</i> otherwise.
	 */
	public boolean addComment(Comment comment) {
		this.comments.add(comment);
		return true;
	}

	/**
	 * The method return a string that represents the item.
	 *
	 * @return a string that represents the item
	 */
	public String toString() {
		String lineSeparator = System.getProperty("line.separator");
		return String.format("Item id %s%sName: %s%sDescription: %s%sCreated: %s%s%s",
									  this.id, lineSeparator, this.name, lineSeparator,
									  this.desc, lineSeparator,
									  DateFormat.getDateInstance().format(this.create),
									  lineSeparator, lineSeparator);
	}
}