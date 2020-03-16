package com.ibsvalleyn.outlet.fragments.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.MainActivity;
import com.ibsvalleyn.outlet.activities.SigningActivity;
import com.ibsvalleyn.outlet.activities.SigningContainerActivity;
import com.ibsvalleyn.outlet.adapters.MyOrdersAdapter;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.helper.Static_variables;
import com.ibsvalleyn.outlet.models.Orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.ibsvalleyn.outlet.activities.MainActivity.postion;

public class MyOrdersFragment extends Fragment {
    RecyclerView myOrdersRecyclerView;
    GridLayoutManager layoutManager;
    MyOrdersAdapter myOrdersAdapter;
    SharedPreferences dataSaver;
    LinearLayout noOrders;
    int id;
    private MainActivity activity;
    TextView go_home;
    private String device_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        dataSaver = getDefaultSharedPreferences(activity);
        device_id = dataSaver.getString(Static_variables.DEVICE_ID, "");
        assert device_id != null;
        if (!device_id.equals("")&&postion!=0) {
            Intent intent = new Intent(activity, SigningContainerActivity.class);
            intent.putExtra("positionProfile",5);

            startActivity(intent);
            activity.finish();

        }else{
            postion=1;
        }

            Log.i("onCreate:", "onCreate: ");
        }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_oders_fragment, container, false);


        dataSaver.edit()
                .putInt("item", 0)
                .apply();

        id = dataSaver.getInt(Static_variables.CUSTOMER_ID, 0);
        noOrders = view.findViewById(R.id.no_order);
        myOrdersRecyclerView = view.findViewById(R.id.my_orders_recycler);
        layoutManager = new GridLayoutManager(activity, 1);
        myOrdersRecyclerView.setLayoutManager(layoutManager);
        myOrdersRecyclerView.setAdapter(myOrdersAdapter);
        go_home = view.findViewById(R.id.go_home);

        getUserProfile();
        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });
        return view;

    }


    public void getUserProfile() {
        Call<List<Orders>> addEVEnt_call = RetrofitClient.getInstance(activity)
                .getOrders(id);
        addEVEnt_call.enqueue(new Callback<List<Orders>>() {

            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {

                if (response.isSuccessful()) {
                    Log.e("TAG", "isSuccessful");
                    if (response.body().size() == 0) {
                        noOrders.setVisibility(View.VISIBLE);
                    } else {
                        noOrders.setVisibility(View.GONE);
                        myOrdersAdapter = new MyOrdersAdapter(activity, response.body());
                        myOrdersRecyclerView.setAdapter(myOrdersAdapter);
                    }

                } else {
                    Log.e("TAG", "notSuccessful");
                }

            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
                Log.e("TAG ", "onFailure");
                Toast.makeText(activity, "حدث خطأ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    postion=0;

    }
}