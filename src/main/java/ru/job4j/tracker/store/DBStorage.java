package ru.job4j.tracker.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.job4j.tracker.models.Comment;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.services.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements {@link Storage} for using Database.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 05.01.2018.
 */
@Component
public class DBStorage implements Storage {

    private static final Logger LOG = LoggerFactory.getLogger(DBStorage.class);

    private final Connection conn;

    private String addSql;

    private String updateSql;

    private String deleteSql;

    private String getAllSql;

    private String getByNameSql;

    private String getByIdSql;

    private String addCommentSql;

    private String getCommentsSql;

    public DBStorage() {
        final Settings settings = Settings.getInstance();
        try {
            Class.forName(settings.getValue("jdbc.driver_class"));
            this.conn = DriverManager.getConnection(settings.getValue("jdbc.url"),
                    settings.getValue("jdbc.username"), settings.getValue("jdbc.password"));
            this.init(settings);
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void add(Item item) {
        try (PreparedStatement ps = this.conn.prepareCall(this.addSql)) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDesc());
            ps.setTimestamp(4, new Timestamp(item.getCreate()));
            ps.execute();
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Item item) {
        try (PreparedStatement ps = this.conn.prepareStatement(this.updateSql)) {
            ps.setString(1, item.getDesc());
            ps.setString(2, item.getId());
            ps.execute();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        }
        List<Comment> itemComm = item.getComments();
        List<Comment> dbComm = this.getComments(item.getId());
        if (itemComm.size() > dbComm.size()) {
            this.addComment(item.getId(), itemComm.get(itemComm.size() - 1));
        }
    }

    @Override
    public void delete(Item item) {
        try (PreparedStatement ps = this.conn.prepareStatement(this.deleteSql)) {
            ps.setString(1, item.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(this.getAllSql)) {
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), rs.getString("description"),
                                     rs.getTimestamp("create_date").getTime());
                item.setId(rs.getString("id"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        for (Item item : result) {
            item.setComments(this.getComments(item.getId()));
        }
        return result;
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement st = this.conn.prepareStatement(this.getByNameSql)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), rs.getString("description"),
                        rs.getTimestamp("create_date").getTime());
                item.setId(rs.getString("id"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        for (Item item : result) {
            item.setComments(this.getComments(item.getId()));
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement ps = conn.prepareStatement(this.getByIdSql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new Item(rs.getString("name"), rs.getString("description"),
                            rs.getTimestamp("create_date").getTime());
                    result.setId(rs.getString("id"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        if (result != null) {
            List<Comment> comments = this.getComments(id);
            if (comments.size() > 0) {
                result.setComments(comments);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @throws UnsupportedOperationException if operation unsupported
     */
    @Override
    public void close() throws UnsupportedOperationException {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private void init(Settings settings) {
        this.addSql = settings.getValue("jdbc.add_item");
        this.deleteSql = settings.getValue("jdbc.delete_item");
        this.updateSql = settings.getValue("jdbc.update_item");
        this.getAllSql = settings.getValue("jdbc.get_items");
        this.getByNameSql = settings.getValue("jdbc.find_by_name");
        this.getByIdSql = settings.getValue("jdbc.find_by_id");
        this.getCommentsSql = settings.getValue("jdbc.get_comments");
        this.addCommentSql = settings.getValue("jdbc.add_comment");
    }

    private List<Comment> getComments(String itemId) {
        List<Comment> result = new ArrayList<>();
        try (PreparedStatement ps = this.conn.prepareStatement(this.getCommentsSql)) {
            ps.setString(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new Comment(rs.getString("author"), rs.getString("comments")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private void addComment(String itemId, Comment comment) {
        try (PreparedStatement ps = this.conn.prepareStatement(this.addCommentSql)) {
            ps.setString(1, comment.getAuthor());
            ps.setString(2, comment.getText());
            ps.setString(3, itemId);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
