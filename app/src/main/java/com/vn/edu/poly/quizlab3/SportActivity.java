package com.vn.edu.poly.quizlab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SportActivity extends AppCompatActivity {

    private CheckBox cbA;
    private CheckBox cbB;
    private CheckBox cbC;
    private CheckBox cbD;
    private Button btnNext;
    private TextView txtvCauhoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        initView();
        getData();

    }


    private void getData() {

        RequestQueue requestQueue = Volley.newRequestQueue(SportActivity.this);
        String url = "http://www.dotplays.com/android/lab3.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ketqua", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject jsonObjectQuiz = jsonObject.getJSONObject("quiz");
                            JSONObject jsonObjectSport = jsonObjectQuiz.getJSONObject("sport");
                            JSONObject jsonObjectQ1 = jsonObjectSport.getJSONObject("q1");

                            JSONArray jsonArrayOption = jsonObjectQ1.getJSONArray("options");
                            //JSONObject jsonObject1 = jsonArrayOption.getJSONObject(0);

                            String a = jsonArrayOption.getString(0);
                            String b = jsonArrayOption.getString(1);
                            String c = jsonArrayOption.getString(2);
                            String d = jsonArrayOption.getString(3);


                            String question = jsonObjectQ1.getString("question");
                            String answer = jsonObjectQ1.getString("answer");

                            Log.d("cauhoi", "cau hoi la: " + question);
                            Log.d("traloi", answer);
                            Log.d("option", answer);
                            Log.d("A", a);

                            txtvCauhoi.setText(question);
                            cbA.setText(a);
                            cbA.setText(b);
                            cbA.setText(c);
                            cbA.setText(d);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    private void initView() {
        cbA = (CheckBox) findViewById(R.id.cbA);
        cbB = (CheckBox) findViewById(R.id.cbB);
        cbC = (CheckBox) findViewById(R.id.cbC);
        cbD = (CheckBox) findViewById(R.id.cbD);
        btnNext = (Button) findViewById(R.id.btnNext);
        txtvCauhoi = (TextView) findViewById(R.id.txtvCauhoi);
    }
}
