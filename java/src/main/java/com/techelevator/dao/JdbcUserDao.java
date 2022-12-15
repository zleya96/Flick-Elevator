package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.techelevator.model.Account;
import com.techelevator.model.Authority;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private AccountDao accountDao;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			return null;
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }


    @Override
    public boolean create(String username, String password, String role, boolean madeAdminRequest) {
        String insertUserSql = "insert into users (username,password_hash,role,made_admin_request) values (?,?,?,?) returning user_id";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        Integer userId = jdbcTemplate.queryForObject(insertUserSql, Integer.class, username, password_hash, ssRole, madeAdminRequest);

        return userId != null;

    }

    @Override
    public User updateUser(int userId, User user) {
        if (userId < 1) {
            throw new IllegalArgumentException("Invalid ID");
        } else if (userId != user.getId()) {
            throw new IllegalArgumentException("ID does not match User");
        }

        String sql = "" +
                "UPDATE users " +
                "SET role = ?, made_admin_request = ? " +
                "WHERE user_id = ?;";

        String authorityName = "";
        Optional<Authority> authority = user.getAuthorities().stream().findFirst();
        if (authority.isPresent()) {
            authorityName = authority.get().getName();
        }

        try {
            jdbcTemplate.update(sql, authorityName, user.getMadeAdminRequest(), userId);
            return getUserById(userId);
        } catch (EmptyResultDataAccessException | NullPointerException e) {

        }
        return null;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthoritiesAsString(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        user.setMadeAdminRequest(rs.getBoolean("made_admin_request"));
        return user;
    }
}
