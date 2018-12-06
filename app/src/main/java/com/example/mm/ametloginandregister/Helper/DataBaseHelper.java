package com.example.mm.ametloginandregister.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mm.ametloginandregister.Model.UserInfo;

import static com.example.mm.ametloginandregister.Helper.Constants.EMAIL_6;
import static com.example.mm.ametloginandregister.Helper.Constants.FIRSTNAME_2;
import static com.example.mm.ametloginandregister.Helper.Constants.LASTNAME_3;
import static com.example.mm.ametloginandregister.Helper.Constants.PASSORD_4;
import static com.example.mm.ametloginandregister.Helper.Constants.PHONE_7;
import static com.example.mm.ametloginandregister.Helper.Constants.RE_PASSORD_5;
import static com.example.mm.ametloginandregister.Helper.Constants.TABLE_NAME;

public class DataBaseHelper extends SQLiteOpenHelper {


    public DataBaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DROP_USER_TABLE);
        onCreate(db);
    }

    public Long addNweUser(UserInfo user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIRSTNAME_2, user.getmFirstName());
        values.put(LASTNAME_3, user.getmLastName());
        values.put(PASSORD_4, user.getmPassword());
        values.put(RE_PASSORD_5, user.getmRe_password());
        values.put(EMAIL_6, user.getmEmail());
        values.put(PHONE_7, user.getmPhoneN());

        Long id = db.insert(TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public Cursor getUser(String email, String password){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM " + TABLE_NAME + " WHERE " + EMAIL_6 + "=? AND " + PASSORD_4
                + "=?", new String[]{email, password});
        return cursor;
    }
}
