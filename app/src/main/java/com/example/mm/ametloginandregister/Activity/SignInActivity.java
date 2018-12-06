package com.example.mm.ametloginandregister.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mm.ametloginandregister.Helper.DataBaseHelper;
import com.example.mm.ametloginandregister.Helper.InputValidation;
import com.example.mm.ametloginandregister.Helper.SharedPreferencesStorage;
import com.example.mm.ametloginandregister.R;

public class SignInActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private CheckBox checkBox;

    private TextView signInLink;
    private EditText editTextMail, editTextPassword;

    private Button mLoginButton;

    private InputValidation mInputValidation;
    private DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViews();

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("SignUp");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mInputValidation = new InputValidation();
        mDataBaseHelper = new DataBaseHelper(this);

        SignInLink ();
    }


    private void findViews(){
        signInLink = findViewById(R.id.signUp_link);

        editTextPassword = findViewById(R.id.passowrd_id);

        editTextMail = findViewById(R.id.email_id);

        mLoginButton = findViewById(R.id.sign_in_button);
    }

    public void connectAndStoreData(){
        String textMail = editTextMail.getText().toString().trim();
        String textPassword = editTextPassword.getText().toString().trim();

        if (!mInputValidation.isValidMail(editTextMail)) {
            editTextMail.setError("Please Enter Correct Email..");
            return;

        }else if (!mInputValidation.isValidPassword(editTextPassword)) {
            editTextPassword.setError("Please Enter Strong Password..");
            return;

        }else {
            Cursor cursor = mDataBaseHelper.getUser(textMail, textPassword);
            if (cursor.getCount() != 0){

                SharedPreferencesStorage.saveEmail(textMail, this);
                SharedPreferencesStorage.savePassword(textPassword, this);

                Intent toMain = new Intent(this, UsersActivity.class);
                toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                emptyInputEditText();
                startActivity(toMain);
                finish();

            }else {
                Toast.makeText(this, "FFFF", Toast.LENGTH_SHORT).show();

            }
        }

    }



    private void SignInLink (){
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignUpActivity();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void toSignUpActivity() {
        startActivity(new Intent(this, SignUp.class));
    }

    public void showAndHidePassword(View view) {
        checkBox = findViewById(R.id.show_id);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    checkBox.setText(getString(R.string.show_txt));

                }else {
                    editTextPassword.setInputType(129);
                    checkBox.setText(getString(R.string.hide_txt));
                }
            }
        });
    }

    public void logInButton(View view) {
        connectAndStoreData();
    }

    private void emptyInputEditText(){
        editTextMail.setText(null);
        editTextPassword.setText(null);
    }
}
