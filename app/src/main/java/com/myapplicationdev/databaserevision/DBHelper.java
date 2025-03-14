package com.myapplicationdev.databaserevision;

//on your development machine, open a browser, go to http://10.0.2.2:8080

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "notes.db";

    private static final String TABLE_NOTE = "note";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_PRIORITY = "priority";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_NOTE +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CONTENT + " TEXT,"
                + COLUMN_PRIORITY + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info" ,"SQL statement: " + createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int  newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    public void insertTask(String content, int priority){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_PRIORITY, priority);
        db.insert(TABLE_NOTE, null, values);
        db.close();
        Log.i( "InsertActivity.java", "Inserted new task Content: " + content + ", Priority: " + priority);
    }

    public ArrayList<String> getNotesInStrings() {
        ArrayList<String> tasks = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_CONTENT  + " FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String obj = cursor.toString();
                tasks.add(obj);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return tasks;
    }

    public ArrayList<Note> getNotesInObjects() {
        ArrayList<Note> notes = new ArrayList<Note>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_CONTENT + ", "
                + COLUMN_PRIORITY
                + " FROM " + TABLE_NOTE
                + " ORDER BY " + COLUMN_PRIORITY + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                //data retrieval in object
                int _id = Integer.parseInt(cursor.getString(0));
                String content = cursor.getString(1);
                String priority = cursor.getString(2);

                Note obj = new Note(_id, content, priority);
                notes.add(obj);

            } while (cursor.moveToNext());
        }
        Log.i("RetrieveActivityListView.java", "Returned notes objects");

        cursor.close();
        db.close();
        return notes;
    }

    //Edit?
    public int updateTask(Note data){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, data.getId());
        values.put(COLUMN_CONTENT, data.getContent());
        values.put(COLUMN_PRIORITY, data.getPriority());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result= db.update(TABLE_NOTE, values, condition, args);
        db.close();

        return result;
    }

    //Delete?
    public int deleteTask(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String condition =COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NOTE, condition, args);

        return result;
    }

}

