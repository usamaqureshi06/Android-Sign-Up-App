package com.example.quras.king;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper helper = new DataBaseHelper(this);

    private EditText username;
    private EditText password;

 // just to check
   // String User;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

   public void onButtonClicked(View view)
   {
       if(view.getId()==R.id.log) /// log in page
       {

           username = (EditText) findViewById(R.id.u1);
           String u = username.getText().toString();
           password = (EditText) findViewById(R.id.p1);
           String p = password.getText().toString();


           String check =helper.search(u);

           if(check.equals(p)) {
               Intent i = new Intent(MainActivity.this, Result.class);
               i.putExtra("YourValueKey", username.getText().toString());
               startActivity(i);

           }
           else
           {
               Toast t= Toast.makeText(MainActivity.this,"User NOT found!!",Toast.LENGTH_SHORT);
               t.show();
           }



       }
       if(view.getId()==R.id.sub) /// sign up page
       {
           Intent i= new Intent(MainActivity.this,First.class);
           startActivity(i);

       }


   }

   //just to check

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
