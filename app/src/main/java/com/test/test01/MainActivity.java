package com.test.test01;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText ageText = (EditText) findViewById(R.id.editAge);

        Button entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age= ageText.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("name",name);
                insertValues.put("age",age);
                long id = db.insert("person", name, insertValues);
            }
        });

        Button updataButton = (Button) findViewById(R.id.update);
        updataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                if (name.equals("")){
                    Toast.makeText(MainActivity.this,"名前を入力して下さい。",
                            Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("age",age);
                    db.update("person", updateValues, "name=?", new String[] { name });
                }
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                if (name.equals("")){
                    Toast.makeText(MainActivity.this, "名前を入力して下さい。",
                            Toast.LENGTH_SHORT).show();
                }else{
                    db.delete("person", "name=?", new String[] { name });
                }
            }
        });

        Button deleteAllButton = (Button) findViewById(R.id.deleteAll);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                db.delete("person", null, null);

            }
        });

        Button dataBaseButton = (Button) findViewById(R.id.dataBase);
        dataBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(MainActivity.this, ShowDataBase.class);
                startActivity(dbIntent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
