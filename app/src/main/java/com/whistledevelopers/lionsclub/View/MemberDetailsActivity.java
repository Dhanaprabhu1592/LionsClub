package com.whistledevelopers.lionsclub.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.whistledevelopers.lionsclub.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MemberDetailsActivity extends AppCompatActivity {
    private ProgressBar spinner;
    Button backButton;
    TextView textMemberWelcomeName,textMemberName,textMemberLastname,textMemberClubname,textMemberClubarea,textMemberClubno,textMemberArea,textMemberCity,
            textMemberMobileno,textMemberRule,textMemberAddress_1,textMemberAddress_2,textMemberDcportfolio,textMemberEmailid,textMemberPincode,textMemberMjf;
    String URL_FINAL;


    //private static final String URL_DATA="http://192.168.43.14/lions/memberDetail.php?table=";
    private static final String URL_DATA="https://test.whistledevelopers.com/CrestiveGlobal/api/memberDetail.php?table=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_details);

        backButton=(Button)findViewById(R.id.btn_back);
        textMemberWelcomeName=(TextView)findViewById(R.id.text_member_welcomname);
        textMemberName=(TextView)findViewById(R.id.text_member_name);
        //textMemberLastname=(TextView)findViewById(R.id.text_member_lastname);
        textMemberClubname=(TextView)findViewById(R.id.text_member_clubname);
        //textMemberClubarea=(TextView)findViewById(R.id.text_member_clubarea);
        textMemberMobileno=(TextView)findViewById(R.id.text_member_mobileno);
       // textMemberRule=(TextView)findViewById(R.id.text_member_rule);
        textMemberAddress_1=(TextView)findViewById(R.id.text_member_address1);
        textMemberAddress_2=(TextView)findViewById(R.id.text_member_address2);
        textMemberClubno=(TextView)findViewById(R.id.text_member_clubno);
        textMemberArea=(TextView)findViewById(R.id.text_member_area);
        textMemberCity=(TextView)findViewById(R.id.text_member_city);
        textMemberDcportfolio=(TextView)findViewById(R.id.text_member_dcportfolio);
        textMemberEmailid=(TextView)findViewById(R.id.text_member_emailid);
        //textMemberPincode=(TextView)findViewById(R.id.text_member_pincode);
        textMemberMjf=(TextView)findViewById(R.id.text_mjf);

        spinner = (ProgressBar)findViewById(R.id.progressBar);

        SharedPreferences prefs = getSharedPreferences("table", MODE_PRIVATE);
        String tableName = prefs.getString("tableName", null);



        Bundle data=getIntent().getExtras();

        String mobileNo=data.getString("mobileNumber");

        URL_FINAL=URL_DATA+tableName+"&mobileNumber="+mobileNo;

        loadMemberDeatils();


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberDetailsActivity.this,MembersActivity.class));
                finish();
            }
        });








    }

    private void loadMemberDeatils() {

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

                   // textMemberWelcomeName.setText("Welcome "+name);
                    textMemberMjf.setText(mjf);
                    textMemberName.setText("Lion "+lastName+" "+name);
                  //  textMemberLastname.setText(lastName);
                    textMemberClubname.setText("LC "+clubArea+" "+clubName);
                    //textMemberClubarea.setText(clubArea);
                    textMemberClubno.setText("Club No: "+clubNo);
                    textMemberMobileno.setText(mobileno+" , "+mobileno2);
                    //textMemberRule.setText(rule);
                    textMemberAddress_1.setText(address1);
                    textMemberAddress_2.setText(address2);
                    textMemberArea.setText(area);
                    textMemberCity.setText(city+" "+pincode);
                    textMemberDcportfolio.setText(dcPortFolio);
                    textMemberEmailid.setText(emailid);
                   // textMemberPincode.setText(pincode);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


                spinner.setVisibility(View.GONE);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                spinner.setVisibility(View.GONE);

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
        spinner.setVisibility(View.VISIBLE);


    }
}
