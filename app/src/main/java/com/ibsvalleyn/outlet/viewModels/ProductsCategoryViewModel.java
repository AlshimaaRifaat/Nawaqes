package com.ibsvalleyn.outlet.viewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.ProductsOfBrandListModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductsCategoryViewModel extends ViewModel {
    Context context;
    private MutableLiveData<List<ProductsOfBrandListModel>> productsOfCategoryMutableLiveData;
    MutableLiveData<String> errorProductsOfCategoryMutableLivedata= new MutableLiveData<>();

    private static final String TAG = "ProductsCategoryViewMod";
    public LiveData<List<ProductsOfBrandListModel>> getProductsOfCategoryList(Context context, int Category_Id
            , int Page_Number, int Row_CountPerPage) {
        //  if (productsOfBrandMutableLiveData == null) {
        productsOfCategoryMutableLiveData = new MutableLiveData<List<ProductsOfBrandListModel>>();
        this.context = context;
        getProductsOfCategoryListValues(Category_Id,Page_Number,Row_CountPerPage);

        //  }
        return productsOfCategoryMutableLiveData;


    }
    private void getProductsOfCategoryListValues(int CategoryId
            ,Integer PageNumber,Integer RowCountPerPage) {
        Single<List<ProductsOfBrandListModel>> observable= RetrofitClient.getInstance(context)
                .getProductsOfCategoryList(CategoryId,PageNumber,RowCountPerPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o->productsOfCategoryMutableLiveData.setValue(o),e-> Log.d(TAG, "getProductsOfCategoryListValues: "+e));
    }
}
