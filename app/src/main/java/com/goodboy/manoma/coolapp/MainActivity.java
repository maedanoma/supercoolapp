package com.goodboy.manoma.coolapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;

import com.goodboy.manoma.coolapp.framework.Initializer;
import com.goodboy.manoma.coolapp.login.LoginController;
import com.goodboy.manoma.coolapp.login.LoginView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initializer initializer = Initializer.newInstance();
        initializer.initialize(this,
                Pair.create(LoginView.class, LoginController.class));
    }
}
