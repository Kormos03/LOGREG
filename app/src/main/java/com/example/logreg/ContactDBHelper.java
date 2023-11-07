package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "felhasznalo";
    private static final String COL_ID = "ID";
    private static final String COL_EMAIL = "email";
    private static final String COL_FELHNEV = "felhnev";
    private static final String COL_JELSZO = "jelszo";
    private static final String COL_TELJESNEV = "teljesnev";

    public ContactDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    String sql = "CREATE TABLE " + TABLE_NAME
            + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_EMAIL + " TEXT UNIQUE NOT NULL, "
            + COL_FELHNEV + " TEXT UNIQUE NOT NULL, "
            + COL_JELSZO + " TEXT NOT NULL, "
            + COL_TELJESNEV + " TEXT NOT NULL);";
    db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    }
    public boolean rogzites(String email, String felhnev, String jelszo, String teljesnev) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, felhnev);
        values.put(COL_JELSZO, jelszo);
        values.put(COL_TELJESNEV, teljesnev);

        long result = db.insert(TABLE_NAME, null, values);

        return result != -1;
    }

    /*public boolean hozzaad(String email, String felhasznalonev, String jelszo, String teljesnev)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, felhasznalonev);
        values.put(COL_JELSZO, jelszo);
        values.put(COL_TELJESNEV, teljesnev);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }*/
    public boolean bejelentkezes(String felhasznalonev, String jelszo) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                COL_FELHNEV + " = ? AND " + COL_JELSZO + " = ?",
                new String[] { felhasznalonev, jelszo },
                null, null, null
        );

        boolean isLoggedIn = cursor.moveToFirst();
        cursor.close();
        db.close();

        return isLoggedIn;
    }
    public Cursor getName()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME,new String[] {COL_FELHNEV}, null, null,null,null,null);
    }
}
