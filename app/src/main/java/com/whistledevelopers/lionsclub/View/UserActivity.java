package com.whistledevelopers.lionsclub.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.whistledevelopers.lionsclub.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class UserActivity extends AppCompatActivity {
    Button btn_continue;
    ImageView img_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btn_continue = (Button) findViewById(R.id.btn_continue);


        /*img_bg = (ImageView) findViewById(R.id.img_lion);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.bg);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Glide.with(this)
                .load(stream.toByteArray())
               .into(img_bg);
        img_bg.setImageBitmap(bitmap);
*/
        //img_bg.setImageResource(R.drawable.bg);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(UserActivity.this, DesignationActivity.class);
                startActivity(continueIntent);
            }
        });
    }
}
