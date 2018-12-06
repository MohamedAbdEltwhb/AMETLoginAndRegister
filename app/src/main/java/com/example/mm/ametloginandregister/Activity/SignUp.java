package com.example.mm.ametloginandregister.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mm.ametloginandregister.Helper.DataBaseHelper;
import com.example.mm.ametloginandregister.Helper.InputValidation;
import com.example.mm.ametloginandregister.Model.UserInfo;
import com.example.mm.ametloginandregister.R;

public class SignUp extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView signInLink;
    private Button mSignUpButton;
    private  EditText mFirstName, mLastName,mPassword, mRe_password, mEmail, mPhoneN;

    private InputValidation mInputValidation;
    private DataBaseHelper mDataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("SignUp");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mInputValidation = new InputValidation();
        mDataBaseHelper = new DataBaseHelper(this);

        findViews();
        SignInLink ();
    }

    public void CreateNewAccount(View view) {
        connectAndStoreData();
    }

    private void findViews(){
        mFirstName = findViewById(R.id.f_name_id);
        mLastName = findViewById(R.id.l_name_id);
        mPassword = findViewById(R.id.passord_id);
        mRe_password = findViewById(R.id.re_passord_id);
        mEmail = findViewById(R.id.email_id);
        mPhoneN = findViewById(R.id.phone_id);
        mSignUpButton = findViewById(R.id.sign_in_button);

    }

    public void connectAndStoreData(){

        if (!mInputValidation.isValidName(mFirstName)){
            mFirstName.setError("Please Enter Correct Name..");
            return;
        }else if (!mInputValidation.isValidName(mLastName)){
            mLastName.setError("Please Enter Correct Name..");
            return;
        }else if (!mInputValidation.isValidPassword(mPassword)){
            mPassword.setError("Please Enter Strong Password..");
            return;
        }else if (!mInputValidation.isValidRePassword(mRe_password, mPassword)){
            mRe_password.setError("Re Password Is not equal Password..");
            return;
        }else if (!mInputValidation.isValidMail(mEmail)){
            mEmail.setError("Please Enter Correct Email..");
            return;
        }else if (!mInputValidation.isValidMobile(mPhoneN)){
            mPhoneN.setError("Please Enter Correct mobile number..");
            return;
        }else {

            String _mFirstName = mFirstName.getText().toString().trim();
            String _mLastName = mLastName.getText().toString().trim();
            String _mPassword = mPassword.getText().toString().trim();
            String _mRe_password = mRe_password.getText().toString().trim();
            String _mEmail = mEmail.getText().toString().trim();
            String _mPhoneN = mPhoneN.getText().toString().trim();

            UserInfo userInfo = new UserInfo(_mFirstName,
                    _mLastName, _mPassword,
                    _mRe_password, _mEmail, _mPhoneN);

            Long id = mDataBaseHelper.addNweUser(userInfo);

            Toast.makeText(SignUp.this, "id is:" + id, Toast.LENGTH_SHORT).show();

            emptyInputEditText();
            toSignInActivity();
            finish();

        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                toSignInActivity();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void SignInLink (){
        signInLink = (TextView)findViewById(R.id.sign_link);
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignInActivity();
            }
        });
    }

    private void toSignInActivity(){
        startActivity(new Intent(this, SignInActivity.class));
    }

    private void emptyInputEditText(){
        mFirstName.setText(null);
        mLastName.setText(null);
        mPassword.setText(null);
        mRe_password.setText(null);
        mEmail.setText(null);
        mPhoneN.setText(null);
    }




}
