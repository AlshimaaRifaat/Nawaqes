package com.ibsvalleyn.outlet.fragments.splash;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.MainActivity;
import com.ibsvalleyn.outlet.activities.SplashScreen;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.data_room.AppDatabase;
import com.ibsvalleyn.outlet.helper.Static_variables;
import com.ibsvalleyn.outlet.models.FavoriteModel;
import com.ibsvalleyn.outlet.models.Guest_session;
import com.ibsvalleyn.outlet.viewModels.WishlistViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.facebook.GraphRequest.TAG;
import static com.ibsvalleyn.outlet.helper.AppFunctions.StackFragment;

public class SplashImageFragment extends Fragment {

    private ImageView ImageFrom;
    private SplashScreen activity;
    SharedPreferences dataSaver;
    int id;
    private String DeviceId;
    private String langSplash;
    private WishlistViewModel model;
    private List<FavoriteModel> favoriteModelList;
    private AppDatabase db;
    private FavoriteModel favoriteModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (SplashScreen) getActivity();
        db = AppDatabase.getInstance(activity);

        assert activity != null;
        @SuppressLint
                ("HardwareIds") final String android_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("DeviceId", android_id);
        DeviceId = android_id;


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash_image_fragment, container, false);
        ImageFrom = view.findViewById(R.id.image);
        dataSaver = getDefaultSharedPreferences(activity);
        langSplash = dataSaver.getString(Static_variables.Lang, "");
        id = dataSaver.getInt(Static_variables.CUSTOMER_ID, 0);
        Log.e("splash_id","splash_id "+id);
        (new Handler()).postDelayed((() -> {
                    if (id != 0) {
                        startActivity(new Intent(activity, MainActivity.class));
                        activity.finish();
                    } else {
                        getGentSession();
                    }
                })
                , 2000L);

        return view;
    }


    private void getGentSession() {
        Call<Guest_session> addEVEnt_call = RetrofitClient.getInstance(activity)
                .GUEST_SESSION_CALL(DeviceId);
        addEVEnt_call.enqueue(new Callback<Guest_session>() {

            @Override
            public void onResponse(@NotNull Call<Guest_session> call, @NotNull Response<Guest_session> response) {

                if (response.isSuccessful()) {
                    Log.e("TAG", "isSuccessful_guest");
                    assert response.body() != null;
                    if (response.body().getResult()) {

                        Log.i(TAG, "onResponse: " + response.body().getNewId());
                        int customer_id = response.body().getNewId();
                        dataSaver.edit()
                                .putInt(Static_variables.CUSTOMER_ID, customer_id).apply();
                        dataSaver.edit()
                                .putString(Static_variables.DEVICE_ID, DeviceId).apply();
                        StackFragment(new SplashLanguageFragment(), activity, R.id.frameLayout_container);
                        Log.i(TAG, "customer_id " + customer_id);

                        //Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(activity, customer_id + "", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(activity, response.body().getUserMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "isSuccessful_guest_error");

                    }

                } else {
                    //   Toast.makeText(activity, "notSuccessful", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<Guest_session> call, Throwable t) {
                Log.e("TAG ", "onFailure5");
                Toast.makeText(activity, "حدث خطأ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}