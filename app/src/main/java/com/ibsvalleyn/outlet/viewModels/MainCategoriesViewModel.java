package com.ibsvalleyn.outlet.viewModels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.MainCategoriesModel;
import com.ibsvalleyn.outlet.models.ShoppingCarts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCategoriesViewModel extends AndroidViewModel {


    private MutableLiveData<List<MainCategoriesModel>> mainCategoriesModelMutableLiveData;

    public MainCategoriesViewModel(@NonNull Application application) {
        super(application);
    }


    //we will call this method to get the data
    public LiveData<List<MainCategoriesModel>> mainCategoriesModelLiveData() {
        //if the list is null
        if (mainCategoriesModelMutableLiveData == null) {
            mainCategoriesModelMutableLiveData = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadMyCart();
        }

        //finally we will return the list
        return mainCategoriesModelMutableLiveData;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadMyCart() {

        Call<List<MainCategoriesModel>> addEVEnt_call = RetrofitClient.getInstance(getApplication())
                .getMainCategories();
        addEVEnt_call.enqueue(new Callback<List<MainCategoriesModel>>() {

            @Override
            public void onResponse(Call<List<MainCategoriesModel>> call, Response<List<MainCategoriesModel>> response) {

                if (response.isSuccessful()) {
                    //finally we are setting the list to our MutableLiveData
                    mainCategoriesModelMutableLiveData.setValue(response.body());
                    Log.i("asddsa", "onResponse: " + new Gson().toJson(response.body()));

                }
            }

            @Override
            public void onFailure(Call<List<MainCategoriesModel>> call, Throwable t) {

            }


        });
    }
}


