package re.gaspa.bcmanager.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.ActivityMainBinding;
import re.gaspa.bcmanager.ui.fragments.Credits;
import re.gaspa.bcmanager.ui.fragments.Home;
import re.gaspa.bcmanager.ui.fragments.Map;
import re.gaspa.bcmanager.ui.fragments.Settings;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        setSupportActionBar(binding.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        this.loadFragment(Home.class);

        binding.navView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);


        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if( id == R.id.preferite )
        {
            item.setChecked( !item.isChecked() );
            // TODO Filter
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Class fragmentClass = Home.class;

        if (id == R.id.nav_home) {
            fragmentClass = Home.class;
        } else if (id == R.id.nav_map) {
            fragmentClass = Map.class;
        } else if (id == R.id.nav_share) {
            //fragmentClass = Share.class;
        } else if (id == R.id.nav_setting) {
            fragmentClass = Settings.class;
        } else if (id == R.id.nav_help) {
            //fragmentClass = Help.class;
        } else if (id == R.id.nav_credits) {
            fragmentClass = Credits.class;
        }

        this.loadFragment(fragmentClass);

        item.setChecked(true);

        binding.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void loadFragment(Class fragmentClass)
    {
        Fragment fragment;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
        else super.onBackPressed();

    }


}
