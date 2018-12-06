package com.example.mm.ametloginandregister.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mm.ametloginandregister.Helper.SharedPreferencesStorage;
import com.example.mm.ametloginandregister.R;

public class UsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int getId = item.getItemId();
        switch (getId){
            case R.id.log_out:
                SharedPreferencesStorage.savePassword(null, this);
                SharedPreferencesStorage.saveEmail(null, this);
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
        finish();
    }
}
