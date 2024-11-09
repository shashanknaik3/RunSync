package com.example.runsyncproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RunsDatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RunsDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_RUNS = "runs";
    private static final String COLUMN_RUNS = "runs";
    private static final String COLUMN_OVER = "over";
    private static final String COLUMN_INNINGS = "innings";

    public RunsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RUNS_TABLE = "CREATE TABLE " + TABLE_RUNS + "(" +
                COLUMN_RUNS + " INTEGER," +
                COLUMN_OVER + " INTEGER," +
                COLUMN_INNINGS + " INTEGER" +
                ")";
        db.execSQL(CREATE_RUNS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addRuns(int runs, int over, int innings) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_RUNS, runs);
        values.put(COLUMN_OVER, over);
        values.put(COLUMN_INNINGS, innings);

        db.insert(TABLE_RUNS, null, values);
        db.close();
    }

    public Cursor getAllRunsForInnings(int innings) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_RUNS, COLUMN_OVER};
        String selection = COLUMN_INNINGS + " = ?";
        String[] selectionArgs = {String.valueOf(innings)};

        return db.query(TABLE_RUNS, columns, selection, selectionArgs, null, null, COLUMN_OVER);
    }
}