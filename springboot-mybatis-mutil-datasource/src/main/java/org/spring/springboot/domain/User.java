package org.spring.springboot.domain;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * Created by bysocket on 07/02/2017.
 */
public class User implements Serializable {

    private int id;

    private String userId;

    private String name;

    private String sex;

    private String age;

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
