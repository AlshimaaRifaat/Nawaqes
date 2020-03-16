package com.ibsvalleyn.outlet.adapters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.MainActivity;
import com.ibsvalleyn.outlet.activities.Product_details;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.fragments.Navigation_My_Cart_Fragment;
import com.bumptech.glide.Glide;
import com.ibsvalleyn.outlet.helper.Static_variables;
import com.ibsvalleyn.outlet.models.Add_to_cart;
import com.ibsvalleyn.outlet.models.CartModel;
import com.ibsvalleyn.outlet.models.LocalCart;
import com.ibsvalleyn.outlet.models.Products;
import com.ibsvalleyn.outlet.models.ShoppingCarts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.ibsvalleyn.outlet.helper.AppFunctions.Rails;
import static com.ibsvalleyn.outlet.helper.Static_variables.API_LINK;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder> {
    private int total;
    private List<CartModel> profile;
    private String numberOfProduct;
    SharedPreferences dataSaver;
    int customer_id;
    private TextView price_Mycart;
    MainActivity context;
    int productId;
    private int Counter;
    private static int quantity_ = 0;
    private int quantity1;
    int q;
    private List<LocalCart> localCarts = new ArrayList<>();
    private String s;

    public MyCartAdapter(MainActivity context, List<CartModel> profile, TextView price_Mycart, int total, int counter) {
        this.context = context;
        this.profile = profile;
        this.price_Mycart = price_Mycart;
        this.Counter = counter;
        this.total = total;
    }

    @NonNull
    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_cart_layout, viewGroup, false);
        return new MyCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHolder holder, int position) {
        Toast toast = new Toast(context);
        dataSaver = getDefaultSharedPreferences(context);
        customer_id = dataSaver.getInt(Static_variables.CUSTOMER_ID, 0);

        if (customer_id == 0) {
            Glide.with(context).load(localCarts.get(position).getImage_url()).into(holder.product_image);
            holder.product_name.setText(localCarts.get(position).getName());

        } else {
            Glide.with(context).load(API_LINK +profile.get(position).getImage_url()).into(holder.product_image);
            if (profile.get(position).getName().length() < 20) {
                holder.product_name.setText(profile.get(position).getName());

            } else {
                String substring = profile.get(position).getName().substring(0, 18) + ". . .";
                holder.product_name.setText(substring);

            }
         //   double price = profile.get(position).getTotalprice();
         //   double priceSelling = profile.getItems().get(position).getTotalselling();
           // Log.i("ZXzXz", "" + price + "  " + priceSelling);
            holder.product_price1.setPaintFlags(holder.product_price1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

//            if (priceSelling < price) {
//                holder.product_price1.setVisibility(View.VISIBLE);
//                holder.product_price.setVisibility(View.VISIBLE);
//
//            }
//            holder.product_price1.setText(Rails(context, price));
//            holder.product_price.setText(Rails(context, priceSelling));

            quantity1 = profile.get(position).getQuantity();
            holder.number.setText(String.valueOf(quantity1));
            s = holder.number.getText().toString();

            Log.e("s", "s " + s);

            if (s.equals("1")) {
                holder.minus.setImageDrawable(context.getResources().getDrawable(R.drawable.recycle_bincopy2));

            }
        }

        holder.product_image.setOnClickListener(view -> {

            Intent intent = new Intent(context, Product_details.class);
            intent.putExtra("product_id", profile.get(position).getId());
            intent.putExtra("product_name", profile.get(position).getName());
            dataSaver.edit()
                    .putInt("isSector", 55)
                    .apply();
            context.startActivity(intent);

        });

        holder.product_name.setOnClickListener(view -> {

            Intent intent = new Intent(context, Product_details.class);
            intent.putExtra("product_id", profile.get(position).getId());
            intent.putExtra("product_name", profile.get(position).getName());
            dataSaver.edit()
                    .putInt("isSector", 55)
                    .apply();
            context.startActivity(intent);

        });
        holder.product_price.setOnClickListener(view -> {

            Intent intent = new Intent(context, Product_details.class);
            intent.putExtra("product_id", profile.get(position).getId());
            intent.putExtra("product_name", profile.get(position).getName());
            dataSaver.edit()
                    .putInt("isSector", 55)
                    .apply();
            context.startActivity(intent);

        });
        holder.wishlist.setOnClickListener(view -> {
            quantity_ = profile.get(position).getQuantity();
            int productId = profile.get(position).getId();
            Call<Add_to_cart> addEVEnt_call = RetrofitClient.getInstance(context)
                    .updateCart(1, customer_id, productId, 0);
            Log.e("customer_id", "customer_id " + customer_id);
            Log.e("productId", "productId " + productId);
            addEVEnt_call.enqueue(new Callback<Add_to_cart>() {

                @Override
                public void onResponse(Call<Add_to_cart> call, Response<Add_to_cart> response) {

                    if (response.isSuccessful()) {

                        if (response.body().isResult() == true) {
                            Log.e("TAG", "isSuccessful");

                            price_Mycart.setText(Rails(context, Double.valueOf(response.body().getTotal_amount())));

                            notifyDataSetChanged();
                            context.loadFragment(new Navigation_My_Cart_Fragment());
                           // Counter = profile.getTotal_Quantity() - quantity_;
                          //  Log.e("abdo", "onResponse: " + profile.getTotal_Quantity());

                            dataSaver.edit()
                                    .putInt("position", position)
                                    .apply();

                            if (toast != null) {
                                toast.cancel();
                                Toast.makeText(context, response.body().getUser_message(), Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            if (toast != null) {
                                toast.cancel();
                                Toast.makeText(context, response.body().getUser_message(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Log.e("TAG", "notSuccessful");
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Add_to_cart> call, Throwable t) {
                    Log.e("TAG ", "onFailure " + t.getMessage());

                    if (toast != null) {
                        toast.cancel();
                        Toast.makeText(context, "حدث خطأ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        holder.plus.setOnClickListener(view -> {
            numberOfProduct = holder.number.getText().toString();
            if (!numberOfProduct.equals("")) {
                int x = Integer.parseInt(numberOfProduct);
                int c = x + 1;
                holder.number.setText(String.valueOf(c));
                int productId = profile.get(position).getId();
                Call<Add_to_cart> addEVEnt_call = RetrofitClient.getInstance(context)
                        .updateCart(1, customer_id, productId, c);
                Log.e("customer_id", "customer_id " + customer_id);
                Log.e("productId", "productId " + productId);

                addEVEnt_call.enqueue(new Callback<Add_to_cart>() {

                    @Override
                    public void onResponse(Call<Add_to_cart> call, Response<Add_to_cart> response) {

                        if (response.isSuccessful()) {

                            if (response.body().isResult() == true) {
                                price_Mycart.setText(Rails(context, Double.valueOf(response.body().getTotal_amount())));
                                notifyDataSetChanged();

                                context.loadFragment(new Navigation_My_Cart_Fragment());
                                dataSaver.edit()
                                        .putInt("position", position)
                                        .apply();
                                Log.e("TAG", "isSuccessful");
                            }
                        } else {
                            Log.e("TAG", "notSuccessful");

                            if (toast != null) {
                                toast.cancel();
                                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Add_to_cart> call, Throwable t) {
                        Log.e("TAG ", "onFailure " + t.getMessage());

                        if (toast != null) {
                            toast.cancel();
                            Toast.makeText(context, "حدث خطأ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        holder.minus.setOnClickListener(view -> {

            numberOfProduct = holder.number.getText().toString();
            if (!numberOfProduct.equals("")) {
                int x = Integer.parseInt(numberOfProduct);

                if (x != 1) {
                    int c = x - 1;
                    holder.number.setText(String.valueOf(c));
                    int productId = profile.get(position).getId();
                    Call<Add_to_cart> addEVEnt_call = RetrofitClient.getInstance(context)
                            .updateCart(1, customer_id, productId, c);
                    Log.e("customer_id", "customer_id " + customer_id);
                    Log.e("productId", "productId " + productId);

                    addEVEnt_call.enqueue(new Callback<Add_to_cart>() {

                        @Override
                        public void onResponse(Call<Add_to_cart> call, Response<Add_to_cart> response) {

                            if (response.isSuccessful()) {
                                context.loadFragment(new Navigation_My_Cart_Fragment());

                                if (response.body().isResult() == true) {
                                    Log.e("TAG", "isSuccessful");

                                    price_Mycart.setText(Rails(context, Double.valueOf(response.body().getTotal_amount())));

                                    notifyDataSetChanged();
                                    context.loadFragment(new Navigation_My_Cart_Fragment());
                                    dataSaver.edit()
                                            .putInt("position", position)
                                            .apply();
                                    Log.e("TAG", "total " + total);

                                } else {
                                }
                            } else {
                                Log.e("TAG", "notSuccessful");

                                if (toast != null) {
                                    toast.cancel();

                                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Add_to_cart> call, Throwable t) {
                            Log.e("TAG ", "onFailure " + t.getMessage());

                            if (toast != null) {
                                toast.cancel();

                                Toast.makeText(context, "حدث خطأ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    holder.minus.setImageDrawable(context.getResources().getDrawable(R.drawable.recycle_bincopy2));
                    quantity_ = profile.get(position).getQuantity();
                    int productId = profile.get(position).getId();
                    Call<Add_to_cart> addEVEnt_call = RetrofitClient.getInstance(context)
                            .updateCart(1, customer_id, productId, 0);
                    Log.e("customer_id", "customer_id " + customer_id);
                    Log.e("productId", "productId " + productId);
                    addEVEnt_call.enqueue(new Callback<Add_to_cart>() {

                        @Override
                        public void onResponse(Call<Add_to_cart> call, Response<Add_to_cart> response) {

                            if (response.isSuccessful()) {

                                if (response.body().isResult() == true) {
                                    Log.e("TAG", "isSuccessful");

                                    price_Mycart.setText("");

                                    notifyDataSetChanged();
                                    context.loadFragment(new Navigation_My_Cart_Fragment());
                                    //Counter = profile.getTotal_Quantity() - quantity_;
                                //    Log.e("abdo", "onResponse: " + profile.getTotal_Quantity());

                                    dataSaver.edit()
                                            .putInt("position", position)
                                            .apply();

                                    if (toast != null) {
                                        toast.cancel();
                                        Toast.makeText(context, response.body().getUser_message(), Toast.LENGTH_SHORT).show();
                                    }

                                } else {

                                    if (toast != null) {
                                        toast.cancel();
                                        Toast.makeText(context, response.body().getUser_message(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Log.e("TAG", "notSuccessful");
                                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Add_to_cart> call, Throwable t) {
                            Log.e("TAG ", "onFailure " + t.getMessage());

                            if (toast != null) {
                                toast.cancel();
                                Toast.makeText(context, "حدث خطأ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (profile == null) {
            return 0;
        }
        return profile.size();
    }

    public class MyCartViewHolder extends RecyclerView.ViewHolder {
        TextView product_price, product_name, product_color, product_size;
        TextView number, product_price1;
        ImageView product_image, plus, minus, wishlist;
        RelativeLayout relative;
        Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "Ubuntu-B.ttf");
        Typeface customTypeOne1 = Typeface.createFromAsset(itemView.getContext().getAssets(), "Ubuntu-R.ttf");

        public MyCartViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_image = itemView.findViewById(R.id.cart_image);
            relative = itemView.findViewById(R.id.relative);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            number = itemView.findViewById(R.id.number);
            product_color = itemView.findViewById(R.id.product_color);
            product_size = itemView.findViewById(R.id.product_size);
            wishlist = itemView.findViewById(R.id.remove_item);
            product_price = itemView.findViewById(R.id.product_price);

            product_price1 = itemView.findViewById(R.id.product_price1);
            product_name.setTypeface(customTypeOne1);
            product_price1.setTypeface(customTypeOne);
            product_price.setTypeface(customTypeOne);
            number.setTypeface(customTypeOne);
        }
    }
}