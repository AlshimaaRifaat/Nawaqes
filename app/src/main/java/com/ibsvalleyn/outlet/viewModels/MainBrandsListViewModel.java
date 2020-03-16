package com.ibsvalleyn.outlet.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.MainBrandModel;
import com.ibsvalleyn.outlet.models.MainCategoriesModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainBrandsListViewModel extends AndroidViewModel {


    private MutableLiveData<List<MainBrandModel>> MainBrandModelMutableLiveData;

    public MainBrandsListViewModel(@NonNull Application application) {
        super(application);
    }


    //we will call this method to get the data
    public LiveData<List<MainBrandModel>> mainBrandsModelLiveData() {
        //if the list is null
        if (MainBrandModelMutableLiveData == null) {
            MainBrandModelMutableLiveData = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadMyCart();
        }

        //finally we will return the list
        return MainBrandModelMutableLiveData;
    }

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    //This method is using Retrofit to get the JSON data from URL
    private void loadMyCart() {


        Observable<List<MainBrandModel>> observable = RetrofitClient.getInstance(getApplication())
                .getMainBrands().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o -> {
            MainBrandModelMutableLiveData.setValue(o);

        }, e -> Log.e("tag", "onError" + e)));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}


