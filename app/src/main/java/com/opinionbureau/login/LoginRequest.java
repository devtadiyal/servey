package com.opinionbureau.login;

public class LoginRequest {

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCulture_id(String culture_id) {
        this.culture_id = culture_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    private String email;
    private String password;
    private String culture_id;
    private String device_id;
    private String device_type;



}
