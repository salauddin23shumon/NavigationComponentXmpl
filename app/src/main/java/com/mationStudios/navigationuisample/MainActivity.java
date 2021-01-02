package com.mationStudios.navigationuisample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mationStudios.navigationuisample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";
    AppBarConfiguration appBarConfiguration;
    ActivityMainBinding activityMainBinding;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpNavigation();
    }

    public void setUpNavigation() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(activityMainBinding.bttmNav,
                navHostFragment.getNavController());
        NavigationUI.setupWithNavController(activityMainBinding.navigationView, navHostFragment.getNavController());
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.getNavController(), activityMainBinding.drawerLayout);
        activityMainBinding.navigationView.setNavigationItemSelectedListener(this);
        appBarConfiguration = new AppBarConfiguration.Builder(navHostFragment.getNavController().getGraph())
                .setDrawerLayout(activityMainBinding.drawerLayout)
                .build();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        int count=navHostFragment.getChildFragmentManager().getBackStackEntryCount();
        Log.e(TAG, "onBackPressed: "+count );
        super.onBackPressed();

    }
}
