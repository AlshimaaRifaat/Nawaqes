package com.ibsvalleyn.outlet.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.adapters.ProductsOfBrandAdapter;
import com.ibsvalleyn.outlet.helper.AppFunctions;
import com.ibsvalleyn.outlet.helper.EndlessRecyclerViewScrollListener;
import com.ibsvalleyn.outlet.models.ProductsOfBrandListModel;
import com.ibsvalleyn.outlet.viewModels.ProductsBrandViewModel;
import com.ibsvalleyn.outlet.viewModels.ProductsCategoryViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

public class ProductsOfCategoryActivity extends AppCompatActivity {
    RecyclerView recyclerProductsOfCategory;
    ProductsCategoryViewModel productsCategoryViewModel;
    ProductsOfBrandAdapter productsOfCategoryAdapter;

    int my_page=1;
    List<ProductsOfBrandListModel> productsOfCategoryDataList;
    private KProgressHUD kProgressHUD;
    private  ProductsOfCategoryActivity productsOfCategoryActivity;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_of_category);

        init();
        kProgressHUD= AppFunctions.getKProgressHUD(this);
        productsOfCategoryDataList=new ArrayList<>();
        productsCategoryViewModel= ViewModelProviders.of(this).get(ProductsCategoryViewModel.class);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerProductsOfCategory.setLayoutManager(gridLayoutManager);
        recyclerProductsOfCategory.setItemAnimator(new DefaultItemAnimator());
        productsOfCategoryAdapter=new ProductsOfBrandAdapter(this,productsOfCategoryDataList);
        recyclerProductsOfCategory.setAdapter(productsOfCategoryAdapter);
        recyclerProductsOfCategory.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                my_page++;
                getProductsOfCategory(my_page);
            }
        });

        getProductsOfCategory(my_page);
    }

    private void getProductsOfCategory(int page) {
        kProgressHUD.show();
        productsCategoryViewModel.getProductsOfCategoryList(this,16,
                page,10).observe(this, productsOfBrandListModels -> {
            kProgressHUD.dismiss();
            productsOfCategoryDataList.addAll(productsOfBrandListModels);
            productsOfCategoryAdapter.notifyItemRangeInserted(productsOfCategoryAdapter.getItemCount(),productsOfCategoryDataList.size());
        });
    }

    private void init() {
        recyclerProductsOfCategory=findViewById(R.id.recyclerProductsOfCategory);
    }
}
