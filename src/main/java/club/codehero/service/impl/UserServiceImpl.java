package club.codehero.service.impl;

import club.codehero.dao.UserDao;
import club.codehero.pojo.User;
import club.codehero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
}
