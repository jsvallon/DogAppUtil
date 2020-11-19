package com.example.dogapputil.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dogapputil.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout mDrawer = findViewById(R.id.drawer_layout);

        findViewById(R.id.ham_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView nvDrawer = findViewById(R.id.nav);
        nvDrawer.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(nvDrawer,navController);

        final TextView barTitle = findViewById(R.id.bar_title);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                barTitle.setText(destination.getLabel());
            }
        });

    }
}