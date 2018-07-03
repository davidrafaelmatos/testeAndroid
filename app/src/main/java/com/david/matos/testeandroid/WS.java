package com.david.matos.testeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.david.matos.testeandroid.DB.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws);

        loadData();

    }

    private void loadData() {
        Response.Listener<JSONObject> objMarca = new Response.Listener<JSONObject>() {

            JSONArray dados;
            JSONObject dado;
            JSONArray notas;
            JSONObject nota;
            String status;
            int countIdade = 0, countNota = 0;

            @Override
            public void onResponse(JSONObject response) {

                try {
                    status = response.getString("status");
                    if (status.toString().equals("OK")) {
                        try {
                            dados = response.getJSONArray("dados");
                            for (int i = 0; i < dados.length(); i++){
                                dado = dados.getJSONObject(i);
                                if (dado.getInt("idade") > 16){
                                    countIdade++;
                                    System.out.println("idade" + countIdade);
                                }
                                notas = dado.getJSONArray("notas");
                                for (int a = 0; a < notas.length(); a++) {
                                    nota = notas.getJSONObject(i);
                                    if (nota.getInt("nota") > 15) {
                                        countNota++;
                                        System.out.println("nota" + countNota);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    fillWS(countIdade, countNota);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListenerMarca = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        WebService.getInstance(getApplicationContext()).GetWS(objMarca, errorListenerMarca);
    }

    private void fillWS(int countIdade, int countNota) {
        TextView lblIdade, lblNota;

        lblIdade = findViewById(R.id.lblIdade);
        lblIdade.setText(String.valueOf(countIdade));

        lblNota = findViewById(R.id.lblNotas);
        lblNota.setText(String.valueOf(countNota));

    }
}
