package edu.fjnu501.crms.shiro;

import edu.fjnu501.crms.service.AccountService;
import edu.fjnu501.crms.domain.User;
import edu.fjnu501.crms.state.RoleType;
import edu.fjnu501.crms.utils.MD5Password;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        User userByUsername = accountService.getUserByUsername(username);

        if (userByUsername == null) {
            throw new UnknownAccountException("账号不存在");
        }

        // 用来判断之前保存的token中的账号密码是否正确
        AuthenticationInfo info = new SimpleAuthenticationInfo(userByUsername, userByUsername.getPassword(), ByteSource.Util.bytes(userByUsername.getAccount() + MD5Password.getShiroSalt()), getName());
        return info;

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<String> role = new HashSet<>(1);
        if (RoleType.registrar.getType() == user.getRoleId()) {
            role.add("registrar");
        } else if (RoleType.teacher.getType() == user.getRoleId()) {
            role.add("teacher");
        } else {
            role.add("student");
        }
        authorizationInfo.setRoles(role);
        return authorizationInfo;
    }

}
