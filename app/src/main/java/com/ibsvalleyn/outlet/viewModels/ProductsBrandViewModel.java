package com.ibsvalleyn.outlet.viewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.CountryListModel;
import com.ibsvalleyn.outlet.models.ProductsOfBrandListModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductsBrandViewModel extends ViewModel {
    Context context;
    private MutableLiveData<List<ProductsOfBrandListModel>> productsOfBrandMutableLiveData;
    MutableLiveData<String> errorProductsOfBrandMutableLivedata= new MutableLiveData<>();

    private static final String TAG = "ProductsBrandViewModel";
    public LiveData<List<ProductsOfBrandListModel>> getProductsOfBrandList(Context context,Integer BrandId
    ,Integer PageNumber,Integer RowCountPerPage) {
      //  if (productsOfBrandMutableLiveData == null) {
            productsOfBrandMutableLiveData = new MutableLiveData<List<ProductsOfBrandListModel>>();
            this.context = context;
            getProductsOfBrandListValues(BrandId,PageNumber,RowCountPerPage);

      //  }
        return productsOfBrandMutableLiveData;


    }
    private void getProductsOfBrandListValues(Integer brandId
            ,Integer pageNumber,Integer rowCountPerPage) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("BrandId",String.valueOf(brandId));
        hashMap.put("PageNumber",String.valueOf(pageNumber));
        hashMap.put("RowCountPerPage",String.valueOf(rowCountPerPage));
        Single<List<ProductsOfBrandListModel>> observable= RetrofitClient.getInstance(context)
                .getProductsOfBrandList(brandId,pageNumber,rowCountPerPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o->productsOfBrandMutableLiveData.setValue(o),e-> Log.d(TAG, "getProductsOfBrandListValues: "+e));
    }
}
