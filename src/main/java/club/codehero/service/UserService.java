package club.codehero.service;

import club.codehero.pojo.User;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/10
 */
public interface UserService {

    User checkUser(String username,String password);

}
