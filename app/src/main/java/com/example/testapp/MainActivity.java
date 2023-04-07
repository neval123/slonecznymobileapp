package com.example.testapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        products.clear();
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        LayoutInflater layoutInflater = getLayoutInflater();
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_deals, R.id.nav_bread, R.id.nav_drink, R.id.nav_dishes,
                R.id.nav_candy, R.id.nav_snacks, R.id.nav_other, R.id.nav_search)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        DatabaseReference database = FirebaseDatabase.getInstance("https://sloneczny-6f0c0-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        DatabaseReference ref = database.child("Items");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                for (DataSnapshot dataSnapshot : Snapshot.getChildren()){
                    Product model = dataSnapshot.getValue(Product.class);
                    products.add(model);
                    Log.w("FirebaseData", "Value: " + model.getName());
                }
                Log.w("FirebaseData", "List: " + products.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("FirebaseData", "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static void createRows(List<Product> data, LayoutInflater inflater, RelativeLayout layout, ViewGroup insertPoint){
        int i = 0;
        for (Product example : data) {

            View row = inflater.inflate(R.layout.product_row, layout, false);
            TextView price = row.findViewById(R.id.textViewPrice);
            price.setText(String.format("%.2f zł\n", example.getPrice()));
            TextView name = row.findViewById(R.id.textViewName);
            name.setText(example.getName());
            ImageView image = row.findViewById(R.id.imageViewProduct);
            Picasso.get().load(example.getImage()).into(image);
//            image.setImageBitmap(example.getImage());
        //    image.setBackgroundColor(Color.TRANSPARENT);
            // layout.addView(row);
         //   ViewGroup insertPoint = (ViewGroup) layout.findViewById(R.id.insert_point_promo);
            //      insertPoint.addView(row, i, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
            insertPoint.addView(row, i);
            i++;

        }
    }
}