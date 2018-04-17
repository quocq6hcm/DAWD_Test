package com.example.quocq.dawd_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ManageActivity extends AppCompatActivity {

    ListView lvBooks;
    ArrayAdapter<Book> lvBooksAdapter;

    BookHelper db;

    int deleteCode;

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    db.deleteBook(3);
                    finish();
                    startActivity(getIntent());

                    break;

                case DialogInterface.BUTTON_NEGATIVE:

                    break;
            }
        }
    };


    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure delete this record?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);

        init();


    }

    public void init()
    {
        db = new BookHelper(this);
        this.lvBooks = (ListView) findViewById(R.id.lvBooks);
        this.lvBooksAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, db.findAll());
        this.lvBooks.setAdapter(lvBooksAdapter);

        lvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Book b = db.findAll().get(position);
                int code = b.getCode();





                db.deleteBook(code);
                finish();
                startActivity(getIntent());
            }
        });



    }



}
