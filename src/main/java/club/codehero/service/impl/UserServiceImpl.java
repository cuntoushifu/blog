package club.codehero.service.impl;

import club.codehero.dao.UserDao;
import club.codehero.pojo.User;
import club.codehero.service.UserService;
import club.codehero.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/10
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        password = EncryptUtils.encrypt(password);
        return userDao.findByUsernameAndPassword(username, password);
    }
}
