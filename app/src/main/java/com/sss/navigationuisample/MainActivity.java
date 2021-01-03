package com.sss.navigationuisample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.sss.navigationuisample.databinding.ActivityMainBinding;
import com.sss.navigationuisample.extras.ChangeHomeScreenListener;

public class MainActivity extends AppCompatActivity implements ChangeHomeScreenListener {

    private static final String TAG = "MainActivity";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding activityMainBinding;
    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(activityMainBinding.myToolbar);

        setUpNavigation();

    }

    public void setUpNavigation() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(activityMainBinding.bottomNav, navHostFragment.getNavController());

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        /** enable this portion when there is no splash**/
//        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home, R.id.explore, R.id.more)
//                .setDrawerLayout(activityMainBinding.drawerLayout)
//                .build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(activityMainBinding.navigationView, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        /** nav drawer with backBtn step-1**/
//        return NavigationUI.navigateUp(navController, activityMainBinding.drawerLayout);

        /**nav drawer with no backBtn step-2**/
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
//        int count=navHostFragment.getChildFragmentManager().getBackStackEntryCount();
//        Log.e(TAG, "onBackPressed: "+count );
        super.onBackPressed();

    }

    @Override
    public void onHomeChange(int fragmentId) {
        activityMainBinding.bottomNav.setVisibility(View.VISIBLE);
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_graph);
        navGraph.setStartDestination(R.id.home);
        navController.setGraph(navGraph);

        /** nav drawer with backBtn step-2**/
//        appBarConfiguration = new AppBarConfiguration.Builder(navGraph)
//                .setOpenableLayout(activityMainBinding.drawerLayout)
//                .build();

        /** nav drawer with no backBtn step-2**/
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home, R.id.explore, R.id.more)
                .setOpenableLayout(activityMainBinding.drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(activityMainBinding.navigationView, navHostFragment.getNavController());
    }
}
