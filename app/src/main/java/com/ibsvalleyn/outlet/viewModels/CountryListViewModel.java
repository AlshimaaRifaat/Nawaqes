package com.ibsvalleyn.outlet.viewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibsvalleyn.outlet.api.Api;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.CityListModel;
import com.ibsvalleyn.outlet.models.CountryListModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListViewModel extends ViewModel {
    Context context;
    private MutableLiveData<List<CountryListModel>> CountryListMutableLiveData;
    MutableLiveData<String> errorMutableLivedata= new MutableLiveData<>();
    private static final String TAG = "CountryListViewModel";

    private MutableLiveData<List<CityListModel>> cityListMutableLiveData;
    MutableLiveData<String> cityErrorMutableLivedata= new MutableLiveData<>();
    public LiveData<List<CountryListModel>> getCountryList( Context context) {
        if (CountryListMutableLiveData == null) {
            CountryListMutableLiveData = new MutableLiveData<List<CountryListModel>>();
            this.context = context;
            getCountryListValues();

        }
        return CountryListMutableLiveData;


    }
    private void getCountryListValues() {
        Single<List<CountryListModel>> observable=RetrofitClient.getInstance(context).getCountryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o->CountryListMutableLiveData.setValue(o),e-> Log.d(TAG, "getCountryListValues: "+e));
    }
    public LiveData<List<CityListModel>> getCityList( Context context,Integer Country_id) {
        if (cityListMutableLiveData == null) {
            cityListMutableLiveData = new MutableLiveData<List<CityListModel>>();
            this.context = context;
            getCityListValues(Country_id);

        }
        return cityListMutableLiveData;
    }
    private void getCityListValues(Integer country_id) {
        HashMap<String, String> hashMap = new HashMap<>();
        String Country_id=String.valueOf(country_id);
        hashMap.put("country_id",Country_id );
        Single<List<CityListModel>> observable=RetrofitClient.getInstance(context).getCityList(country_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(c->cityListMutableLiveData.setValue(c),ce-> Log.d(TAG, "getCountryListValues: "+ce));
    }
}
