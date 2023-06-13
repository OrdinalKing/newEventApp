package com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.ui.myEvent.MyEventFragment;
import com.ui.event.EventFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ActionBarDrawerToggle drawer_toggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawer_toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new EventFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();
            if (id == R.id.myEvents) {
                replaceFragment(new MyEventFragment());
            }
               else if (id == R.id.events) {
                replaceFragment(new EventFragment());
            }
            return true;
        });

        drawer_toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(drawer_toggle);
        drawer_toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
//                    case R.id.home_id:
//                    {
//                        Toast.makeText(
//                                MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    case R.id.error_id:
//                    {
//                        Toast.makeText(
//                                MainActivity.this, "Report Error", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    case R.id.about_id:
//                    {
//                        Toast.makeText(
//                                MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
                    default:
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}