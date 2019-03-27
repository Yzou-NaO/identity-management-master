package com.nao.dao;

import android.util.Log;

import com.nao.bean.User;
import com.nao.util.DBUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Classname UserDAO
 * @Description add&find user
 * @Author NaO
 * @Date 3/22/2019 12:01 PM
 * @Version 1.0
 **/
public class UserDAO {
    public static void main(String[] args) {

    }

//    /**
//     * @param user
//     */
//    public void add(User user) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        try {
//            conn = DBUtil.getConn();
//            String sql = "INSERT INTO t_user(username,otp_sk) values (?,?)";
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, user.getUsername());
//            stmt.setString(2, user.getOtp_sk());
//            stmt.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    // ignore
//                }
//            }
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    // ignore
//                }
//            }
//        }
//    }

    /**
     * @param username
     * @return user
     */
    public void getUserByName(String username,OnLoginListener loginListener) {
        new Thread() {
            @Override
            public void run() {
                User user = new User();
                user.setUsername(username);
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder formBody = new FormBody.Builder();
                    formBody.add("username", username);
                    Request request = new Request.Builder()
                            .url("http://10.0.41.50:8080/otp_demo_master_war_exploded/GetInformationServlet")
                            .post(formBody.build())
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String jsonData = response.body().string();
                        JSONArray jsonArray = new JSONArray(jsonData);
                        JSONObject object = jsonArray.getJSONObject(0);
                        user.setPhonenumber(object.getString("phonenumber"));
                        user.setEmail(object.getString("email"));
                        user.setDepartment(object.getString("department"));
                        user.setOtp_sk(object.getString("otpsk"));
                    }
                    loginListener.loginSuccess(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * @param username
     * @param password
     * @param loginListener
     */
    public void login(String username, String password, OnLoginListener loginListener) {
        new Thread() {
            @Override
            public void run() {
                if (username.equals("") || password.equals("")) {
                    loginListener.loginFailed();
                } else {
                    try {
                        OkHttpClient client = new OkHttpClient();
                        FormBody.Builder formBody = new FormBody.Builder();
                        formBody.add("username", username);
                        formBody.add("password", password);
                        Request request = new Request.Builder()
                                .url("http://10.0.41.50:8080/otp_demo_master_war_exploded/MyLoginServlet")
                                .post(formBody.build())
                                .build();
                        Response response = null;
                        response = client.newCall(request).execute();
                        if (response.isSuccessful()) {
                            if (response.body().string().equals("true\r\n")) {
                                UserDAO userDAO = new UserDAO();
                                userDAO.getUserByName(username,loginListener);
                            } else {
                                loginListener.loginFailed();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
