package com.nao.bean;

/**
 * @ClassName User
 * @Description userBean
 * @Author NaO
 * @Date 3/21/2019 5:37 PM
 * @Version 1.0
 **/
public class User {
    private String username;
    private String password;
    private String phonenumber;
    private String email;
    private String department;
    private String otp_sk;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp_sk() {
        return otp_sk;
    }

    public void setOtp_sk(String otp_sk) {
        this.otp_sk = otp_sk;
    }
}
