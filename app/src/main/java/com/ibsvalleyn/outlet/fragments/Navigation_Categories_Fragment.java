package com.ibsvalleyn.outlet.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.MainActivity;
import com.ibsvalleyn.outlet.adapters.BrandsAdapter;
import com.ibsvalleyn.outlet.adapters.CategoriesAdapter;
import com.ibsvalleyn.outlet.viewModels.MainBrandsListViewModel;
import com.ibsvalleyn.outlet.viewModels.MainCategoriesViewModel;

public class Navigation_Categories_Fragment extends Fragment {
    RecyclerView offersRecyclerView;
    GridLayoutManager layoutManager;
    CategoriesAdapter offersAdapter;
    MainActivity activity;
    private SharedPreferences dataSaver;
    private int CUSTOMER_id;
    int total_quantity;

    private String offers;
    MainCategoriesViewModel mainCategoriesViewModel;

    View view;
    RecyclerView recyclerMainCategories, recycler_brands;
    private MainBrandsListViewModel mainBrandsListViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.navigation_categories_fragment, container, false);
        activity = (MainActivity) getActivity();
        init();
        getMainCategoriesList();
        getBrandsList();
      /*  dataSaver = getDefaultSharedPreferences(activity);
        total_quantity = dataSaver.getInt("abdo", 0);
        offers = dataSaver.getString("offers", "");


        CUSTOMER_id = dataSaver.getInt(Static_variables.CUSTOMER_ID, 0);
        //offersRecyclerView = view.findViewById(R.id.offers_recycler);
        layoutManager = new GridLayoutManager(activity, 2);
        offersRecyclerView.setLayoutManager(layoutManager);

        if (!offers.equals("")) {

            metaModel = ViewModelProviders.of(this).get(OffersViewMetaModel.class);
            metaModel.setContext(activity);
            metaModel.getOffers(offers).observe(activity, heroList -> {
                offersAdapter = new CategoriesAdapter(activity, heroList,activity.getDb());
                offersRecyclerView.setAdapter(offersAdapter);
                dataSaver.edit()
                        .putString("offers", "")
                        .apply();
                if (activity.getNotificationsBadge1() != null) {
                    activity.getNotificationsBadge1().setText("" + total_quantity);
                }
            });
        } else {
            model = ViewModelProviders.of(this).get(OffersViewModel.class);
            model.setContext(activity);
            model.getOffers().observe(activity, heroList -> {
                offersAdapter = new CategoriesAdapter(activity, heroList,activity.getDb());
                offersRecyclerView.setAdapter(offersAdapter);

                if (activity.getNotificationsBadge1() != null) {
                    activity.getNotificationsBadge1().setText("" + total_quantity);
                }


            });
        }

*/
        return view;
    }

    private void getBrandsList() {

        mainBrandsListViewModel = ViewModelProviders.of(this).get(MainBrandsListViewModel.class);
        mainBrandsListViewModel.mainBrandsModelLiveData().observe(activity, heroList -> {

            recycler_brands.setLayoutManager(new GridLayoutManager(getContext(), 3));
            BrandsAdapter brandsAdapter = new BrandsAdapter(getContext(), heroList);
            recycler_brands.setAdapter(brandsAdapter);

        });
    }

    private void getMainCategoriesList() {

        mainCategoriesViewModel = ViewModelProviders.of(activity).get(MainCategoriesViewModel.class);

        mainCategoriesViewModel.mainCategoriesModelLiveData().observe(activity, heroList -> {

            recyclerMainCategories.setLayoutManager(new GridLayoutManager(getContext(), 3));
            CategoriesAdapter brandsAdapter = new CategoriesAdapter(getContext(), heroList);
            recyclerMainCategories.setAdapter(brandsAdapter);
        });
    }

    private void init() {
        recyclerMainCategories = view.findViewById(R.id.recyclerMainCategories);
        recycler_brands = view.findViewById(R.id.recycler_brands);
    }

}