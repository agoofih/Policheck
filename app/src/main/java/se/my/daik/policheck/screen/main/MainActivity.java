package se.my.daik.policheck.screen.main;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import se.my.daik.policheck.Factory;
import se.my.daik.policheck.R;
import se.my.daik.policheck.fragments.FavoriteFragment;
import se.my.daik.policheck.fragments.InfoFragment;
import se.my.daik.policheck.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.GoToNextFromMain, FavoriteFragment.GoToNextFromFavorite, InfoFragment.GoToNextFromInfo {

    private static final String TAG = "MainActivity";

    private int closeCounter = 0;

    private ActionBarDrawerToggle mDrawerToggle;
    ActionBar actionBar;

    NavigationView navigationView;

    private RssEntryRepository rssEntryRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(new MainFragment());
        }




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Feed");
            //actionBar.setSubtitle("Policheck");

        }


        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );

        drawerLayout.addDrawerListener(mDrawerToggle);

        navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.main_fragment:
                        loadFragment(new MainFragment());

                        if (actionBar != null) {
                            actionBar.setTitle("Feed");
                            //actionBar.setSubtitle("Policheck");
                        }
                        break;

                    case R.id.favorite_fragment:
                        loadFragment(new FavoriteFragment());
                        if (actionBar != null) {
                            actionBar.setTitle("Favorite");
                            //actionBar.setSubtitle("Policheck");
                        }
                        break;

                    case R.id.info_fragment:
                        loadFragment(new InfoFragment());
                        if (actionBar != null) {
                            actionBar.setTitle("Info");
                            //actionBar.setSubtitle("Policheck");
                        }
                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();

                return false;
            }
        });

        navigationView.setCheckedItem(R.id.main_fragment);

        rssEntryRepository = Factory.getRssEntryRepository(this.getApplication());

        new FeedOneService().update(rssEntryRepository);
        new FeedSecondService().update(rssEntryRepository);
        rssEntryRepository.nuke();

    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.placeholder, fragment)
                .commit();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    @Override
    public void goToNextFragmentFromMain() {
        FavoriteFragment fragment = new FavoriteFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.placeholder, fragment)
                .commit();
    }

    @Override
    public void goToNextFragmentFromFavorite() {
        MainFragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.placeholder, fragment)
                .commit();
    }

    @Override
    public void goToNextFragmentFromInfo() {
        InfoFragment fragment = new InfoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.placeholder, fragment)
                .commit();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));

    }

    @Override
    public void onBackPressed() {

        if (MainFragment.isMainFragmentLive() == true) {

            if (closeCounter == 0){
                closeCounter++;
                Toast.makeText(this, R.string.toast_close_message, Toast.LENGTH_SHORT).show();
            }else{
                finish();
            }

        }else{
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.placeholder, fragment)
                    .commit();
            actionBar.setTitle("Feed");
            navigationView.setCheckedItem(R.id.main_fragment);
        }
    }


}
