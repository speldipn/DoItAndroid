package com.tpmn.doitandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

/**
 * <CoordinateLayout>
 *   <AppbarLayout>
 *     <Toolbar>
 *       <TextView /> : Toolbar title
 *     </Toolbar>
 *     <TabLayout/>
 *   </AppbarLayout>
 *   <FrameLayout/>
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

    private void setup(){
        toolBar = findViewById(R.id.toolBar);
        tabs = findViewById(R.id.tabs);
        container = findViewById(R.id.container);
        titleTextView = findViewById(R.id.titleTextView);

        tabs.addTab(tabs.newTab().setText("First"));
        tabs.addTab(tabs.newTab().setText("Second"));
        tabs.addTab(tabs.newTab().setText("Third"));
    }
}