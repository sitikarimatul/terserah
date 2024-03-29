package com.example.terserah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;

public class registrasiActivity extends AppCompatActivity {

  private RequestQueue mRequestQueue;
  private Button btnLinkLogin;
  private EditText edtNpm, edtNama, edtProdi, edtPassword;
  private Button btnKirim;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registrasi);
    getSupportActionBar().hide();
    btnLinkLogin= (Button) findViewById(R.id.btnLinkLogin);
    btnKirim = (Button) findViewById(R.id.btnKirim);
    mRequestQueue = Volley.newRequestQueue(this);

    edtNpm = (EditText) findViewById(R.id.edtNpm);
    edtProdi = (EditText) findViewById(R.id.edtProdi);
    edtNama =(EditText) findViewById(R.id.edtNama);
    edtPassword = (EditText) findViewById(R.id.edtPassword);

    btnKirim = (Button) findViewById(R.id.btnKirim);
    btnKirim.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String strNpm = edtNpm.getText().toString();
        String strProdi = edtProdi.getText().toString();
        String strNama = edtNama.getText().toString();
        String strPass = edtPassword.getText().toString();

        if(strNpm.isEmpty()){
          Toast.makeText(getApplicationContext(), "Npm tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else if(strProdi.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strNama.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Prodi tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strPass.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Password tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else {
  inputData(strNpm, strProdi,strNama, strPass);

          Intent a;
          a = new Intent(registrasiActivity.this, loginactivity.class);
          startActivity(a);
          finish();
        } }
    });

    getSupportActionBar().hide();
    btnLinkLogin= (Button) findViewById(R.id.btnLinkLogin);
  btnLinkLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(registrasiActivity.this, loginactivity.class);
        startActivity(i);
        finish();
      }
    });
  }
  private void inputData(String npm, String nama, String Prodi, String Pass) {
    //final String URL = "/volley/resource/12";
    // Post params to be sent to the server
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("npm", npm);
    params.put("nama", nama);
    params.put("prodi", Prodi);
    params.put("password", Pass);

    JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.inputDataMhs, new JSONObject(params),
      new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
          try {
            VolleyLog.v("Response:%n %s", response.toString(4));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        VolleyLog.e("Error: ", error.getMessage());
      }
    });

// add the request object to the queue to be executed
//ApplicationController.getInstance().addToRequestQueue(req);
    mRequestQueue.add(req);
  }
}

