package re.gaspa.bcmanager.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.ActivityMainBinding;
import re.gaspa.bcmanager.ui.fragments.Credits;
import re.gaspa.bcmanager.ui.fragments.EditProfile;
import re.gaspa.bcmanager.ui.fragments.Home;
import re.gaspa.bcmanager.ui.fragments.Map;
import re.gaspa.bcmanager.ui.fragments.Settings;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Preferences;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private FragmentManager fragmentManager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        Preferences.getPreferences(binding.getRoot().getContext());
        BusinessCard personal = Preferences.getPersonalBusinessCard(null);

        DrawerLayout drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        this.loadFragment(Home.class);
        binding.navView.getMenu().getItem(0).setChecked(true);

        binding.navView.setNavigationItemSelectedListener(this);

        if( personal != null ) {
            Bitmap profile = personal.getProfilo();
            Bitmap sfondo = personal.getSfondo();
            String nome = personal.getNome();
            String role = personal.getLavoroRuolo();

            if( profile != null ) {
                CircleImageView profileImage = binding.navView.getHeaderView(0).findViewById(R.id.profile_image);
                profileImage.setImageBitmap(profile);
            }
            if( sfondo != null ) {
                ImageView backgroundImage = binding.navView.getHeaderView(0).findViewById(R.id.default_background);
                backgroundImage.setImageBitmap(sfondo);
            }
        }

        binding.navView.getHeaderView(0).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);


        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SEARCH VIEW", "QUERY SUBMIT "+ query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                Log.d("SEARCH VIEW", "TEXT CHANGE " + newText);
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


        if( id == R.id.nav_share )
        {
            item.setChecked(false);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Utilizza pure te BCManager! http://gaspa.re/bcmanager");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Condividi!"));
        }
        else if( id == R.id.nav_help )
        {
            item.setChecked(false);
            Intent intent = new Intent(this.getApplicationContext(), Help.class);
            this.getApplicationContext().startActivity(intent);
        }
        else
        {
            Class fragmentClass = Home.class;

            if (id == R.id.nav_home) {
                fragmentClass = Home.class;
            } else if (id == R.id.nav_map) {
                fragmentClass = Map.class;
            } else if (id == R.id.nav_setting) {
                fragmentClass = Settings.class;
            } else if (id == R.id.nav_credits) {
                fragmentClass = Credits.class;
            } else if (id == R.id.nav_profile) {
                fragmentClass = EditProfile.class;
            }

            this.loadFragment(fragmentClass);

            item.setChecked(true);

        }

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
        }
        else if( binding.navView.getMenu().getItem(0).isChecked() )
        {
            finish();
        }
        else {
            this.loadFragment(Home.class);
            binding.navView.getMenu().getItem(0).setChecked(true);
        }

    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        Intent intent = new Intent(view.getContext(), BusinessCardActivity.class);
        intent.putExtra("businesscard", Preferences.getPersonalBusinessCard(null) );
        view.getContext().startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO
    }
}
