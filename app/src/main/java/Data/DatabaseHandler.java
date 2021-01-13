package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.contactmanager.R;

import java.util.ArrayList;
import java.util.List;

import Model.Record;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATEBASE_NAME, null, Util.DATEBASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL - Structured Query Language
        String CREATE_SCORE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," +  Util.KEY_NAME + " TEXT,"
                + Util.KEY_SCORE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void setRecord(Record score){
        SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(Util.KEY_NAME, score.getName());
       values.put(Util.KEY_SCORE, score.getScore());

        //Insert to row
        db.insert(Util.TABLE_NAME, null, values);
        db.close();
    }

    public Record getRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_SCORE}, Util.KEY_ID + "=?",
                new String[]{"id"}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Record record = new Record(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return  record;
    }

}
