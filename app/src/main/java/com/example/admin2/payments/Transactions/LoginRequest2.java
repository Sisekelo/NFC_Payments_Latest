package com.example.admin2.payments.Transactions;

/**
 * Created by Admin2 on 5/28/2017.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest2 extends StringRequest {

        private static final String LOGIN_REQUEST_URL = "http://172.16.0.136/tutorial2/Login/login.php";
    private Map<String, String> params;


    public LoginRequest2(String username, String password, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams () {
        return params;
    }
}

