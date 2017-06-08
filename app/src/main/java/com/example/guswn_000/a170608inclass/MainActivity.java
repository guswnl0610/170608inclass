package com.example.guswn_000.a170608inclass;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    SQLiteDatabase database = null;
    EditText et,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.etdbname);
        et2 = (EditText)findViewById(R.id.etData);
    }


    public void onClick(View v)
    {
        if(v.getId() == R.id.btncreatedb)
        {
            if(et.getText().toString().equals(""))
            {
                Toast.makeText(this,"DB이름을 넣으세요",Toast.LENGTH_SHORT).show();
                et.requestFocus();
                return;
            }
            database = openOrCreateDatabase(et.getText().toString(), MODE_PRIVATE,null);
            if(database == null)
            {
                Toast.makeText(this,"DB생성에 실패",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"DB생성에 성공",Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.btndeletedb)
        {
            boolean delete = deleteDatabase(et.getText().toString());
            if(delete)
            {
                toastShow("DB 삭제 성공");
                database = null;
            }
            else
            {
                toastShow("DB 삭제 실패");
            }
        }
        else if(v.getId() == R.id.btncreateTable)
        {
//            create table if not exist students(
//                 id integer primary key autoincrement,
//                 name text not null,
//                 hakno text //계산해야할 필요가 있는애들만 숫자 그렇지않으면 텍스트
//
           String sql = "Create table students (" +
                   "id integer PRIMARY KEY autoincrement," +
                   "name text not null," +
                   "hakno text);";
            try
            {
                database.execSQL(sql);
                toastShow("students 테이블 생성");
            }
            catch (SQLiteException e)
            {
                toastShow("이미 students 테이블이 있습니다.");
            }
        }
        else if (v.getId() == R.id.btninsertdata)
        {
            String sql1 = "Insert into students values (null,'김현지',2015038259);";
            String sql2 = "Insert into students values (null,'최상아',2015038668);";
            String sql3 = "Insert into students values (null,'박남주',2015044566);";
            try
            {
                database.execSQL(sql1);
                database.execSQL(sql2);
                database.execSQL(sql3);
            }
            catch (SQLiteException e)
            {
                toastShow("SQL에러" + e.getMessage());
            }

        }
        else if (v.getId() == R.id.btnselectdata)
        {
            String sql1 = "Select * from students order by id;";
            try
            {
                Cursor recordset = database.rawQuery(sql1,null);
                recordset.moveToFirst();
                String str = "";
                do
                {
                    str += recordset.getInt(0) + "/" + recordset.getString(1) + "/" + recordset.getString(2)+"\n";
                }
                while (recordset.moveToNext());
                recordset.close();
                et2.setText(str);
                toastShow("데이터가 조회되었습니다.");
            }
            catch (SQLiteException e)
            {
                toastShow("에러모르겠다" + e.getMessage());
            }
        }
        else if(v.getId() == R.id.button2)
        {
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }


    }

    public void toastShow(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
