package com.whistledevelopers.lionsclub.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.whistledevelopers.lionsclub.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDetailActivity extends AppCompatActivity {

    TextView textUserWelcome,textUsermjf,textUserName,textUserClubname,textUserClubNo,textUserMobileNumber,textUserRule,
    textUserAddress,textUserAddress2,textUSerArea,textUSerCity,textUserEmail,textUserDcportfolio;
    Button btnContinue;
    String URL_FINAL;

    ProgressBar progressBar;
    private static final String URL_DATA="https://test.whistledevelopers.com/CrestiveGlobal/api/memberDetail.php?table=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);


        Bundle bd=getIntent().getExtras();

        String table=bd.getString("table");
        String phoneNumber=bd.getString("phonenumber");

        URL_FINAL=URL_DATA+table+"&mobileNumber="+phoneNumber;


        progressBar=(ProgressBar)findViewById(R.id.progressBarUser);

        textUserWelcome=(TextView)findViewById(R.id.text_user_welcomname);
        textUsermjf=(TextView)findViewById(R.id.text_user_mjf);
        textUserName=(TextView)findViewById(R.id.text_user_name);
        textUserClubname=(TextView)findViewById(R.id.text_user_clubname);
        textUserClubNo=(TextView)findViewById(R.id.text_user_clubno);
        textUserMobileNumber=(TextView)findViewById(R.id.text_user_mobileno);
      //  textUserRule=(TextView)findViewById(R.id.text_user_rule);
        textUserAddress=(TextView)findViewById(R.id.text_user_address1);
        textUserAddress2=(TextView)findViewById(R.id.text_user_address2);
        textUSerArea=(TextView)findViewById(R.id.text_user_area);
        textUSerCity=(TextView)findViewById(R.id.text_user_city);
        textUserEmail=(TextView)findViewById(R.id.text_user_emailid);
        textUserDcportfolio=(TextView)findViewById(R.id.text_user_dcportfolio);

        btnContinue=(Button)findViewById(R.id.btn_continue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DesignationActivity.class));
                finish();
            }
        });


        getUserData();


    }

    private void getUserData() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_FINAL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);

                    JSONObject jsonObject=jsonArray.getJSONObject(0);

                    String name=jsonObject.getString("name");
                    String lastName=jsonObject.getString("lastname");
                    String clubNo=jsonObject.getString("clubno");
                    String clubName=jsonObject.getString("clubname");
                    String clubArea=jsonObject.getString("clubarea");
                    String mobileno=jsonObject.getString("mobileno");
                    String mobileno2=jsonObject.getString("mobileno2");
                    String rule=jsonObject.getString("rule");
                    String address1=jsonObject.getString("address1");
                    String address2=jsonObject.getString("address2");
                    String area=jsonObject.getString("area");
                    String city=jsonObject.getString("city");
                    String dcPortFolio=jsonObject.getString("dcportfolio");
                    String emailid=jsonObject.getString("emailid");
                    String pincode=jsonObject.getString("pincode");
                    String mjf=jsonObject.getString("mjf");

                  //  textUserWelcome.setText("Welcome "+name);
                    textUsermjf.setText(mjf);
                    textUserName.setText("Lion "+lastName+" "+name);
                    //  textMemberLastname.setText(lastName);
                    textUserClubname.setText("LC "+clubArea+" "+clubName);
                    //textMemberClubarea.setText(clubArea);
                    textUserClubNo.setText("Club No: "+clubNo);
                    textUserMobileNumber.setText(mobileno+" , "+mobileno2);
                    //textUserRule.setText(rule);
                    textUserAddress.setText(address1);
                    textUserAddress2.setText(address2);
                    textUSerArea.setText(area);
                    textUSerCity.setText(city+" "+pincode);
                    textUserDcportfolio.setText(dcPortFolio);
                    textUserEmail.setText(emailid);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


                progressBar.setVisibility(View.GONE);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
        progressBar.setVisibility(View.VISIBLE);
    }
}
