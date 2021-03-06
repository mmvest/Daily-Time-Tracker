package com.orangeplasticcup.ocuptimemanagement.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.orangeplasticcup.ocuptimemanagement.R;
import com.orangeplasticcup.ocuptimemanagement.networking.NetworkManager;
import com.orangeplasticcup.ocuptimemanagement.ui.home.main.SectionsPagerAdapter;

public class HomeScreenActivity extends AppCompatActivity {

    private static final String LOGOUT_URL = "http://66.103.121.23/api/logout.php";

    private static HomeScreenActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(Bundle.EMPTY);
        instance = this;
        setContentView(R.layout.activity_home_screen);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(instance)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                StringRequest logoutPOSTRequest = new StringRequest(Request.Method.POST, LOGOUT_URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {}
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {}
                                });

                                NetworkManager.getInstance(getApplicationContext()).addToRequestQueue(logoutPOSTRequest);
                                finish();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}