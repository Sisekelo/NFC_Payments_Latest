package com.example.admin2.payments;

/**
 * Created by Admin2 on 5/28/2017.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EnterController extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://truth101.000webhostapp.com/register.php";
    private Map<String, String> params;


         public EnterController(String sender, String receiver, int amount, Response.Listener<String> listener){
            super(Method.POST, REGISTER_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("sender", sender);
             params.put("receiver", receiver);
             params.put("amount", String.valueOf(amount));
        }

        @Override
        public Map<String, String> getParams () {
            return params;
        }
    }

