package edu.fjnu501.crms.mapper;

import edu.fjnu501.crms.domain.User;

public interface AccountMapper {

    User getUserByUsername(String username);

    int getUidByUsername(String username);

    int findMaxId();

    void registerAccount(User user);

}
