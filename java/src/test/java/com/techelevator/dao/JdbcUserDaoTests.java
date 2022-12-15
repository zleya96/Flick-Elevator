package com.techelevator.dao;

import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class JdbcUserDaoTests extends BaseDaoTests {
    protected static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER", false);
    protected static final User USER_2 = new User(2, "user2", "user2", "ROLE_USER", false);
    private static final User USER_3 = new User(3, "user3", "user3", "ROLE_USER", false);
    private static final User USER_4 = new User(4, "user4", "user4", "ROLE_USER", false);

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findIdByUsername_given_null_throws_exception() {
        sut.findIdByUsername(null);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findIdByUsername_given_invalid_username_throws_exception() {
        sut.findIdByUsername("invalid");
    }

    @Test
    public void findIdByUsername_given_valid_user_returns_user_id() {
        int actualUserId = sut.findIdByUsername(USER_1.getUsername());

        Assert.assertEquals(USER_1.getId(), actualUserId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUsername_given_null_throws_exception() {
        sut.findByUsername(null);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findByUsername_given_invalid_username_throws_exception() {
        sut.findByUsername("invalid");
    }

    @Test
    public void findByUsername_given_valid_user_returns_user() {
        User actualUser = sut.findByUsername(USER_1.getUsername());

        Assert.assertEquals(USER_1, actualUser);
    }

    public void getUserById_given_invalid_user_id_returns_null() {
        User user = sut.getUserById(-1);

        Assert.assertNull(user);
    }

    @Test
    public void getUserById_given_valid_user_id_returns_user() {
        User actualUser = sut.getUserById(USER_1.getId());

        Assert.assertEquals(USER_1, actualUser);
    }

    @Test
    public void findAll_returns_all_users() {
        List<User> users = sut.findAll();

        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
        Assert.assertEquals(USER_1, users.get(0));
        Assert.assertEquals(USER_2, users.get(1));
        Assert.assertEquals(USER_3, users.get(2));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_user_with_null_username() {
        sut.create(null, USER_3.getPassword(), "ROLE_USER", false);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_user_with_existing_username() {
        sut.create(USER_1.getUsername(), USER_3.getPassword(), "ROLE_USER", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_user_with_null_password() {
        sut.create(USER_3.getUsername(), null, "ROLE_USER", false);
    }

    @Test
    public void create_user_creates_a_user() {
        User newUser = new User(-1, "new", "user", "ROLE_USER", false);

        boolean userWasCreated = sut.create(newUser.getUsername(), newUser.getPassword(), "ROLE_USER", newUser.getMadeAdminRequest());


        Assert.assertTrue(userWasCreated);

        User actualUser = sut.findByUsername(newUser.getUsername());
        newUser.setId(actualUser.getId());

        actualUser.setPassword(newUser.getPassword()); // reset password back to unhashed password for testing
        Assert.assertEquals(newUser, actualUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUser_given_invalid_id_throws_exception() {
        sut.updateUser(-1, USER_1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUser_given_non_matching_id_throws_exception() {
        sut.updateUser(3, USER_1);
    }

    @Test
    public void updateUser_given_valid_params_returns_updated_user() {
        User test = sut.updateUser(1, USER_1);
        Assert.assertEquals(USER_1.getId(), test.getId());
        Assert.assertEquals(USER_1.getUsername(), test.getUsername());
        Assert.assertEquals(USER_1.getAuthorities(), test.getAuthorities());
        Assert.assertEquals(USER_1.getMadeAdminRequest(), test.getMadeAdminRequest());
    }
}
