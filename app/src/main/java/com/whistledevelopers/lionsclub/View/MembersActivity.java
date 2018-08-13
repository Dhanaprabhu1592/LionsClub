package com.whistledevelopers.lionsclub.View;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.whistledevelopers.lionsclub.Adapter.MemberDataAdapter;
import com.whistledevelopers.lionsclub.Model.MemberData;
import com.whistledevelopers.lionsclub.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MembersActivity extends AppCompatActivity {

    //private static final String URL_DATA="http://192.168.43.14/lions/pastgovernors.php?keyword=";
    private static final String URL_DATA="https://test.whistledevelopers.com/CrestiveGlobal/api/pastgovernors.php?keyword=";

    List<MemberData> productList;

    RecyclerView recyclerView;

    String url;
    String name;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        recyclerView = findViewById(R.id.recylcerViewMember);
        recyclerView.setHasFixedSize(true);
        progressBar=(ProgressBar)findViewById(R.id.progressBarMember);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);







        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        productList = new ArrayList<>();

        //Getting table name
        SharedPreferences prefs = getSharedPreferences("table", MODE_PRIVATE);
        String getName = prefs.getString("tableName", null);


        url=URL_DATA+getName;

        loadProducts();




    }



    private void loadProducts()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {

                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {

                        JSONObject membersData = array.getJSONObject(i);




                        productList.add(new MemberData(
                                membersData.getString("clubname"),
                                membersData.getString("clubarea"),
                                membersData.getString("name"),
                                membersData.getString("lastname"),
                                membersData.getString("mjf"),
                                membersData.getString("mobileno")

                        ));
                    }


                    //creating adapter object and setting it to recyclerview
                    MemberDataAdapter adapter = new MemberDataAdapter(MembersActivity.this, productList);
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MembersActivity.this, "Some Thing Went Wrong...Try Again Later", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MembersActivity.this,DesignationActivity.class);
                startActivity(intent);

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem=menu.findItem(R.id.menusearch);


        SearchView searchView=(SearchView) menuItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MemberDataAdapter memberDataAdapter= (MemberDataAdapter) recyclerView.getAdapter();
                Filter filter=memberDataAdapter.getFilter();
                filter.filter(newText);
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(MembersActivity.this,DesignationActivity.class));

        return super.onSupportNavigateUp();
    }
}
