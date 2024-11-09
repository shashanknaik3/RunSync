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

public class BolwerDataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BowlerDBSQL";

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BOWLER = "bowlerdb";


    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_OVERS = "overs";

    private static final String COLUMN_RUNS = "runs";
    private static final String COLUMN_BALLS = "balls";
    private static final String COLUMN_WICKETS = "wickets";
    private static final String COLUMN_MAIDENS = "maidens";

    private Context context;

    public BolwerDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BATSMAN = "CREATE TABLE " + TABLE_BOWLER + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_OVERS + " INTEGER," +
                COLUMN_BALLS + " INTEGER," +
                COLUMN_RUNS + " INTEGER," +
                COLUMN_WICKETS + " INTEGER," +
                COLUMN_MAIDENS + " INTEGER" +
                ")";
        db.execSQL(CREATE_TABLE_BATSMAN);

//        Toast.makeText(context, "Bowler table created successfully!", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addBolwer(Bowler bowler) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, bowler.getName());
        values.put(COLUMN_OVERS, bowler.getOvers());
        values.put(COLUMN_BALLS, bowler.getBalls());
        values.put(COLUMN_RUNS,bowler.getRuns());
        values.put(COLUMN_WICKETS, bowler.getWickets());
        values.put(COLUMN_MAIDENS, bowler.getMaidens());

        try {
            long newRowId = db.insert(TABLE_BOWLER, null, values);
            if (newRowId != -1) {
//                Toast.makeText(context, "Bowler data saved successfully!", Toast.LENGTH_SHORT).show();

                Log.d("Database", "New Bowler added with ID: " + newRowId);
            } else {
                Toast.makeText(context, "Failed to save bowler data!", Toast.LENGTH_SHORT).show();

                Log.e("Database", "Error while saving bolwers data.");
            }
        } catch (Exception e) {

            e.printStackTrace();

            Log.e("Database", "Exception while adding bowlers data: " + e.getMessage());
        } finally {
            db.close();
        }
    }

    public List<String> getAllUniqueBowlerNames() {
        List<String> bowlerNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + COLUMN_NAME + " FROM " + TABLE_BOWLER, null);

        if (cursor.moveToFirst()) {
            do {
                String bowlerName = cursor.getString(0);
                bowlerNames.add(bowlerName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bowlerNames;
    }


    public Bowler getBowlerData(String bowlerName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                COLUMN_NAME,
                COLUMN_OVERS,
                COLUMN_BALLS,
                COLUMN_RUNS,
                COLUMN_WICKETS,
                COLUMN_MAIDENS
        };
        String selection = COLUMN_NAME + " = ?";
        String[] selectionArgs = { bowlerName };

        Cursor cursor = db.query(
                TABLE_BOWLER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Bowler bowler = null;
        if (cursor.moveToFirst()) {
            bowler = new Bowler();
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int oversIndex = cursor.getColumnIndex(COLUMN_OVERS);
            int ballsIndex = cursor.getColumnIndex(COLUMN_BALLS);
            int runsIndex = cursor.getColumnIndex(COLUMN_RUNS);
            int wicketsIndex = cursor.getColumnIndex(COLUMN_WICKETS);
            int maidensIndex = cursor.getColumnIndex(COLUMN_MAIDENS);

            if (nameIndex != -1) {
                bowler.setName(cursor.getString(nameIndex));
            }
            if (oversIndex != -1) {
                bowler.setOvers(cursor.getInt(oversIndex));
            }
            if (ballsIndex != -1) {
                bowler.setBalls(cursor.getInt(ballsIndex));
            }
            if (runsIndex != -1) {
                bowler.setRuns(cursor.getInt(runsIndex));
            }
            if (wicketsIndex != -1) {
                bowler.setWickets(cursor.getInt(wicketsIndex));
            }
            if (maidensIndex != -1) {
                bowler.setMaidens(cursor.getInt(maidensIndex));
            }
        }
        cursor.close();
        return bowler;
    }


    public int updateBowler(Bowler bowler) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_OVERS, bowler.getOvers());
        values.put(COLUMN_BALLS, bowler.getBalls());
        values.put(COLUMN_RUNS, bowler.getRuns());
        values.put(COLUMN_WICKETS, bowler.getWickets());
        values.put(COLUMN_MAIDENS, bowler.getMaidens());

        String selection = COLUMN_NAME + " = ?";
        String[] selectionArgs = { bowler.getName() };

        return db.update(TABLE_BOWLER, values, selection, selectionArgs);
    }




}
