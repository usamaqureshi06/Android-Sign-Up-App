package com.example.quras.king;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.support.v7.app.AppCompatActivity;

public class First extends AppCompatActivity{


    DataBaseHelper helper= new DataBaseHelper(this);

    private EditText name;
    private EditText username;
    private EditText email1;
    private EditText password;
    private EditText confirm;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);


    }

    public void onButtonClicked(View view) {

      /// checking the status if they are equal or not.


            if (view.getId() == R.id.sup) {

                password = (EditText) findViewById(R.id.pass);
                confirm = (EditText) findViewById(R.id.cpass);
                name=(EditText)findViewById(R.id.nam);
                username=(EditText)findViewById(R.id.unam);
                email1=(EditText)findViewById(R.id.email);
                String pass = password.getText().toString();
                String cp = confirm.getText().toString();
                String em = email1.getText().toString();
                String us = username.getText().toString();
                String nam = name.getText().toString();
                if (pass.equals(cp)) {


                    Contact c = new Contact();
                    c.setName(nam);
                    c.setPass(pass);
                    c.setEmail(em);
                    c.setUname(us);

                    helper.insert(c);

                    Intent i = new Intent(First.this, MainActivity.class);
                    startActivity(i);

                }


                else                    // passwords are not same.
                {
                    openDialogue();
                }


            }

            }


    public void openDialogue()
    {
       Dialogue dialogue = new Dialogue();
       dialogue.show(getSupportFragmentManager(),"Example Dialog");

    }
}
