package com.ibsvalleyn.outlet.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.AccordionTransformer;
import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.MainActivity;
import com.ibsvalleyn.outlet.activities.ProductOfCategories;
import com.ibsvalleyn.outlet.activities.SearchResultActivity;
import com.ibsvalleyn.outlet.adapters.CategoryAdapter;
import com.ibsvalleyn.outlet.adapters.OffersAdapter;
import com.ibsvalleyn.outlet.adapters.ProductCollectionAdapter;
import com.ibsvalleyn.outlet.adapters.ProductSectorsAdapter;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.helper.Static_variables;
import com.ibsvalleyn.outlet.models.Banners;
import com.ibsvalleyn.outlet.models.Products;
import com.ibsvalleyn.outlet.models.Sectors;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.ibsvalleyn.outlet.helper.AppFunctions;
import com.ibsvalleyn.outlet.viewModels.HomeBrandsViewModel;
import com.ibsvalleyn.outlet.viewModels.OffersViewMetaModel;
import com.ibsvalleyn.outlet.viewModels.OffersViewModel;
import com.ibsvalleyn.outlet.viewModels.ProductCollectionViewModel;
import com.ibsvalleyn.outlet.viewModels.ProductSectorsViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.Color.*;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.ibsvalleyn.outlet.activities.MainActivity.Counter;

public class Content_Home_Fragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "RegisterAdasdasdctivity";
    List<Integer> subcats_id = new ArrayList<>();
    List<String> sectors_name = new ArrayList<>();
    String sector_name;
    private FrameLayout mFrame;
    TextView noData, category_name, category_name1,
            category_name2, category_name3, category_name4, category_nam;
    ImageView main_image, capture3, capture2, capture5, capture6, capture7, banner1, banner2;
    private TabLayout category_tabs;
    LinearLayout container_linear;
    List<Integer> idList = new ArrayList<>();
    SharedPreferences dataSaver;
    RelativeLayout capture5_relative, capture7_relative;
    LinearLayout capture6_relative;
    int idSubCat, customer_id;
    RecyclerView productRecyclerView;
    GridLayoutManager layoutManager;
    ProductSectorsAdapter productAdapter;
    MainActivity activity;
    private ImageView search;
    private List<Products> products = new ArrayList<>();
    private KProgressHUD kProgressHUD;
    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private List<Sectors> body;
    int p = 0;
    private int sectorPosition;
    private TextSliderView textSliderView;
    String sector_name1, sector_name2, sector_name3, sector_name4, sector_name5;
    RelativeLayout relative1, relative2;
    private SliderLayout slider;
    ProductSectorsViewModel model;
    HomeBrandsViewModel modelBrands;
    PagerIndicator custom_indicator;
    private RelativeLayout relativeLayout_slider;
    private ImageView main_imageslider;
    private TextView category_namslider;
    ProgressBar progressBar;
    private int pageNumber = 1;
    boolean isLoading = true;
    int pastVisibleItem = 0, visibleItemCount = 0, totalItemCount = 0, previousTotal = 0;
    int viewThreshold = 10;
    NestedScrollView nested_scroll_view;
    private RecyclerView rvBrands;
    private GridLayoutManager layoutManager1, layoutManager2;
    private RelativeLayout main_image_banner;
    private RecyclerView rvOffers;
    private OffersViewMetaModel metaModel;
    private ProductCollectionAdapter collectionAdapter;
    private ProductCollectionViewModel model1;
    private LinearLayoutManager layoutManager3,layoutManager4;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        kProgressHUD = AppFunctions.getKProgressHUD(activity);
        body = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_home_fragment, container, false);
        dataSaver = getDefaultSharedPreferences(activity);
        p = dataSaver.getInt("position", 0);
        customer_id = dataSaver.getInt(Static_variables.CUSTOMER_ID, 0);
        initViews(view);
        search = view.findViewById(R.id.search);
        search.setOnClickListener(view1 -> ShowPopUp(search));
        dataSaver = getDefaultSharedPreferences(activity);
        customer_id = dataSaver.getInt(Static_variables.CUSTOMER_ID, 0);
//        activity.runOnUiThread(() -> sector_id = dataSaver.getInt(Static_variables.Sector_id, 0));
        Calligrapher calligrapher = new Calligrapher(activity);
        calligrapher.setFont(activity, "Ubuntu-R.ttf", true);
        slider = view.findViewById(R.id.slider);
        slider.setBackground(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        slider.setDuration(4000);
        slider.setPagerTransformer(false, new AccordionTransformer());
        slider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
        slider.setCustomIndicator(view.findViewById(R.id.custom_indicator));
        model = ViewModelProviders.of(this).get(ProductSectorsViewModel.class);
        modelBrands = ViewModelProviders.of(this).get(HomeBrandsViewModel.class);
        modelBrands.setContext(activity);
        model.setContext(activity);
        getBrands();

        return view;
    }


    private void initViews(View view) {
        main_image_banner = view.findViewById(R.id.main_image_banner);
        category_name = view.findViewById(R.id.category_name);
        category_name1 = view.findViewById(R.id.category_name1);
        category_name2 = view.findViewById(R.id.category_name2);
        category_name3 = view.findViewById(R.id.category_name3);
        category_name4 = view.findViewById(R.id.category_name4);
        relativeLayout_slider = view.findViewById(R.id.relativeLayout_slider);
        main_imageslider = view.findViewById(R.id.main_imageslider);
        category_namslider = view.findViewById(R.id.category_namslider);
        relative1 = view.findViewById(R.id.relative1);
        relative2 = view.findViewById(R.id.relative2);
        category_nam = view.findViewById(R.id.category_nam);
        layoutManager = new GridLayoutManager(activity, 2);
        productRecyclerView = view.findViewById(R.id.product_recycler);
        productRecyclerView.setLayoutManager(layoutManager);
        main_image = view.findViewById(R.id.main_image);
        capture2 = view.findViewById(R.id.capture2);
        capture3 = view.findViewById(R.id.capture3);
        banner1 = view.findViewById(R.id.banner1);
        banner2 = view.findViewById(R.id.banner2);
        capture5 = view.findViewById(R.id.capture5);
        capture6 = view.findViewById(R.id.capture6);
        capture7 = view.findViewById(R.id.capture7);
        progressBar = view.findViewById(R.id.progressBar);
        nested_scroll_view = view.findViewById(R.id.nested_scroll_view);


        capture5_relative = view.findViewById(R.id.capture5_relative);
        capture6_relative = view.findViewById(R.id.capture6_relative);
        capture7_relative = view.findViewById(R.id.capture7_relative);
        container_linear = view.findViewById(R.id.container_linear);
        rvBrands = view.findViewById(R.id.rvCategory);
        rvOffers = view.findViewById(R.id.rvOffers);

        model1 = ViewModelProviders.of(this).get(ProductCollectionViewModel.class);
        model1.setContext(activity);

        layoutManager3 = new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false);
        rvOffers.setLayoutManager(layoutManager3);

        model1.getProductsCollection().observe(activity, heroList1 -> {
            collectionAdapter = new ProductCollectionAdapter(activity, heroList1, activity.getDb());
            rvOffers.setAdapter(collectionAdapter);


        });


//        layoutManager1 = new GridLayoutManager(activity, 3);
//        rvCategory.setLayoutManager(layoutManager1);

//        main_image.setOnClickListener(this);
//        capture2.setOnClickListener(this);
//        capture3.setOnClickListener(this);
//        capture5.setOnClickListener(this);
//        capture6.setOnClickListener(this);
//        capture7.setOnClickListener(this);

//        banner1.setOnClickListener(view1 -> {
//            activity.getmBottomNavigation().setSelectedItemId(R.id.navigation_offers);
//            dataSaver.edit()
//                    .putString("offers", "")
//                    .apply();
//
//        });

        Call<List<Sectors>> addEVEnt_call = RetrofitClient.getInstance(activity)
                .getSectors(1000);

        addEVEnt_call.enqueue(new Callback<List<Sectors>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Sectors>> call, Response<List<Sectors>> response) {
                if (response.isSuccessful()) {
                    Log.e("TAG", "isSuccessful" + new Gson().toJson(response.body()));

                    assert response.body() != null;

                    model.getProducts(100, 14, pageNumber, 6).observe(activity, heroList -> {
                        productRecyclerView.setVisibility(View.VISIBLE);
                        productAdapter = new ProductSectorsAdapter(activity, heroList.getProducts(), Counter, activity.getDb());
                        productRecyclerView.setAdapter(productAdapter);
                        getBanners(14);
                        Log.e("TAG", "notSuccessful" + new Gson().toJson(heroList.getCategoriesList()));
                        productRecyclerView.setNestedScrollingEnabled(false);

//                        productRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                            @Override
//                            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
//                                super.onScrolled(recyclerView, dx, dy);
//                                visibleItemCount = layoutManager.getChildCount();
//                                totalItemCount = layoutManager.getItemCount();
//                                pastVisibleItem = layoutManager.findFirstVisibleItemPosition();
//                                Log.e("TAG", "dy " + dy);
//                                // dy = dy+1;
//                                if (dy > 0) {
//                                    if (isLoading) {
//                                        if (totalItemCount > previousTotal) {
//                                            isLoading = false;
//                                            previousTotal = totalItemCount;
//                                        }
//                                    }
//                                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItem + viewThreshold)) {
//                                        Log.e("TAG", "performPagination()");
//
//                                        pageNumber++;
//                                        model.getProducts(100, position, customer_id, pageNumber, 16).observe(activity, heroList1 -> {
//                                            productAdapter.addInRecycle(heroList1.getProducts());
//
//                                        });
//                                        isLoading = true;
//
//                                        Log.e("TAG", "pageNumber " + pageNumber);
//
//                                    }
//                                }
//                            }
//                        });
//
//                        });

                        nested_scroll_view.setOnScrollChangeListener(((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                            visibleItemCount = layoutManager.getChildCount();
                            totalItemCount = layoutManager.getItemCount();
                            pastVisibleItem = layoutManager.findFirstVisibleItemPosition();
                            Log.e("TAG", "dy " + scrollY);
                            Log.e("TAG", "dy " + oldScrollY);
                            // dy = dy+1;
                            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                                if (isLoading) {
                                    if (totalItemCount > previousTotal) {
                                        isLoading = false;
                                        previousTotal = totalItemCount;
                                    }
                                }
                                if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItem + viewThreshold)) {
                                    Log.e("TAG", "performPagination()");

                                    pageNumber++;
                                    model.getProducts(100, 14, pageNumber, 16).observe(activity, heroList1 -> {
                                        productAdapter.addInRecycle(heroList1.getProducts());

                                    });
                                    isLoading = true;

                                    Log.e("TAG", "pageNumber " + pageNumber);

                                }
                            }

                        }
                        ));

                    });
                } else {
                    Log.e("TAG", "notSuccessful");
                    Log.e("TAG", "notSuccessful" + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Sectors>> call, Throwable t) {
                Log.e("TAG ", "onFailure " + t.getMessage());
            }
        });

    }


    public void getBrands() {


        modelBrands.getHomeBrands().observe(activity, brandsModels -> {

            layoutManager4 = new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false);

            rvBrands.setLayoutManager(layoutManager4);
            rvBrands.setAdapter(new CategoryAdapter(activity, brandsModels));


        });


    }

    private void getBanners(int i) {

        Call<Banners> addEVEnt_call = RetrofitClient.getInstance(activity)
                .getBanners(i, 1);
        addEVEnt_call.enqueue(new Callback<Banners>() {
            @Override
            public void onResponse(Call<Banners> call, Response<Banners> response) {

                if (response.isSuccessful()) {
                    Log.e("TAG", "isSuccessful");
                    Log.e(TAG, "onResponse2KKKKKKKKKKk: " + new Gson().toJson(response.body()));

                    assert response.body() != null;
                    if (response.body().getPromoBanner() != null) {
//                        banner1.setVisibility(View.VISIBLE);
//                        banner1.setOnClickListener(view -> {
//                            activity.getmBottomNavigation().setSelectedItemId(R.id.navigation_offers);
//                            dataSaver.edit()
//                                    .putString("offers", response.body().getPromoBanner().getBannerType())
//                                    .apply();
//
//                        });

//                        Glide.with(getApplicationContext()).load(response.body().getPromoBanner().getPictureUrl()).into(banner1);
                    } else {
                        banner1.setVisibility(View.GONE);
                    }
                    if (response.body().getMainBanners().size() != 0) {
                        if (response.body().getMainBanners().size() == 1) {
                            main_image_banner.setVisibility(View.VISIBLE);
                            Glide.with(getApplicationContext()).load(response.body().getMainBanners().get(0).getPictureUrl()).into(main_image);
                            main_image.setOnClickListener(view -> {

                                activity.getmBottomNavigation().setSelectedItemId(R.id.navigation_offers);
                                dataSaver.edit()
                                        .putString("offers", response.body().getMainBanners().get(0).getMetaTitle())
                                        .apply();

                            });

                        } else {

                            main_image_banner.setVisibility(View.GONE);

                            slider.setVisibility(View.VISIBLE);
                            for (int i = 0; i < response.body().getMainBanners().size(); i++) {

                                TextSliderView demoSlider = new TextSliderView(activity);
                                int categoryId = response.body().getMainBanners().get(i).getBannerId();
                                // String aUrl = homeModel.getCategoriesList().get(i).getPictureurl().replace("http", "https");

                                // demoSlider.getView().findViewById(SliderLayout.ALIGN_END);
                                int finalI = i;
                                demoSlider
                                        .image(response.body().getMainBanners().get(i).getPictureUrl())
                                        .setOnSliderClickListener(slider1 -> {
                                            activity.getmBottomNavigation().setSelectedItemId(R.id.navigation_offers);
                                            dataSaver.edit()
                                                    .putString("offers", response.body().getMainBanners().get(finalI).getMetaTitle())
                                                    .apply();

                                        })
                                        .setScaleType(BaseSliderView.ScaleType.Fit);

                                slider.addSlider(demoSlider);
                            }
                        }


                    } else {
                        main_image_banner.setVisibility(View.GONE);
                        slider.setVisibility(View.GONE);
                    }

                    if (response.body().getSubBanner() != null) {
                        banner2.setVisibility(View.VISIBLE);

                        banner2.setOnClickListener(view -> {

                            activity.getmBottomNavigation().setSelectedItemId(R.id.navigation_offers);
                            dataSaver.edit()
                                    .putString("offers", response.body().getSubBanner().getMetaTitle())
                                    .apply();
                        });
                        Glide.with(getApplicationContext()).load(response.body().getSubBanner().getPictureUrl()).into(banner2);

                    } else {
                        banner2.setVisibility(View.GONE);
                    }
                } else {
                    Log.e("TAG", "notSuccessful");
                    Log.e("TAG", "notSuccessful" + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Banners> call, Throwable t) {
                Log.e("TAG ", "onFailure " + t.getMessage());
                //  Toast.makeText(activity, "حدث خطأ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowPopUp(View v) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        final EditText searchEt;
        final PopupWindow popupWindow;

        LayoutInflater layoutInflater = getLayoutInflater();
        final View popupView = layoutInflater.inflate(R.layout.popup_layout, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(
                TRANSPARENT));
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        searchEt = popupView.findViewById(R.id.searchEt);
        searchEt.requestFocus();
        // searchEt.setSelectAllOnFocus(true);
        ImageView search_btn1 = popupView.findViewById(R.id.search_btn1);
        search_btn1.setOnClickListener(view -> {
            String SearchWord = searchEt.getText().toString();
            if (SearchWord.length() >= 3) {
                Intent intent = new Intent(activity, SearchResultActivity.class);
                intent.putExtra("SearchWord", SearchWord);
                intent.putExtra("idSubCat", String.valueOf(idSubCat));
                popupWindow.dismiss();

                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                activity.startActivity(intent);

            } else {
                Toast.makeText(activity, activity.getResources().getString(R.string.Pleasecharacters), Toast.LENGTH_SHORT).show();
            }
        });
        searchEt.setOnEditorActionListener((v1, actionId, event) -> {

            String SearchWord = searchEt.getText().toString();
            if (SearchWord.length() >= 3) {
                Intent intent = new Intent(activity, SearchResultActivity.class);
                intent.putExtra("SearchWord", SearchWord);
                intent.putExtra("idSubCat", String.valueOf(idSubCat));
                popupWindow.dismiss();

                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                activity.startActivity(intent);

            } else {
                Toast.makeText(activity, activity.getResources().getString(R.string.Pleasecharacters), Toast.LENGTH_SHORT).show();
            }
            Log.i("sadasd", "ShowPopUp: " + idSubCat);

            return true;
        });

        popupWindow.showAsDropDown(v);
    }

    @Override
    public void onClick(View view) {
        if (view == main_image) {
            if (idList.size() >= 1) {
                Intent intent = new Intent(activity, ProductOfCategories.class);
                intent.putExtra("id", idList.get(0));
                //intent.putExtra("sectors_name", sector_name);
                dataSaver.edit()
                        .putInt(Static_variables.Category_id, idList.get(0))
                        .apply();

                dataSaver.edit()
                        .putString("sectors_name", sector_name)
                        .apply();

                dataSaver.edit()
                        .putInt(Static_variables.Sector_id, idSubCat)
                        .apply();

                startActivity(intent);
                activity.finish();
            }
        }
        if (view == capture2) {
            if (idList.size() >= 2) {
                Intent intent = new Intent(activity, ProductOfCategories.class);
                intent.putExtra("id", idList.get(1));

                dataSaver.edit()
                        .putInt(Static_variables.Category_id, idList.get(1))
                        .apply();

                dataSaver.edit()
                        .putString("sectors_name", sector_name1)
                        .apply();

                dataSaver.edit()
                        .putInt(Static_variables.Sector_id, idSubCat)
                        .apply();

                startActivity(intent);
                activity.finish();

            }
        }
        if (view == capture3) {
            Intent intent = new Intent(activity, ProductOfCategories.class);
            if (idList.size() >= 3) {
                intent.putExtra("id", idList.get(2));

                dataSaver.edit()
                        .putInt(Static_variables.Category_id, idList.get(2))
                        .apply();

                dataSaver.edit()
                        .putString("sectors_name", sector_name2)
                        .apply();

                dataSaver.edit()
                        .putInt(Static_variables.Sector_id, idSubCat)
                        .apply();

                startActivity(intent);
                activity.finish();

            }
        }
        if (view == capture5) {
            if (idList.size() >= 4) {
                Intent intent = new Intent(activity, ProductOfCategories.class);
                intent.putExtra("id", idList.get(3));

                dataSaver.edit()
                        .putInt(Static_variables.Category_id, idList.get(3))
                        .apply();

                dataSaver.edit()
                        .putString("sectors_name", sector_name3)
                        .apply();

                dataSaver.edit()
                        .putInt(Static_variables.Sector_id, idSubCat)
                        .apply();

                startActivity(intent);
                activity.finish();

            }
        }
        if (view == capture6) {
            if (idList.size() >= 5) {
                Intent intent = new Intent(activity, ProductOfCategories.class);
                intent.putExtra("id", idList.get(4));
                intent.putExtra("id", "");

                dataSaver.edit()
                        .putInt(Static_variables.Category_id, idList.get(4))
                        .apply();

                dataSaver.edit()
                        .putString("sectors_name", sector_name4)
                        .apply();

                dataSaver.edit()
                        .putInt(Static_variables.Sector_id, idSubCat)
                        .apply();

                startActivity(intent);
                activity.finish();

            }
        }
        if (view == capture7) {
            if (idList.size() >= 6) {
                Intent intent = new Intent(activity, ProductOfCategories.class);
                intent.putExtra("id", idList.get(5));

                dataSaver.edit()
                        .putInt(Static_variables.Category_id, idList.get(5))
                        .apply();
                dataSaver.edit()
                        .putString("sectors_name", sector_name5)
                        .apply();
                dataSaver.edit()
                        .putInt(Static_variables.Sector_id, idSubCat)
                        .apply();

                startActivity(intent);
                activity.finish();

            }
        }
    }
}