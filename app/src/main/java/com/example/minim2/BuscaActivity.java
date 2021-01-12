package com.example.minim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BuscaActivity extends AppCompatActivity {

    APIInterface APIIface;
    String user;
    Button button;
    Handler handler = new Handler();


    public String getUser(View v){
        EditText usernameContainer;
        usernameContainer = (EditText)findViewById(R.id.editTextTextPersonName);
        return usernameContainer.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

    }

    public void onClick(View v) {

        user = getUser(v);
        openMainActivity();
    }

    public void openMainActivity(){

        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.putExtra("username",this.user);
        startActivity(mainActivity);

    }

}