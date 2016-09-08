package com.zllh.common.common.model;


public class PubUser {
    private String userId;

    private String userName;

    private String acountName;

    private String password;

    private String createdatetime;

    private String modifydatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAcountName() {
        return acountName;
    }

    public void setAcountName(String acountName) {
        this.acountName = acountName == null ? null : acountName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(String newDate) {
        this.createdatetime = newDate;
    }

    public String getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(String modifydatetime) {
        this.modifydatetime = modifydatetime;
    }
}