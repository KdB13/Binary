package com.kdb.binary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.kdb.binary.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        setupNavigationUI();
    }

    private void setupNavigationUI() {
        navController = Navigation.findNavController(findViewById(R.id.nav_host_fragment));
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

}