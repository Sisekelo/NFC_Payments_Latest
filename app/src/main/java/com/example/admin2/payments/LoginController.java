package com.example.admin2.payments;

/**
 * Created by Admin2 on 5/28/2017.
 */

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.name;
import static com.example.admin2.payments.R.string.password;

public class LoginController extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "https://truth101.000webhostapp.com/LoginCompany.php";
    private Map<String, String> params;


    public LoginController(String username, String password, Response.Listener<String> listener){
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

