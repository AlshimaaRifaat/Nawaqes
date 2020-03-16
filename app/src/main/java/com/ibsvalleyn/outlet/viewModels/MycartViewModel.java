package com.ibsvalleyn.outlet.viewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.CartModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MycartViewModel extends ViewModel {

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    private MutableLiveData<List<CartModel>> myCart;

    //we will call this method to get the data
    public LiveData<List<CartModel>> getMyCart(int id) {
        //if the list is null
        if (myCart == null) {
            myCart = new MutableLiveData<>();
            //we will load it asynchronously from server in t   his method
            loadMyCart(id);
        }

        //finally we will return the list
        return myCart;
    }

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    //This method is using Retrofit to get the JSON data from URL
    private void loadMyCart(int id) {

        Observable<List<CartModel>> observable = RetrofitClient.getInstance(context)
                .getCart1(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer<List<CartModel>> observer = new Observer<List<CartModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<CartModel> shoppingCarts) {
                myCart.setValue(shoppingCarts);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        compositeDisposable.add(observable.subscribe(o -> {
            myCart.setValue(o);

        }, e -> Log.e("tag", "onError" + e)));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}


