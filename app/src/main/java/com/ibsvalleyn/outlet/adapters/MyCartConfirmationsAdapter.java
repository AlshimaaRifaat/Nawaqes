package com.ibsvalleyn.outlet.adapters;


import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.CheckOutActivity;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.helper.Static_variables;
import com.ibsvalleyn.outlet.models.Add_to_cart;
import com.ibsvalleyn.outlet.models.ShoppingCarts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.ibsvalleyn.outlet.helper.AppFunctions.Rails;

public class MyCartConfirmationsAdapter extends RecyclerView.Adapter<MyCartConfirmationsAdapter.MyCartViewHolder> {
    ShoppingCarts profile;
    String numberOfProduct;
    SharedPreferences dataSaver;
    int customer_id;
    Button place_order;
    CheckOutActivity context;

    public MyCartConfirmationsAdapter(CheckOutActivity context, ShoppingCarts profile) {
        this.context = context;
        this.profile = profile;
    }

    @NonNull
    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_cart_confirmations_row, viewGroup, false);
        MyCartViewHolder myCartViewHolder = new MyCartViewHolder(view);
        return myCartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHolder holder, int position) {
        Toast toast = new Toast(context);

        dataSaver = getDefaultSharedPreferences(context);
        customer_id = dataSaver.getInt(Static_variables.CUSTOMER_ID, 0);
        Glide.with(context).load(profile.getItems().get(position).getProduct().getImage_url()).into(holder.product_image);
//        holder.product_name.setText(profile.getItems().get(position).getProduct().getName());
        double price = profile.getItems().get(position).getTotalprice();
        double priceTotalselling = profile.getItems().get(position).getTotalselling();
        if (price > priceTotalselling) {
            holder.product_price1.setVisibility(View.VISIBLE);
            holder.product_price.setVisibility(View.VISIBLE);
        }
        holder.product_price.setText(Rails(context, priceTotalselling));
        holder.product_price1.setText(Rails(context, price));
        holder.product_price1.setPaintFlags(holder.product_price1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        int quantity = profile.getItems().get(position).getQuantity();
        holder.number.setText("Qty: " + quantity);
        double netPrice = profile.getItems().get(position).getProduct().getPrice();


        if (profile.getItems().get(position).getProduct().getName().length() < 20) {
            holder.product_name.setText(profile.getItems().get(position).getProduct().getName());

        } else {
            String substring = profile.getItems().get(position).getProduct().getName().substring(0, 18) + ". . .";
            holder.product_name.setText(substring);

        }


        holder.wishlist.setOnClickListener(view -> {

            int productId = profile.getItems().get(position).getProduct().getId();
            Call<Add_to_cart> addEVEnt_call = RetrofitClient.getInstance(context)
                    .updateCart(2, customer_id, productId, 0);
            Log.e("customer_id", "customer_id " + customer_id);
            Log.e("productId", "productId " + productId);

            addEVEnt_call.enqueue(new Callback<Add_to_cart>() {

                @Override
                public void onResponse(Call<Add_to_cart> call, Response<Add_to_cart> response) {

                    if (response.isSuccessful()) {

                        if (response.body().isResult() == true) {
                            Log.e("TAG", "isSuccessful");

                            place_order.setText("Place This Order: " + response.body().getTotal_amount());
                            profile.getItems().remove(profile.getItems().get(position));
//                                context.badgeCart().setText(String.valueOf(profile.getShoppingCarts().getItems().size()));
                            notifyDataSetChanged();

                            Log.i("ZZFDXGD", "onResponse: " + profile.getItems().size());

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

                    }
                    Toast.makeText(context, "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            });
        });

        holder.plus.setOnClickListener(view -> {
            numberOfProduct = holder.number.getText().toString();
            if (!numberOfProduct.equals("")) {
                int x = Integer.parseInt(numberOfProduct);
                int c = x + 1;
                holder.number.setText(String.valueOf(c));
                holder.product_price.setText(Rails(context, (netPrice * c)));

                int productId = profile.getItems().get(position).getProduct().getId();
                Call<Add_to_cart> addEVEnt_call = RetrofitClient.getInstance(context)
                        .updateCart(2, customer_id, productId, c);
                Log.e("customer_id", "customer_id " + customer_id);
                Log.e("productId", "productId " + productId);

                addEVEnt_call.enqueue(new Callback<Add_to_cart>() {

                    @Override
                    public void onResponse(Call<Add_to_cart> call, Response<Add_to_cart> response) {

                        if (response.isSuccessful()) {

                            if (response.body().isResult() == true) {
                                place_order.setText("Place This Order: " + response.body().getTotal_amount());


                                Log.e("TAG", "isSuccessful");
                                //       Toast.makeText(context, response.body().getUser_message(), Toast.LENGTH_SHORT).show();
                            } else {
                                //     Toast.makeText(context, response.body().getUser_message(), Toast.LENGTH_SHORT).show();
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
                if (x != 0) {
                    int c = x - 1;
                    holder.number.setText(String.valueOf(c));
                    holder.product_price.setText(String.valueOf(netPrice * c));

                    int productId = profile.getItems().get(position).getProduct().getId();
                    Call<Add_to_cart> addEVEnt_call = RetrofitClient.getInstance(context)
                            .updateCart(2, customer_id, productId, c);
                    Log.e("customer_id", "customer_id " + customer_id);
                    Log.e("productId", "productId " + productId);

                    addEVEnt_call.enqueue(new Callback<Add_to_cart>() {

                        @Override
                        public void onResponse(Call<Add_to_cart> call, Response<Add_to_cart> response) {

                            if (response.isSuccessful()) {

                                if (response.body().isResult() == true) {
                                    Log.e("TAG", "isSuccessful");
                                    place_order.setText("Place This Order: " + response.body().getTotal_amount());

                                } else {
                                    //     Toast.makeText(context, response.body().getUser_message(), Toast.LENGTH_SHORT).show();
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
            }
        });
    }

    @Override
    public int getItemCount() {
        if (profile != null) {
            return profile.getItems().size();

        } else {
            return 0;

        }
    }

    public class MyCartViewHolder extends RecyclerView.ViewHolder {
        TextView product_price, product_price1, product_name, product_color, product_size;
        TextView number;
        ImageView product_image, plus, minus, wishlist;
        Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "Ubuntu-B.ttf");
        Typeface customTypeOne1 = Typeface.createFromAsset(itemView.getContext().getAssets(), "Ubuntu-R.ttf");

        public MyCartViewHolder(@NonNull View itemView) {
            super(itemView);
            product_price = itemView.findViewById(R.id.product_price);
            product_price1 = itemView.findViewById(R.id.product_price1);
            product_name = itemView.findViewById(R.id.product_name);
            product_image = itemView.findViewById(R.id.cart_image);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            number = itemView.findViewById(R.id.number);
            product_color = itemView.findViewById(R.id.product_color);
            product_size = itemView.findViewById(R.id.product_size);
            wishlist = itemView.findViewById(R.id.wishlist);
            product_name.setTypeface(customTypeOne1);
            product_price1.setTypeface(customTypeOne);
            product_price.setTypeface(customTypeOne);
            number.setTypeface(customTypeOne);
        }
    }
}