package com.example.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.home.FirstFragment;


import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.home.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    @ColorInt int colore = Color.parseColor("#557186");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Infla il layout dell'attività principale utilizzando il binding
        com.example.home.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Imposta la toolbar come action bar
        setSupportActionBar(binding.toolbar);

        // Ottiene il NavController per gestire la navigazione tra i fragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        // Configura la barra dell'applicazione per la navigazione tra i fragment
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Imposta un listener per il click sul pulsante FAB (Floating Action Button)

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAnchorView(R.id.fab)
                            .setBackgroundTint(colore)
                            .setActionTextColor(colore)
                            .setAction("Action", null).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla il menu delle opzioni nell'action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*// Gestisce il clic sugli elementi del menu delle opzioni
        int id = item.getItemId();

        // Se l'elemento cliccato è l'impostazione delle azioni, esegui l'azione corrispondente
        if (id == R.id.action_settings) {
            return true;
        }
        */
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Gestisce il clic sulla freccia di navigazione nella barra dell'applicazione
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }



}
