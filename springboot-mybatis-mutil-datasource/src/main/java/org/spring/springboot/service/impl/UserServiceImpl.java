package org.spring.springboot.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import org.spring.springboot.dao.cluster.CityDao;
import org.spring.springboot.dao.master.UserDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 用户业务实现层
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源
    @Autowired
RestTemplate restTemplate;
    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        System.out.println("主表："+ user.getName());//JSONUtils.toJSONString(user)
        User city = cityDao.findByName("温岭市");
        System.out.println("从表："+city.getName());
        return user;
    }
}
