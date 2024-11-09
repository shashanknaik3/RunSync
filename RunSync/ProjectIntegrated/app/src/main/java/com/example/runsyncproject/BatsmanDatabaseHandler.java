package com.example.runsyncproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BatsmanDatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BatsmanDBSQL";

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BATSMAN = "batsmandb";


    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_RUNS = "runs";
    private static final String COLUMN_BALLS = "balls";
    private static final String COLUMN_FOUR = "four";
    private static final String COLUMN_SIX = "six";

    private Context context;

    public BatsmanDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BATSMAN = "CREATE TABLE " + TABLE_BATSMAN + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_RUNS + " INTEGER," +
                COLUMN_BALLS + " INTEGER," +
                COLUMN_FOUR + " INTEGER," +
                COLUMN_SIX + " INTEGER" +
                ")";
        db.execSQL(CREATE_TABLE_BATSMAN);
        Log.d("Database", "Batsman table created successfully");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade strategy for a new database version (if needed).
    }

    public void addBatsman(Batsman batsman) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, batsman.getName());
        values.put(COLUMN_RUNS, batsman.getRuns());
        values.put(COLUMN_BALLS, batsman.getBalls());
        values.put(COLUMN_FOUR, batsman.getFour());
        values.put(COLUMN_SIX, batsman.getSix());

        try {
            long newRowId = db.insert(TABLE_BATSMAN, null, values);
            if (newRowId != -1) {
                // Log a success message
                Log.d("Database", "New Batsman added with ID: " + newRowId);
            } else {
                Toast.makeText(context, "Failed to save batsman data!", Toast.LENGTH_SHORT).show();
                // Log an error message
                Log.e("Database", "Error inserting new batsman.");
            }
        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
            // Log the exception
            Log.e("Database", "Exception while adding batsman: " + e.getMessage());
        } finally {
            db.close();
        }
    }


    public List<Batsman> getAllBatsmen() {
        List<Batsman> batsmenList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BATSMAN, null);

        if (cursor.moveToFirst()) {
            do {
                Batsman batsman = new Batsman();
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int runsIndex = cursor.getColumnIndex(COLUMN_RUNS);
                int ballsIndex = cursor.getColumnIndex(COLUMN_BALLS);
                int fourIndex = cursor.getColumnIndex(COLUMN_FOUR);
                int sixIndex = cursor.getColumnIndex(COLUMN_SIX);

                if (nameIndex >= 0) {
                    batsman.setName(cursor.getString(nameIndex));
                }

                if (runsIndex >= 0) {
                    batsman.setRuns(cursor.getInt(runsIndex));
                }

                if (ballsIndex >= 0) {
                    batsman.setBalls(cursor.getInt(ballsIndex));
                }

                if (fourIndex >= 0) {
                    batsman.setFour(cursor.getInt(fourIndex));
                }

                if (sixIndex >= 0) {
                    batsman.setSix(cursor.getInt(sixIndex));
                }

                batsmenList.add(batsman);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return batsmenList;
    }



    public List<String> getAllUniqueBatsmanNames() {
        List<String> batsmanNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + COLUMN_NAME + " FROM " + TABLE_BATSMAN, null);

        if (cursor.moveToFirst()) {
            do {
                String batsmanName = cursor.getString(0);
                batsmanNames.add(batsmanName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return batsmanNames;
    }


    public Batsman getBatsmanByName(String batsmanName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_NAME, COLUMN_RUNS, COLUMN_BALLS, COLUMN_FOUR, COLUMN_SIX};
        String selection = COLUMN_NAME + " = ?";
        String[] selectionArgs = {batsmanName};

        Cursor cursor = db.query(TABLE_BATSMAN, projection, selection, selectionArgs, null, null, null);

        Batsman batsman = new Batsman();
        if (cursor.moveToFirst()) {
            int runsIndex = cursor.getColumnIndex(COLUMN_RUNS);
            int ballsIndex = cursor.getColumnIndex(COLUMN_BALLS);
            int fourIndex = cursor.getColumnIndex(COLUMN_FOUR);
            int sixIndex = cursor.getColumnIndex(COLUMN_SIX);

            batsman.setName(batsmanName);

            if (runsIndex >= 0) {
                batsman.setRuns(cursor.getInt(runsIndex));
            }

            if (ballsIndex >= 0) {
                batsman.setBalls(cursor.getInt(ballsIndex));
            }

            if (fourIndex >= 0) {
                batsman.setFour(cursor.getInt(fourIndex));
            }

            if (sixIndex >= 0) {
                batsman.setSix(cursor.getInt(sixIndex));
            }
        }

        cursor.close();
        return batsman;
    }


}
