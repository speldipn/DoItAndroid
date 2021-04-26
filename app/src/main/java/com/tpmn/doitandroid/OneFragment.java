package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {

    ViewPager viewPager;
    CustomPagerAdapter customPagerAdapter;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(PracticeActivity.TAG, "onAttach called by OneFragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(PracticeActivity.TAG, "onCreate called by OneFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(PracticeActivity.TAG, "onCreateView called by OneFragment");
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(PracticeActivity.TAG, "onViewCreated called by OneFragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(PracticeActivity.TAG, "onActivityCreated called by OneFragment");

        List<Drawable> list = new ArrayList<>();
        list.add(ContextCompat.getDrawable(getContext(), R.drawable.menu_refresh));
        list.add(ContextCompat.getDrawable(getContext(), R.drawable.menu_search));
        list.add(ContextCompat.getDrawable(getContext(), R.drawable.menu_settings));

        customPagerAdapter = new CustomPagerAdapter(list);
        viewPager.setAdapter(customPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
    }

    private class CustomPagerAdapter extends PagerAdapter {
        private final List<Drawable> list;

        public CustomPagerAdapter(List<Drawable> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.pager_item, null);
            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageDrawable(list.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    public static Fragment newInstance() {
        return new OneFragment();
    }
}