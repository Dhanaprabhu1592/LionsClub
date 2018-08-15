package com.whistledevelopers.lionsclub.View;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.whistledevelopers.lionsclub.Adapter.DesignationAdapter;
import com.whistledevelopers.lionsclub.Model.Designation;
import com.whistledevelopers.lionsclub.R;

import java.util.ArrayList;

public class DesignationActivity extends AppCompatActivity {

    ListView listView;
    private ArrayList<Designation> designationArrayList;
    private DesignationAdapter designationAdapter;


    String data="";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designation);
        editor = getSharedPreferences("table", MODE_PRIVATE).edit();
       prefs = getSharedPreferences("table", MODE_PRIVATE);







        listView=(ListView)findViewById(R.id.list);

        designationArrayList=new ArrayList<Designation>();
        designationArrayList.add(new Designation("Past Dist. Governors"));
        designationArrayList.add(new Designation("Dist. Governor"));
        designationArrayList.add(new Designation("Imm Past Dist Governor"));
        designationArrayList.add(new Designation("Vice Dist Governors"));
        designationArrayList.add(new Designation("Cab.Secretary"));
        designationArrayList.add(new Designation("Cab.Treasurer"));
        designationArrayList.add(new Designation("Jt Cab Secretary"));
        designationArrayList.add(new Designation("Jt Cab Treasurer"));
        designationArrayList.add(new Designation("DG Admin Team"));
        designationArrayList.add(new Designation("Region Chairpersons"));
        designationArrayList.add(new Designation("Zone Chairpersons"));
        designationArrayList.add(new Designation("Dist Chairpersons"));
        designationArrayList.add(new Designation("President"));
        designationArrayList.add(new Designation("Secretary"));
        designationArrayList.add(new Designation("Treasurer"));
        designationArrayList.add(new Designation("1st Vice President"));
        designationArrayList.add(new Designation("Member"));

        designationAdapter=new DesignationAdapter(DesignationActivity.this, designationArrayList);
        listView.setAdapter(designationAdapter);

        listView.setTextFilterEnabled(false);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    String data="pastdist";
                    navigateMethod(data);
                }
                else if(position==1)
                {
                    String data="distgov";
                    navigateMethod(data);

                }

                else if(position==2)
                {
                    String data="immpast";
                    navigateMethod(data);

                }
                else if(position==3)
                {
                    String data="vicedist";
                    navigateMethod(data);

                }

                else if(position==4)
                {
                    data="cabsect";
                    navigateMethod(data);

                }
                else if(position==5)
                {
                    String data="cabtres";
                    navigateMethod(data);

                }

                else if(position==6)
                {
                    data="jtcabsect";
                    navigateMethod(data);

                }

                else if(position==7)
                {
                    String data="jtcabtres";
                    navigateMethod(data);

                }

                else if(position==8)
                {
                    String data="dgadmin";
                    navigateMethod(data);

                }

                else if(position==9)
                {
                    data="region";
                    navigateMethod(data);
                }

                else if(position==10)
                {
                    data="zone";
                    navigateMethod(data);

                }

                else if(position==11)
                {
                    String data="distchair";
                    navigateMethod(data);

                }

                else if(position==12)
                {
                    data="president";
                    navigateMethod(data);

                }

                else if(position==13)
                {
                    data="secretary";
                    navigateMethod(data);

                }
                else if(position==14)
                {
                    data="treasurer";
                    navigateMethod(data);

                }
                else if(position==15)
                {
                    data="firstvice";
                    navigateMethod(data);
                }
                else if(position==16)
                {
                    data="member";
                    navigateMethod(data);
                }





            }
        });







    }




    private void navigateMethod(String data) {



        editor.putString("tableName", data);

        editor.apply();

       Intent intent=new Intent(DesignationActivity.this,MembersActivity.class);
       startActivity(intent);
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


             DesignationAdapter designationAdapter= (DesignationAdapter) listView.getAdapter();
                Filter filter=designationAdapter.getFilter();
                filter.filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}
