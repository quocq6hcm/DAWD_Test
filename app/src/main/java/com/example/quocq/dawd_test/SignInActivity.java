package com.example.quocq.dawd_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;

    BookHelper db = new BookHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();
    }


    public void init()
    {
        this.txtPassword = (EditText) findViewById(R.id.txtPassword);
        this.txtUsername = (EditText) findViewById(R.id.txtUsername);
    }

    public void doSignIn(View view)
    {
        Log.i("size: ", db.findAll().size() + "");

//        db.addBook(new Book(1,"C# Programming", 30, ""));
//        db.addBook(new Book(2,"Java Programming", 35, ""));
//        db.addBook(new Book(3,"SQL Server 2014", 40, ""));
        if(!TextUtils.isEmpty(txtUsername.getText().toString()) && !TextUtils.isEmpty(txtPassword.getText().toString()))
        {
            if(txtPassword.getText().toString().equals("fptaptech") && txtUsername.getText().toString().equals("a@a.com") )
            {
                startActivity(new Intent(this, ManageActivity.class));
            }
            else
                Toast.makeText(this, "Invalid!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Null field(s) remain!", Toast.LENGTH_LONG).show();

    }
}
