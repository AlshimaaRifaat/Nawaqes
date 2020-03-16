package com.ibsvalleyn.outlet.viewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.HomeBrandsModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeBrandsViewModel extends ViewModel {

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    private MutableLiveData<List<HomeBrandsModel>> homeBrands;

    public LiveData<List<HomeBrandsModel>> getHomeBrands() {
        if (homeBrands == null) {
            homeBrands = new MutableLiveData<>();
            loadHomeBrands();
        }

        return homeBrands;
    }

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void loadHomeBrands() {

        Observable<List<HomeBrandsModel>> observable = RetrofitClient.getInstance(context)
                .getBrands().subscribeOn(Schedulers.io())
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
