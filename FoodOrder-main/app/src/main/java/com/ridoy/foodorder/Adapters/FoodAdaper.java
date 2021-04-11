package com.ridoy.foodorder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ridoy.foodorder.DetailedActivity;
import com.ridoy.foodorder.ModelClasses.Food;
import com.ridoy.foodorder.R;
import com.ridoy.foodorder.databinding.SampleFoodBinding;

import java.util.ArrayList;

public class FoodAdaper extends RecyclerView.Adapter<FoodAdaper.FoodViewHolder> {

    Context context;
    ArrayList<Food> foodList;

    public FoodAdaper(Context context, ArrayList<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_food,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food=foodList.get(position);
        holder.binding.foodName.setText(food.getName());
        holder.binding.foodPriceTV.setText(food.getPrice());
        holder.binding.foodDescriptionTV.setText(food.getDescription());
        holder.binding.foodimageView.setImageResource(food.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailedActivity.class);
                intent.putExtra("foodName",food.getName());
                intent.putExtra("foodPrice",food.getPrice());
                intent.putExtra("foodImage",food.getImage());
                intent.putExtra("foodDescription",food.getDescription());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        SampleFoodBinding binding;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=SampleFoodBinding.bind(itemView);
        }
    }
}
