package com.ibsvalleyn.outlet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.adapters.ProductsOfBrandAdapter;
import com.ibsvalleyn.outlet.helper.EndlessRecyclerViewScrollListener;
import com.ibsvalleyn.outlet.models.ProductsOfBrandListModel;
import com.ibsvalleyn.outlet.viewModels.ProductsBrandViewModel;
import com.ibsvalleyn.outlet.viewModels.SignUpViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsOfBrandActivity extends AppCompatActivity {
    RecyclerView recyclerProductsOfBrand;
    ProductsBrandViewModel productsBrandViewModel;
    ProductsOfBrandAdapter productsOfBrandAdapter;
    int my_page = 1;
    List<ProductsOfBrandListModel> productsOfBrandDataList;
    private int brand_id;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_of_brand);
        init();
        productsOfBrandDataList = new ArrayList<>();
        productsBrandViewModel = ViewModelProviders.of(this).get(ProductsBrandViewModel.class);
        Intent intent = getIntent();
        brand_id = intent.getIntExtra("brand_id", 0);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerProductsOfBrand.setLayoutManager(gridLayoutManager);
        recyclerProductsOfBrand.setItemAnimator(new DefaultItemAnimator());

        productsOfBrandAdapter = new ProductsOfBrandAdapter(this, productsOfBrandDataList);
        recyclerProductsOfBrand.setAdapter(productsOfBrandAdapter);

        recyclerProductsOfBrand.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                my_page++;
                getProductsOfBrand(my_page);
            }
        });

        getProductsOfBrand(my_page);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getProductsOfBrand(final int page) {
        productsBrandViewModel.getProductsOfBrandList(this, brand_id,
                page, 6).observe(this, productsOfBrandListModels -> {
            productsOfBrandDataList.addAll(productsOfBrandListModels);
            productsOfBrandAdapter.notifyItemRangeInserted(productsOfBrandAdapter.getItemCount(), productsOfBrandDataList.size());
        });
    }

    private void init() {
        recyclerProductsOfBrand = findViewById(R.id.recyclerProductsOfBrand);
        back = findViewById(R.id.back);
    }
}
