package com.ridoy.foodorder.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ridoy.foodorder.ModelClasses.Order;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String dbName = "orderDB.db";
    final static int version = 4;

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orders" +
                "(id integer primary key autoincrement," +
                "Username text," +
                "Userphone text," +
                "totalprice int," +
                "image int," +
                "description text," +
                "foodName text," +
                "quantity int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders");
        onCreate(db);

    }

    public boolean insertOrder(String username, String userphone, String foodname, int price, int image, String description, int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Userphone", userphone);
        values.put("totalprice", price);
        values.put("image", image);
        values.put("description", description);
        values.put("foodName", foodname);
        values.put("quantity", quantity);
        long id = database.insert("orders", null, values);

        if (id <= 0) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<Order> orderList() {
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodName,totalprice,image from orders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Order order = new Order();
                order.setOrderNumber(cursor.getInt(0) + "");
                order.setOrderName(cursor.getString(1));
                order.setOrderPrice(cursor.getInt(2) + "");
                order.setOrderImage(cursor.getInt(3));
                orders.add(order);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id=" + id, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;

    }

    public boolean updateOrder(String username, String userphone, String foodname, int price, int image, String description, int quantity, int orderId) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Userphone", userphone);
        values.put("totalprice", price);
        values.put("image", image);
        values.put("description", description);
        values.put("foodName", foodname);
        values.put("quantity", quantity);
        long id = database.update("orders", values, "id=" + orderId, null);

        if (id <= 0) {
            return false;
        } else {
            return true;
        }

    }
    public int orderDelete(int orderId){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders","id="+orderId,null);
    }
}
