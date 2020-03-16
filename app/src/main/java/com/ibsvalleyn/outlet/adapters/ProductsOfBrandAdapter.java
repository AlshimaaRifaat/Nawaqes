package com.ibsvalleyn.outlet.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.ProductsOfBrandActivity;
import com.ibsvalleyn.outlet.models.ProductsOfBrandListModel;

import java.util.List;

import static com.ibsvalleyn.outlet.helper.Static_variables.API_LINK3;

public class ProductsOfBrandAdapter extends RecyclerView.Adapter<ProductsOfBrandAdapter.ProductsOfBrandViewHolder> {

    private Context context;
    private List<ProductsOfBrandListModel> productsOfBrandListModels;


    public ProductsOfBrandAdapter(Context context, List<ProductsOfBrandListModel> productsOfBrandListModels) {
        this.context = context;
        this.productsOfBrandListModels = productsOfBrandListModels;
    }

    @NonNull
    @Override
    public ProductsOfBrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_products_of_brand, parent, false);
        return new ProductsOfBrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductsOfBrandViewHolder holder, int position) {
        //  Glide.with(context).load(API_LINK3+productsOfBrandListModels.get(position).getPicUrl()).into(holder.product_image);
        holder.product_name.setText(productsOfBrandListModels.get(position).getName());
        holder.product_old_price.setText("$" + productsOfBrandListModels.get(position).getOldPrice());
        holder.product_old_price.setPaintFlags(holder.product_old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.product_new_price.setText("$" + productsOfBrandListModels.get(position).getPrice());
        Log.e("TAG", "Product_id " + productsOfBrandListModels.get(position).getId());

    }

    @Override
    public int getItemCount() {
        // return heroList.size();
        return productsOfBrandListModels.size();
    }

    class ProductsOfBrandViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        TextView product_name;
        TextView product_old_price;
        TextView product_new_price;


        public ProductsOfBrandViewHolder(View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_old_price = itemView.findViewById(R.id.product_old_price);
            product_new_price = itemView.findViewById(R.id.product_new_price);

        }
    }
}
