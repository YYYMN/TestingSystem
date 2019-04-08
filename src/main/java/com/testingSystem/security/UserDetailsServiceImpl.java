package com.testingSystem.security;


import com.testingSystem.model.dao.RoleDao;
import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        com.testingSystem.model.entity.User user;
        user = userDao.getUserByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + " not found");
        }
        Role role = roleDao.getRoleByRoleId(user.getRoleId());

        User.UserBuilder builder;

        builder = org.springframework.security.core.userdetails.User.withUsername(user.getLogin());
        builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
        builder.roles(role.getRoles());

        return builder.build();
    }
}

