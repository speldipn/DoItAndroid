package com.tpmn.doitandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.tpmn.doitandroid.OneFragment;

public class PracticeActivity extends AppCompatActivity {

    public static final String TAG = "speldipn";

    private FragmentContainerView container;
    private Button oneButton, twoButton, threeButton;
    private List<Fragment> fragments = new ArrayList<>();
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        setup();
        Log.d(TAG, "onCreate called by PracticeActivity");
    }

    private void setup() {
        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);

        fragments.add(OneFragment.newInstance());
        fragments.add(TwoFragment.newInstance());
        fragments.add(ThreeFragment.newInstance());

        View.OnClickListener clickListener = v -> {
            switch (v.getId()) {
                case R.id.oneButton:
                    selectFragment(0);
                    break;
                case R.id.twoButton:
                    selectFragment(1);
                    break;
                case R.id.threeButton:
                    selectFragment(2);
                    break;
            }
        };

        oneButton.setOnClickListener(clickListener);
        twoButton.setOnClickListener(clickListener);
        threeButton.setOnClickListener(clickListener);

        pager = findViewById(R.id.pager);

        CustomFragmentAdapter customAdapter = new CustomFragmentAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(customAdapter);
    }

    private void selectFragment(int index) {
        Toast.makeText(this, index + " selected", Toast.LENGTH_SHORT).show();
        pager.setCurrentItem(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private class CustomFragmentAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> fragments;

        public CustomFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
            super(fm);
            fragments = list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}