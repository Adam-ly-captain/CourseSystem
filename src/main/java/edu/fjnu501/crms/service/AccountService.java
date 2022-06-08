package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.User;

import java.text.ParseException;

public interface AccountService {

    User getUserByUsername(String username);

    int getUidByUsername(String username);

    String register(User user) throws ParseException;

}
