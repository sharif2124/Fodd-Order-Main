package com.ridoy.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ridoy.foodorder.Adapters.OrdersAdapter;
import com.ridoy.foodorder.Database.DBHelper;
import com.ridoy.foodorder.ModelClasses.Order;
import com.ridoy.foodorder.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;
    OrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper dbHelper=new DBHelper(this);
        ArrayList<Order> orderList=dbHelper.orderList();

        adapter=new OrdersAdapter(this,orderList);
        binding.ordersRV.setHasFixedSize(true);
        binding.ordersRV.setAdapter(adapter);

    }
}