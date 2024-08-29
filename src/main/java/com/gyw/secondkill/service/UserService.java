package com.gyw.secondkill.service;

import com.gyw.secondkill.dao.UserDao;
import com.gyw.secondkill.domain.User;
import com.gyw.secondkill.result.CodeMsg;
import com.gyw.secondkill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dell
 * @create 2019-07-14 16:04
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx() {
        User u1= new User();
        u1.setId(2);
        u1.setName("2222");
        userDao.insert(u1);

        User u2= new User();
        u2.setId(1);
        u2.setName("11111");
        userDao.insert(u2);

        return true;
    }

}
