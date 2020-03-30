package com.example.quras.king;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";
    private static final String TABLE_NAME="contacts";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_USERNAME="username";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_PASS="password";

    SQLiteDatabase db;

    private static final String CREATE_TABLE="create table "+TABLE_NAME+ "( "+COLUMN_ID+" integer primary key not null , "+
            COLUMN_NAME+" text not null , "+COLUMN_EMAIL +" text not null , "+COLUMN_USERNAME+" text not null , "+COLUMN_PASS+" text not null );";

    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query =" DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


    public void insert(Contact c)
    {
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query="Select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        int count = cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_USERNAME,c.getUname());
        values.put(COLUMN_PASS,c.getPass());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public String search(String unm)
    {
        db=this.getReadableDatabase();
        String query = " select "+ COLUMN_USERNAME + " , "+COLUMN_PASS+" from "+TABLE_NAME;
        Cursor c = db.rawQuery(query,null);

        String a,b;
        b="nothing";
        if(c.moveToFirst())
        {
            do{
              a=c.getString(0);
              if(a.equals(unm))
              {
                  b=c.getString(1);
                  break;
              }

            }while(c.moveToNext());
        }
        return b;

    }
}
