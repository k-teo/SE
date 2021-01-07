package com.studies.se;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;

public class EmployeeDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "words_database";
    public static final String EMPLOYEE_TABLE_NAME = "employees";
    public static final String EMPLOYEE_COLUMN_ID = "id";
    public static final String EMPLOYEE_COLUMN_NAME = "name";
    public static final String EMPLOYEE_COLUMN_SURNAME = "surname";
    public static final String EMPLOYEE_COLUMN_RATE = "rate";
    public static final String EMPLOYEE_COLUMN_PHONE = "phone_number";
    public static final String EMPLOYEE_COLUMN_EXPERIENCE = "experience";
    public static final String EMPLOYEE_COLUMN_OWNER_ID = "owner_id";

    public EmployeeDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + EMPLOYEE_TABLE_NAME + " (" +
                EMPLOYEE_COLUMN_ID + " TEXT NOT NULL, " +
                EMPLOYEE_COLUMN_NAME + " TEXT NOT NULL, " +
                EMPLOYEE_COLUMN_SURNAME + " TEXT NOT NULL, " +
                EMPLOYEE_COLUMN_RATE + " TEXT, " +
                EMPLOYEE_COLUMN_PHONE + " TEXT, " +
                EMPLOYEE_COLUMN_EXPERIENCE + " TEXT, " +
                EMPLOYEE_COLUMN_OWNER_ID + " TEXT NOT NULL, " +
                "PRIMARY KEY (" + EMPLOYEE_COLUMN_ID + ")," +
                "FOREIGN KEY (" + EMPLOYEE_COLUMN_OWNER_ID + ") REFERENCES " + EMPLOYEE_TABLE_NAME + " (" + EMPLOYEE_COLUMN_ID + ")" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    boolean addEmployee(String id, String name, String surname, String rate, String phone, String experience, String owner){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_COLUMN_ID, id);
        contentValues.put(EMPLOYEE_COLUMN_NAME, name);
        contentValues.put(EMPLOYEE_COLUMN_SURNAME, surname);
        contentValues.put(EMPLOYEE_COLUMN_RATE, rate);
        contentValues.put(EMPLOYEE_COLUMN_PHONE, phone);
        contentValues.put(EMPLOYEE_COLUMN_EXPERIENCE, experience);
        contentValues.put(EMPLOYEE_COLUMN_OWNER_ID, owner);

        return sqLiteDatabase.insert(EMPLOYEE_TABLE_NAME, null, contentValues) != -1;
    }

    Cursor getAllEmployees() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + EMPLOYEE_TABLE_NAME, null);
    }

    Cursor getAllEmployees(String ownerId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + EMPLOYEE_TABLE_NAME
                + " WHERE " + EMPLOYEE_COLUMN_OWNER_ID + "=" + ownerId, null);
    }

    boolean updateEmployee(String id, String name, String surname, String rate, String phone, String experience, String owner) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_COLUMN_ID, id);
        contentValues.put(EMPLOYEE_COLUMN_NAME, name);
        contentValues.put(EMPLOYEE_COLUMN_SURNAME, surname);
        contentValues.put(EMPLOYEE_COLUMN_RATE, rate);
        contentValues.put(EMPLOYEE_COLUMN_PHONE, phone);
        contentValues.put(EMPLOYEE_COLUMN_EXPERIENCE, experience);
        contentValues.put(EMPLOYEE_COLUMN_OWNER_ID, owner);
        return sqLiteDatabase.update(EMPLOYEE_TABLE_NAME, contentValues,
                EMPLOYEE_COLUMN_ID + "=?",
                new String[] { id }) == 1;

    }

    boolean deleteEmployee(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(EMPLOYEE_TABLE_NAME,
                EMPLOYEE_COLUMN_ID + "=?" ,
                new String[] { id }) == 1;
    }
    void deleteAll() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
