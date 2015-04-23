package com.example.restclients2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by NSG1 on 2/20/2015.
 */


public class DBAdapter {
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public static final String KEY_ROWID = "_id";
    private static final String DATABASE_NAME = "Restclients";
    private static final String DATABASE_TABLE = "url";
    private static final int DATABASE_VERSION = 6;
    private static final String TAG = "DBAdapter";
    public static final String KEY_URL = "urlname";
    public static final String KEY_URLADDRESS = "urladdress";
    private static final String DATABASE_TABLEKV = "urlkv";
    public static final String KEY_URLKEY = "urlkey";
    public static final String KEY_VALUE = "urlvalue";
   public static final String KEY_FROWID = "_fid";
    public static final String FK_ID = "kv_id";


    private static final String DATABASE_CREATE =
            "create table url(_id integer primary key autoincrement,urlname text not null, "
                    + "urladdress text not null);";

//    private static final String DATABASE_CREATEKV =
//            "create table urlkv(_fid integer primary key autoincrement,kv_id integer,urlkey text not null, "
//                    + "urlvalue text not null,foreign key(kv_id) references url(_id));";

    private static final String DATABASE_CREATEKV =
            "create table urlkv(_fid integer primary key autoincrement,urlkey text not null, "
                    + "urlvalue text not null,kv_id integer);";

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);//SQLiteOpenHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        } // SQLiteDatabase.CursorFactory is null by default
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                db.execSQL(DATABASE_CREATE);
                db.execSQL(DATABASE_CREATEKV);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS url");
           // db.execSQL("DROP TABLE IF EXISTS urlkv");
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS url");
           // db.execSQL("DROP TABLE IF EXISTS urlkv");
            onCreate(db);
        }
    }


    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
//        if (!db.isReadOnly()) {
//            // Enable foreign key constraints
//            db.execSQL("PRAGMA foreign_keys=ON;");
//        }
        return this;
    }
    //---closes the database---
    public void close()
    {
        DBHelper.close();


    }
    public long insertContact(String url, String urladdress)
    {
        db = DBHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_URL, url);
        initialValues.put(KEY_URLADDRESS, urladdress);
      //  initialValues.put(KEY_PHONE, phone);
        return db.insert(DATABASE_TABLE, null, initialValues); // 2nd parameter -  null indicates all column values.
    }
    public long insertContact1(String key, String value,int id)
    {
        db = DBHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_URLKEY, key);
        initialValues.put(KEY_VALUE, value);
        initialValues.put(FK_ID, id);
        //  initialValues.put(KEY_PHONE, phone);
        return db.insert(DATABASE_TABLEKV, null, initialValues); // 2nd parameter -  null indicates all column values.
    }

    //---deletes a particular contact---
    public boolean deleteContact(int rowId)
    {
        db = DBHelper.getWritableDatabase();
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0; // relational operator returns true / false

    }
    public boolean deleteContactkv(int rowId)
    {
        db = DBHelper.getWritableDatabase();
        return db.delete(DATABASE_TABLEKV, FK_ID + "=" + rowId, null) > 0; // relational operator returns true / false
    }
    public  int  getid(String faiz)
    {

        db = DBHelper.getWritableDatabase();


        String url=faiz;



        Cursor cursor1=   db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_URL,KEY_URLADDRESS},KEY_URL + "=?",new String[]{url} , null,null,null);
        if (cursor1 != null) {
            cursor1.moveToFirst();
        }
         int index=  cursor1.getColumnIndex(DBAdapter.KEY_ROWID);

       int id= cursor1.getInt(index);

        return id;

        }

    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        Cursor cursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_URL,KEY_URLADDRESS}, null, null, null, null, null);
      /*  if (cursor != null) {
            cursor.moveToFirst();
        }*/
        return cursor;
    }
  /*  public Cursor getContact(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLEKV, new String[] {KEY_FROWID,
                                KEY_URLKEY,KEY_VALUE}, FK_ID + "=" + rowId, null,
                        null, null, null, null);

        return mCursor;
    }*/

    //---retrieves a particular contact---
    public Cursor getContactkv(int rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLEKV, new String[] {KEY_URLKEY,
                                KEY_VALUE},FK_ID + "=" + rowId, null,
                        null, null, null, null);
       /* if (mCursor != null) {
            mCursor.moveToFirst();
        }*/
        return mCursor;
    }

    //---updates a contact---
    public boolean updateContact(int rowId, String url, String urladdress)
    {
        db = DBHelper.getWritableDatabase();

        ContentValues args = new ContentValues();
        args.put(KEY_URL,url);
        args.put(KEY_URLADDRESS,urladdress);

        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
   /* public boolean updateContact(int rowId, String key, String value)
    {
        db = DBHelper.getWritableDatabase();

        ContentValues args = new ContentValues();
        args.put(KEY_URLKEY,key);
        args.put(KEY_VALUE,value);

        return db.update(DATABASE_TABLE, args, FK_ID + "=" + rowId, null) > 0;
    }*/
}
