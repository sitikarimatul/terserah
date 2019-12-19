package com.example.terserah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginactivity extends AppCompatActivity {

  private Button btnLinkRegister;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_loginactivity);
    getSupportActionBar().hide();

     btnLinkRegister = (Button) findViewById(R.id.btnLinkRegister);
     btnLinkRegister.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         Intent i = new Intent(loginactivity.this,registrasiActivity.class);
         startActivity(i);
         finish();
       }
     });
  }
}
