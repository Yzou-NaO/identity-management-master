package com.nao.presenter;

import com.nao.activity.LoginActivity;
import com.nao.bean.User;
import com.nao.dao.OnLoginListener;
import com.nao.dao.UserDAO;
import com.nao.util.Utils;

import android.os.Handler;


/**
 * @Classname LoginPresenter
 * @Description
 * @Author NaO
 * @Date 3/22/2019 12:17 PM
 * @Version 1.0
 **/
public class LoginPresenter {
    private UserDAO userDAO;
    private LoginActivity loginActivity;
    private Handler handler = new Handler();

    public LoginPresenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.userDAO = new UserDAO();
    }

    /**
     * 登录并将用户信息存入SharedPreference
     */
    public void login() {
        userDAO.login(loginActivity.getUserName(), loginActivity.getPassWord(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Utils.putValue(loginActivity,"username",user.getUsername());
                        Utils.putValue(loginActivity,"phonenumber",user.getPhonenumber());
                        Utils.putValue(loginActivity,"email",user.getEmail());
                        Utils.putValue(loginActivity,"department",user.getDepartment());
                        Utils.putValue(loginActivity,"otpsk",user.getOtp_sk());
                        Utils.showShortToast(loginActivity,"登陆成功");
                        loginActivity.toMainActivty();
                    }
                });
            }

            @Override
            public void loginFailed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginActivity.showFailedError();
                    }
                });
            }
        });
    }
}
