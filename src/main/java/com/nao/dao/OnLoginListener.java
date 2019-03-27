package com.nao.dao;

import com.nao.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
