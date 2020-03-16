package com.ibsvalleyn.outlet.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.GetWhilstModel;
import com.ibsvalleyn.outlet.models.Wishlists;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistViewModel extends ViewModel {

    private Context context;

    private MutableLiveData<List<GetWhilstModel>> wishList;
    //we will call this method to get the data
    public LiveData<List<GetWhilstModel>> getWishlist(int id , Context context) {
        //if the list is null
        if (wishList == null) {
            wishList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadWishlist(id, context);
        }
        //finally we will return the list
        return wishList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadWishlist(int id , Context context) {

        Call<List<GetWhilstModel>> addEVEnt_call = RetrofitClient.getInstance(context)
                .getWishlist(1055);
        addEVEnt_call.enqueue(new Callback<List<GetWhilstModel>>() {

            @Override
            public void onResponse(Call<List<GetWhilstModel>> call, Response<List<GetWhilstModel>> response) {

                if (response.isSuccessful()) {
                    //finally we are setting the list to our MutableLiveData
                    wishList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GetWhilstModel>> call, Throwable t) {

            }


        });
    }
}
