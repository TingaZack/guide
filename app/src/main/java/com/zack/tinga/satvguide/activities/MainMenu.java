package com.zack.tinga.satvguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.zack.tinga.satvguide.MainActivity;
import com.zack.tinga.satvguide.R;
import com.zack.tinga.satvguide.fragments.Etv_Fragment;
import com.zack.tinga.satvguide.fragments.SABC_1_Fragment;
import com.zack.tinga.satvguide.fragments.SABC_2_Fragment;
import com.zack.tinga.satvguide.fragments.SABC_3_Fragment;

public class MainMenu extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_s1:
                    fragmentTransaction.replace(R.id.content, new SABC_1_Fragment()).commit();
                    return true;
                case R.id.navigation_s2:
                    fragmentTransaction.replace(R.id.content, new SABC_2_Fragment()).commit();
                    return true;
                case R.id.navigation_s3:
                    fragmentTransaction.replace(R.id.content, new SABC_3_Fragment()).commit();
                    return true;
                case R.id.navigation_etv:
                    fragmentTransaction.replace(R.id.content, new Etv_Fragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.content, new SABC_1_Fragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
