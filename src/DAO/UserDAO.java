package DAO;

import Utilities.Constants.SqlQueries;

public class UserDAO {


    public boolean addUser(String userName, String password) {
        String query = SqlQueries.SELECT_ALL_USERS;

        return true;
    }
}
