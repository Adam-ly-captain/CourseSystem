package edu.fjnu501.crms.service.impl;

import edu.fjnu501.crms.domain.User;
import edu.fjnu501.crms.mapper.AccountMapper;
import edu.fjnu501.crms.service.AccountService;
import edu.fjnu501.crms.utils.MD5Password;
import edu.fjnu501.crms.utils.RandomUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public User getUserByUsername(String username) {
        return accountMapper.getUserByUsername(username);
    }

    @Override
    public int getUidByUsername(String username) {
        return accountMapper.getUidByUsername(username);
    }

    @Override
    public String register(User user) throws ParseException {
        String userName = RandomUsername.getRandomUserName(accountMapper.findMaxId());
        user.setAccount(userName);
        // 密码加密
        String md5Pwd = MD5Password.MD5Pwd(user);
        user.setPassword(md5Pwd);
        accountMapper.registerAccount(user);

        return userName;
    }

}
