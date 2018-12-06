package com.example.mm.ametloginandregister.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mm.ametloginandregister.Helper.SharedPreferencesStorage;
import com.example.mm.ametloginandregister.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferencesStorage storage = new SharedPreferencesStorage();
        if (storage.getEmail(this) != null){
            Intent intent = new Intent(this, UsersActivity.class);
            startActivity(intent);
        }
    }


    public void toActivity(View view) {

        int buttonID = view.getId();

        switch (buttonID) {
            case R.id.sign_in_button:
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                break;

            case R.id.sign_up_button:
                startActivity(new Intent(MainActivity.this, SignUp.class));
                break;
        }
    }
}
