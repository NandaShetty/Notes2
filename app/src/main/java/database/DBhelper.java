package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Notes;

import static database.LoginContract.LoginEntry.COLUMN_CATEGORY;
import static database.LoginContract.LoginEntry.COLUMN_CONTENT;
import static database.LoginContract.LoginEntry.COLUMN_DATE;
import static database.LoginContract.LoginEntry.COLUMN_IS_ALARM;
import static database.LoginContract.LoginEntry.COLUMN_REMINDER;
import static database.LoginContract.LoginEntry.COLUMN_TITLE;
import static database.LoginContract.LoginEntry.TABLE_NAME;
import static database.LoginContract.LoginEntry._ID;

/**
 * Created by Nanda devi shetty b on 30-11-2017.
 */

public class DBhelper extends SQLiteOpenHelper {

    private static final int Db_Version = 27;
    private static final String Db_Name = "users";
    private static final String Table_Name = "login";

    SQLiteDatabase db;


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TITLE + " TEXT," +
                    COLUMN_CONTENT + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_CATEGORY + " TEXT," +
                    COLUMN_REMINDER + " TEXT," +
                    COLUMN_IS_ALARM + " INTEGER)";


    private static final String SQL_DELETE_ALL_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBhelper(Context context) {

        super(context, Db_Name, null, Db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ALL_ENTRIES);
        onCreate(db);
    }


    public void addUser(Notes usr) {

        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, usr.getName());
        cv.put(COLUMN_CONTENT, usr.getPassword());
        cv.put(COLUMN_DATE, new Date().toString());
        cv.put(COLUMN_CATEGORY, usr.getCategory());
        cv.put(COLUMN_REMINDER, new Date().getTime());
        cv.put(COLUMN_IS_ALARM, usr.getAlarm());

        db.insert(Table_Name, null, cv);
        db.close();

    }

    public List<Notes> displayAll() {
        List<Notes> notesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Table_Name;

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Notes notes = new Notes();
                notes.set_ID(cursor.getInt(0));
                notes.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                notes.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT)));
                notes.setDateCreated(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                notes.setAlarmDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REMINDER)));
                notes.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)));
                notes.setAlarm(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ALARM)));

                notesList.add(notes);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return notesList;
    }

    public void deleteEntry(int _id) {
        db = this.getWritableDatabase();
        db.delete(Table_Name, _ID + "=" + _id, null);
        db.close();
    }

    public int updateNote(int _id, String name, String content, String type) {
        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, name);
        cv.put(COLUMN_CONTENT, content);
        cv.put(COLUMN_CATEGORY, type);

        return db.update(Table_Name, cv, LoginContract.LoginEntry._ID + "=" + _id,
                null);
    }
}