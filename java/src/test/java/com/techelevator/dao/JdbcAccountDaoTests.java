package com.techelevator.dao;

import com.techelevator.model.Account;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.constraints.AssertTrue;

public class JdbcAccountDaoTests extends BaseDaoTests{

    protected static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER", false);
    protected static final User USER_2 = new User(2, "user2", "user2", "ROLE_USER", false);
    private static final User USER_3 = new User(3, "user3", "user3", "ROLE_USER", false);
    private static final User USER_4 = new User(4, "user4", "user4", "ROLE_USER", false);

    protected static final Account ACCOUNT_1 = new Account(1, 1, "1,2,3", "1,2,3", "1,2,3", "action,adventure");
    protected static final Account ACCOUNT_2 = new Account(2, 2, "4,5,6", "4,5,6", "4,5,6", "romance,thriller");
    protected static final Account ACCOUNT_3 = new Account(3, 3, "7,8,9", "7,8,9", "7,8,9", "comedy,horror");

    private JdbcAccountDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDao(jdbcTemplate);
    }




    @Test(expected = IllegalArgumentException.class)
    public void getAccountByAccountId_given_invalid_id_throws_exception() {
        sut.getAccountByAccountId(-1);
    }

    @Test
    public void getAccountByAccountId_given_nonexistent_returns_null() {
        sut.getAccountByAccountId(20);
    }

    @Test
    public void getAccountByAccountId_given_valid_id_returns_account() {
        Account test = sut.getAccountByAccountId(ACCOUNT_1.getAccountId());
        int actual = test.getAccountId();
        Assert.assertEquals(1, actual);
        Assert.assertEquals(1, test.getUserId());
        Assert.assertEquals("1,2,3", test.getLikedMovies());
        Assert.assertEquals("1,2,3", test.getDislikedMovies());
        Assert.assertEquals("1,2,3", test.getFavoriteMovies());
        Assert.assertEquals("action,adventure", test.getPreferredGenres());
    }




    @Test(expected = IllegalArgumentException.class)
    public void getAccountByUserId_given_invalid_id_throws_exception() {
        sut.getAccountByUserId(-1);
    }

    @Test
    public void getAccountByUserId_given_nonexistent_returns_null() {
        sut.getAccountByUserId(20);
    }

    @Test
    public void getAccountByUserId_given_valid_id_returns_account() {
        Account test = sut.getAccountByUserId(ACCOUNT_1.getUserId());
        int actual = test.getUserId();
        Assert.assertEquals(1, actual);
        Assert.assertEquals(1, test.getUserId());
        Assert.assertEquals("1,2,3", test.getLikedMovies());
        Assert.assertEquals("1,2,3", test.getDislikedMovies());
        Assert.assertEquals("1,2,3", test.getFavoriteMovies());
        Assert.assertEquals("action,adventure", test.getPreferredGenres());
    }




    @Test(expected = IllegalArgumentException.class)
    public void updateAccount_given_invalid_id_throws_exception() {
        sut.updateAccount(-1, ACCOUNT_1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateAccount_given_non_matching_id_throws_exception() {
        sut.updateAccount(2, ACCOUNT_1);
    }

    @Test
    public void updateAccount_given_valid_params_returns_updated_account() {
        Account test = sut.updateAccount(1, ACCOUNT_1);
        int actual = test.getAccountId();
        Assert.assertEquals(1, actual);
        Assert.assertEquals(1, test.getUserId());
        Assert.assertEquals("1,2,3", test.getLikedMovies());
        Assert.assertEquals("1,2,3", test.getDislikedMovies());
        Assert.assertEquals("1,2,3", test.getFavoriteMovies());
        Assert.assertEquals("action,adventure", test.getPreferredGenres());
    }



    @Test(expected = IllegalArgumentException.class)
    public void createAccount_given_invalid_id_throws_exception() {
        sut.createAccount(-1);
    }

    @Test
    public void create_account_given_valid_id_returns_created_account() {
        Account test = sut.createAccount(4);

        int actual = test.getUserId();

        Assert.assertEquals(4, actual);

    }

}
