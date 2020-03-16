package com.ibsvalleyn.outlet.viewModels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.models.SignUp.SignUpModel;
import com.ibsvalleyn.outlet.models.SignUp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    private  MutableLiveData<SignUpModel> signUpMutableLiveData;
    MutableLiveData<String> signUpError =new MutableLiveData<>();
    Context context;
    public LiveData<SignUpModel> getSignUp(User user,Context context)
    {
        signUpMutableLiveData=new MutableLiveData<>();
        this.context=context;
        SignUp(user);
        return signUpMutableLiveData;
    }
    public void SignUp(User user){
       /* Map<String,String> map=new HashMap<>();
        map.put("CustomerId" ,user.getCustomerId());
        map.put("FirstName",user.getFirstName());
        map.put("LastName",user.getLastName());
        map.put("Email",user.getEmail());
        map.put("Phone",user.getPhone());
        map.put("Password",user.getPassword());
        map.put("GenderId",user.getGenderId());
        map.put("CountryId",user.getCountryId());
        map.put("CityId",user.getCityId());*/
        RetrofitClient.getInstance(context).userSignUp(user.getCustomerId(),user.getFirstName()
                ,user.getLastName(),user.getEmail(),user.getPhone(),user.getPassword(),user.getGenderId()
                ,user.getCountryId(),user.getCityId()).enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                if (response.code()==200)
                    signUpMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {
                signUpError.setValue(null);
                Toast.makeText(context, "Check network connection!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

