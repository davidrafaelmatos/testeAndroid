package com.david.matos.testeandroid.DB;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebService {

    private static WebService ourInstance;
    public static synchronized WebService getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new WebService(context);

        return ourInstance;
    }

    private Context context;
    private RequestQueue requestQueue;

    private WebService(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        return requestQueue;
    }

    private String url = "http://ahead.ycorn.pt/saraws/ws_teste_3.php";

    public void GetUserById(int idUser, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                listener,
                errorListener)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type","application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };

        getRequestQueue().add(jsObjRequest);
    }


}


