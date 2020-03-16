package com.ibsvalleyn.outlet.viewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.ProductCollection;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProductCollectionViewModel extends ViewModel {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    private MutableLiveData<List<ProductCollection>> homeBrands;

    public LiveData<List<ProductCollection>> getProductsCollection() {
        if (homeBrands == null) {
            homeBrands = new MutableLiveData<>();
            loadProductsCollection();
        }

        return homeBrands;
    }

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void loadProductsCollection() {

        Observable<List<ProductCollection>> observable = RetrofitClient.getInstance(context)
                .getProductsCollections("On Sale",1,200).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        compositeDisposable.add(observable.subscribe(o ->{ homeBrands.setValue(o);

        }, e -> Log.e("tag","onError"+ e)));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
