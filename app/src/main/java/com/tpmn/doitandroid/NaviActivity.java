package com.tpmn.doitandroid;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.tpmn.doitandroid.R;

/**
 * <CoordinateLayout>
 * <AppbarLayout>
 * <Toolbar>
 * <TextView /> : Toolbar title
 * </Toolbar>
 * <TabLayout/>
 * </AppbarLayout>
 * <FrameLayout/>
 * </CoordinateLayout>
 */

public class NaviActivity extends AppCompatActivity {

    Toolbar toolBar;
    TabLayout tabs;
    FrameLayout container;
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        setup();
    }

    private void setup() {
        toolBar = findViewById(R.id.toolBar);
        tabs = findViewById(R.id.tabs);
        container = findViewById(R.id.container);
        titleTextView = findViewById(R.id.titleTextView);

        tabs.addTab(tabs.newTab().setText("First"));
        tabs.addTab(tabs.newTab().setText("Second"));
        tabs.addTab(tabs.newTab().setText("Third"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String msg;
                if (tab.getText().equals("First")) {
                    msg = "First";
                } else if (tab.getText().equals("Second")) {
                    msg = "Second";
                } else {
                    msg = "Third";
                }
                Toast.makeText(NaviActivity.this, msg + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }
}
