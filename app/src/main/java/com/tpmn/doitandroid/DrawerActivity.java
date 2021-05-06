package com.tpmn.doitandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
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

public class DrawerActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private TabLayout tabs;
    private Toolbar toolBar;
    private NavigationView navigationView;
    private Fragment[] fragments = {OneFragment.newInstance(), TwoFragment.newInstance(), ThreeFragment.newInstance()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setup();
    }

    private void setup() {
        setupViews();
        setupTabs();
        setupFragments();
        setupNavigation();
        setupTitle();
    }

    private void setupViews() {
        drawer = findViewById(R.id.drawer);
        tabs = findViewById(R.id.tabs);
        navigationView = findViewById(R.id.navigationView);
        toolBar = findViewById(R.id.toolBar);
    }

    private void setupTabs() {
        tabs.addTab(tabs.newTab().setText(getString(R.string.first)));
        tabs.addTab(tabs.newTab().setText(getString(R.string.second)));
        tabs.addTab(tabs.newTab().setText(getString(R.string.third)));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0: changeFragment(0); break;
                    case 1: changeFragment(1); break;
                   case 2: changeFragment(2); break;
                    default:
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void setupFragments() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragments[0])
                .commit();
    }

    private void changeFragment(int index) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragments[index])
                .commit();
    }

    private void setupTitle() {
        toolBar.setTitle(getString(R.string.first));
    }

    @SuppressLint("NonConstantResourceId")
    private void setupNavigation() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            String msg = "";
            switch (item.getItemId()) {
                case R.id.first:  msg = "First Menu";  break;
                case R.id.second: msg = "Second Menu"; break;
                case R.id.third:  msg = "Third Menu";  break;
            }
            Toast.makeText(DrawerActivity.this, msg, Toast.LENGTH_SHORT).show();
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

}