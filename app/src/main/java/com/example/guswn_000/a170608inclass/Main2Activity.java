package com.example.guswn_000.a170608inclass;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity
{
    EditText et1,et2;
    ListView listView;
    MyManageDB manageDB;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView)findViewById(R.id.etData);
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        manageDB = MyManageDB.getInstance(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }


    public void onClick(View v)
    {
        if(v.getId() == R.id.button)
        {
            manageDB.execINSERTStudent(et1.getText().toString(),et2.getText().toString());
        }
        else if (v.getId() == R.id.btnselectdata)
        {
            data.clear();
            String sql = "Select * from students order by id;";
            Cursor cursor = manageDB.execSELECTStudents(sql);
            cursor.moveToFirst();
            do
            {
                String str = "";
                str += cursor.getInt(0) + ":" + cursor.getString(1) + ":" + cursor.getString(2);
                data.add(str);
                adapter.notifyDataSetChanged();
            }
            while (cursor.moveToNext());
            cursor.close();

        }
    }

    public void showToast(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
