package com.ridoy.foodorder.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ridoy.foodorder.Database.DBHelper;
import com.ridoy.foodorder.DetailedActivity;
import com.ridoy.foodorder.ModelClasses.Order;
import com.ridoy.foodorder.OrdersActivity;
import com.ridoy.foodorder.R;
import com.ridoy.foodorder.databinding.SampleOrderBinding;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    Context context;
    ArrayList<Order> orderList;

    public OrdersAdapter(Context context, ArrayList<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_order, parent, false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.binding.orderfoodimageView.setImageResource(order.getOrderImage());
        holder.binding.orderfoodName.setText(order.getOrderName());
        holder.binding.orderfoodNumberTV.setText(order.getOrderNumber());
        holder.binding.orderfoodPriceTV.setText(order.getOrderPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("orderNumber", Integer.parseInt(order.getOrderNumber()));
                intent.putExtra("type", 2);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setIcon(R.drawable.ic_cancel)
                        .setMessage("Do you want to Delete this item?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper helper = new DBHelper(context);
                                int isdeleted = helper.orderDelete(Integer.parseInt(order.getOrderNumber()));
                                if (isdeleted > 0) {
                                    Toast.makeText(context, "Order Deleted", Toast.LENGTH_SHORT).show();
                                    ((OrdersActivity)context).finish();
                                    context.startActivity(((OrdersActivity) context).getIntent());
                                } else {
                                    Toast.makeText(context, "Some error Occurs", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {
        SampleOrderBinding binding;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SampleOrderBinding.bind(itemView);
        }
    }
}
