package com.example.guswn_000.a170608inclass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guswn_000 on 2017-06-08.
 */

public class MyManageDB
{
    private static MySQLiteDatabase database = null;
    private static SQLiteDatabase myDB2 = null;
    private static MyManageDB mInstance = null;

    public final static MyManageDB getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new MyManageDB(context);
        }
        return mInstance;
    }

    MyManageDB(Context context)
    {
        database = new MySQLiteDatabase(context, "myDB2", null, 1); //얘는 헬퍼
        myDB2 = database.getWritableDatabase();//openorcreate데이타베이스랑똑같은애다
    }
    public Cursor execSELECTStudents(String sql)
    {
        Cursor cursor = myDB2.rawQuery(sql,null);
        return cursor;
    }

    public void execINSERTStudent(String name, String nickname)
    {
        String sql = "Insert into students values (null,'" + name + "','" +nickname +"');";
        myDB2.execSQL(sql);
    }
}
