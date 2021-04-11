package com.ridoy.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ridoy.foodorder.Adapters.FoodAdaper;
import com.ridoy.foodorder.ModelClasses.Food;
import com.ridoy.foodorder.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Food> foodList;
    FoodAdaper adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        foodList=new ArrayList<>();

        foodList.add(new Food("Burger","2","This is lovely burger",R.drawable.burger));
        foodList.add(new Food("Pasta","3","This is lovely Pasta",R.drawable.pasta));
        foodList.add(new Food("Pizza","4","This is lovely Pizza",R.drawable.pizzza));

        adaper=new FoodAdaper(this,foodList);
        binding.foodListRV.setAdapter(adaper);
        binding.foodListRV.setHasFixedSize(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_menu_orders:
                startActivity(new Intent(this,OrdersActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}