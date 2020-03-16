package com.ibsvalleyn.outlet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.ProductsOfBrandActivity;
import com.ibsvalleyn.outlet.models.MainBrandModel;
import com.ibsvalleyn.outlet.models.MainCategoriesModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.ibsvalleyn.outlet.helper.Static_variables.API_LINK;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.ViewHolder> {

    Context context;
    private List<MainBrandModel> mainCategoriesModels;

    // DetailsProductView detailsProductView;


    public BrandsAdapter(Context context, List<MainBrandModel> mainCategoriesModels) {
        this.context = context;
        this.mainCategoriesModels = mainCategoriesModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_brand_layout, parent, false);
        return new ViewHolder(view);
    }
// main_brand_layout
    /* public void onClickItemProduct(DetailsProductView detailsProductView)
     {
         this.detailsProductView=detailsProductView;
     }*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.T_mainCategories.setText(mainCategoriesModels.get(position).getBrandName());
//        holder.T_description.setText(String.valueOf(commentsDataList.get(position).getRateComment()));
        Glide.with(context).load(API_LINK+mainCategoriesModels.get(position).getPicUrl()).into(holder.circleImageView);
        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsOfBrandActivity.class);
                intent.putExtra("brand_id",mainCategoriesModels.get(position).getBrandId());
                context.startActivity(intent);
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                detailsProductView.showDetailsProduct(productsDataList.get(position));
//            }
//        });
    }

    public int getItemCount() {
        return mainCategoriesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circleImageView;
        private TextView T_mainCategories;


        public ViewHolder(View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circleImageView);
            T_mainCategories = itemView.findViewById(R.id.T_mainCategories);

        }
    }
}