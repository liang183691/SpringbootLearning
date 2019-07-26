package org.spring.springboot.test;

import com.google.gson.annotations.SerializedName;

public class User {
    private Account account;

    @SerializedName("code")
    private String password;

    private String name;

    private int age;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setCode(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [account=" + account.getAccountNo() + ", password=" + password + ", name=" + name + ", age=" + age + "]";
    }
}
