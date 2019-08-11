package org.felipe.springbootapp.features.account;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ResultSetExtractor<Account> extractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;

    public static final RowMapper<Account> mapper = new RowMapper<Account>() {

        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Account(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("email")
            );
        }
    };


    public AccountRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Account find(long id) {
        return jdbcTemplate.query(
                "SELECT id, name, username, email FROM account WHERE id = ?",
                new Object[]{id},
                extractor);
    }

    public Account findByUsername(String username) {
        return jdbcTemplate.query(
                "SELECT id, name, username, email FROM account WHERE username = ?",
                new Object[]{username},
                extractor);
    }

    public Account create(Account account) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO account (name, username, email) " +
                            "VALUES (?, ?, ?)",
                    RETURN_GENERATED_KEYS
            );

            statement.setString(1, account.getName());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getEmail());

            return statement;
        }, generatedKeyHolder);

        return find(generatedKeyHolder.getKey().longValue());
    }

    public List<Account> list() {
        return jdbcTemplate.query("SELECT id, name, username, email FROM account", mapper);
    }

    public Account update(long id, Account account) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE account SET name = ?, username = ?, email = ?  " +
                            "WHERE id  = ? "

            );

            statement.setString(1, account.getName());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getEmail());
            statement.setLong(4, id);

            return statement;
        });

        return find(id);

    }

    public void delete(long id) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM account WHERE id  = ? "

            );
            statement.setLong(1, id);

            return statement;
        });
    }


}
