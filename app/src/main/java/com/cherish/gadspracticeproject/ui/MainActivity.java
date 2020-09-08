package com.cherish.gadspracticeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cherish.gadspracticeproject.R;
import com.cherish.gadspracticeproject.adapter.FragmentAdapter;
import com.cherish.gadspracticeproject.data.remote.APIService;
import com.cherish.gadspracticeproject.data.remote.APIUtils;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private FragmentAdapter myAdapter;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        submitBtn = findViewById(R.id.submitBtn);
        tabLayout.addTab(tabLayout.newTab().setText("Learning Leaders"));
        tabLayout.addTab(tabLayout.newTab().setText("Skill IQ Leaders"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = findViewById(R.id.viewPager);
        myAdapter = new FragmentAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(myAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Submit", "Submit Button is clicked");
                Intent intent = new Intent(getApplicationContext(), SubmissionActivity.class);
                startActivity(intent);
            }
        });


    }
}
