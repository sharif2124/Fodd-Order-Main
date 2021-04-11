package com.ridoy.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ridoy.foodorder.Database.DBHelper;
import com.ridoy.foodorder.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {

    ActivityDetailedBinding binding;
    int quantity = 1;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBHelper dbHelper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 1) {

            String foodName = getIntent().getStringExtra("foodName");
            String foodDescription = getIntent().getStringExtra("foodDescription");
            int foodPrice = Integer.parseInt(getIntent().getStringExtra("foodPrice"));
            int foodImage = getIntent().getIntExtra("foodImage", 0);

            binding.detailsFoodName.setText(foodName);
            binding.detailsfoodimageView.setImageResource(foodImage);
            binding.detailsDescription.setText(foodDescription);
            if (quantity == 1) {
                totalPrice = foodPrice;
            }
            binding.totalfoodPriceTV.setText(totalPrice + "");

            binding.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity--;
                    if (quantity < 1) {
                        Toast.makeText(DetailedActivity.this, "Minimum 1 Quantity Required", Toast.LENGTH_SHORT).show();
                        quantity = 1;
                    } else {
                        binding.quantity.setText(String.valueOf(quantity));
                        totalPrice = foodPrice * quantity;
                        binding.totalfoodPriceTV.setText(String.valueOf(totalPrice));
                    }
                }
            });

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity++;
                    binding.quantity.setText(String.valueOf(quantity));
                    totalPrice = foodPrice * quantity;
                    binding.totalfoodPriceTV.setText(String.valueOf(totalPrice));
                }
            });

            binding.orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = binding.nameET.getText().toString();
                    String phone = binding.phoneET.getText().toString();
                    boolean insertted = dbHelper.insertOrder(name, phone, foodName, totalPrice,
                            foodImage, foodDescription, quantity);
                    if (insertted) {
                        Toast.makeText(DetailedActivity.this, "Order Succesful", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        startActivity(new Intent(DetailedActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(DetailedActivity.this, "Order Failed\nSome Error Occurs", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            int id=getIntent().getIntExtra("orderNumber",0);
            Cursor cursor=dbHelper.getOrderById(id);
            binding.detailsFoodName.setText(cursor.getString(6));
            binding.detailsfoodimageView.setImageResource(cursor.getInt(4));
            binding.detailsDescription.setText(cursor.getString(5));
            binding.totalfoodPriceTV.setText(cursor.getInt(3)+"");
            binding.quantity.setText(cursor.getInt(7)+"");
            binding.nameET.setText(cursor.getString(1));
            binding.phoneET.setText(cursor.getString(2));
            binding.orderBtn.setText("Update Order");

            int foodPrice=((cursor.getInt(3))/(cursor.getInt(7)));
            final int[] quantity = {cursor.getInt(7)};

            binding.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity[0]--;
                    if (quantity[0] < 1) {
                        Toast.makeText(DetailedActivity.this, "Minimum 1 Quantity Required", Toast.LENGTH_SHORT).show();
                        quantity[0] = 1;
                    } else {
                        binding.quantity.setText(String.valueOf(quantity[0]));
                        totalPrice = foodPrice * quantity[0];
                        binding.totalfoodPriceTV.setText(String.valueOf(totalPrice));
                    }
                }
            });

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity[0]++;
                    binding.quantity.setText(String.valueOf(quantity[0]));
                    totalPrice = foodPrice * quantity[0];
                    binding.totalfoodPriceTV.setText(String.valueOf(totalPrice));
                }
            });

            binding.orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean updated=dbHelper.updateOrder(
                            binding.nameET.getText().toString(),
                            binding.phoneET.getText().toString(),
                            binding.detailsFoodName.getText().toString(),
                            totalPrice,
                            cursor.getInt(4),
                            binding.detailsDescription.getText().toString(),
                            quantity[0],id);
                    if (updated) {
                        Toast.makeText(DetailedActivity.this, "Order Update Succesful", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        startActivity(new Intent(DetailedActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(DetailedActivity.this, "Order Update Failed\nSome Error Occurs", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}