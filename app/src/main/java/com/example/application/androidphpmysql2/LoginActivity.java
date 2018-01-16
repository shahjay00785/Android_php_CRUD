package com.example.application.androidphpmysql2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG = "MainActivity";
    Button btnLogin;
    EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void onClick(View view) {


        etUsername = (EditText) findViewById(R.id.editTextUserName);
        etPassword = (EditText) findViewById(R.id.editTextPwd);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(this);
        final HashMap postdata = new HashMap();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        postdata.put("txtUsername", username);
        postdata.put("txtPassword", password);

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(LoginActivity.this, postdata, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(LOG, s);
                if (s.contains("success")) {
                    Toast.makeText(LoginActivity.this, "sucess full login", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "You can log into", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginActivity.this,ListActivity.class);
                    startActivity(intent);
                }
            }
        });
        task1.execute("http://10.0.2.2/customer");


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
