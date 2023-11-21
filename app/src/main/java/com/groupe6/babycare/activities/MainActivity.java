package com.groupe6.babycare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.fragments.ActivitiesFragment;
import com.groupe6.babycare.activities.fragments.ChartsFragment;
import com.groupe6.babycare.activities.fragments.ChildrenFragment;
import com.groupe6.babycare.activities.fragments.DiaperFragment;
import com.groupe6.babycare.activities.fragments.FeedingFragment;
import com.groupe6.babycare.activities.fragments.HealthcareFragment;
import com.groupe6.babycare.activities.fragments.HomeFragment;
import com.groupe6.babycare.activities.fragments.NotesFragment;
import com.groupe6.babycare.activities.fragments.SleepingFragment;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        initNavDrawer(savedInstanceState);

    }

    private void initNavDrawer(Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_nav,
                R.string.close_nav
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new HomeFragment()
            ).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new HomeFragment()
            ).commit();
        } else if (item.getItemId() == R.id.nav_children) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new ChildrenFragment()
            ).commit();
        }
        else if (item.getItemId() == R.id.nav_feeding) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new FeedingFragment()
            ).commit();
        }
        else if (item.getItemId() == R.id.nav_diaper) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new DiaperFragment()
            ).commit();
        }
        else if (item.getItemId() == R.id.nav_sleeping) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new SleepingFragment()
            ).commit();
        }
        else if (item.getItemId() == R.id.nav_activities) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new ActivitiesFragment()
            ).commit();
        }
        else if (item.getItemId() == R.id.nav_healthcare) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new HealthcareFragment()
            ).commit();
        }
        else if (item.getItemId() == R.id.nav_notes) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new NotesFragment()
            ).commit();
        }
        else if (item.getItemId() == R.id.nav_charts) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new ChartsFragment()
            ).commit();
        } else {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
       drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}