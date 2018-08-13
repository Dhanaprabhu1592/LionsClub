package com.whistledevelopers.lionsclub.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whistledevelopers.lionsclub.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

             networkDetect();


           }
       },3000);


    }

  private void networkDetect() {

      ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
      if (activeNetwork != null) {
          if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
              Intent intent=new Intent(SplashActivity.this,UserActivity.class);
              startActivity(intent);
              finish();

          } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
              Intent intent=new Intent(SplashActivity.this,UserActivity.class);
              startActivity(intent);
              finish();

          }
      } else {
          AlertDialog.Builder builder=new AlertDialog.Builder(this);

          builder.setCancelable(true)
                  .setMessage("Please turn on your network connection")
                  .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                          finish();

                      }
                  });

          AlertDialog alertDialog=builder.create();
          alertDialog.setTitle("Alert");
          alertDialog.show();

      }
    }
}
