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

public class RegisterController extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://172.16.0.136/tutorial2/Login/registerCompany.php";
    private Map<String, String> params;


         public RegisterController(String name, String username, int password, Response.Listener<String> listener){
            super(Method.POST, REGISTER_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("name", name);
            params.put("username", username);
            params.put("password", String.valueOf(password));
        }

        @Override
        public Map<String, String> getParams () {
            return params;
        }
    }

