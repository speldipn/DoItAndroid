package com.tpmn.doitandroid;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class DrawerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    OneFragment oneFragment = new OneFragment();
    TwoFragment twoFragment = new TwoFragment();
    ThreeFragment threeFragment = new ThreeFragment();
    TabLayout tabLayout;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, oneFragment, "one")
                .commit();

        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment nextFragment = null;
        switch (tab.getPosition()) {
            case 0:
                nextFragment = oneFragment;
                break;
            case 1:
                nextFragment = twoFragment;
                break;
            case 2:
                nextFragment = threeFragment;
                break;
        }
        if (nextFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, nextFragment)
                    .commit();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String selectedItemName = "";
        switch (item.getItemId()) {
            case R.id.menu_search:
                selectedItemName = "Search";
                break;
            case R.id.menu_settings:
                selectedItemName = "Settings";
                break;
            case R.id.menu_refresh:
                selectedItemName = "Refresh";
                break;
        }
        Toast.makeText(this, selectedItemName, Toast.LENGTH_SHORT).show();
        return true;
    }
}