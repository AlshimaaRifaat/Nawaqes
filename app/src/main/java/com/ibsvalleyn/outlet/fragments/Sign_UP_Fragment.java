package com.ibsvalleyn.outlet.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.MainActivity;
import com.ibsvalleyn.outlet.activities.SigningActivity;
import com.ibsvalleyn.outlet.adapters.GenderSpinnerAdapter;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.helper.AppFunctions;
import com.ibsvalleyn.outlet.helper.GPSTracker;
import com.ibsvalleyn.outlet.helper.Static_variables;
import com.ibsvalleyn.outlet.models.CityListModel;
import com.ibsvalleyn.outlet.models.CountryListModel;
import com.ibsvalleyn.outlet.models.Guest_session;
import com.ibsvalleyn.outlet.models.SignUp.SignUpModel;
import com.ibsvalleyn.outlet.models.SignUp.User;
import com.ibsvalleyn.outlet.viewModels.CountryListViewModel;
import com.ibsvalleyn.outlet.viewModels.SignUpViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.facebook.GraphRequest.TAG;

public class Sign_UP_Fragment extends Fragment {
    Button register;
    EditText first_name, lastName, mobileNumber, email, password, confirmPassword;
    private KProgressHUD kProgressHUD;

    private SigningActivity activity;
    String First_name, LastName, MobileNumber, Email, Password, ConfirmPassword;
    // String first_name, last_name, pass_word, user_phone, password_con, user_mail;
    SharedPreferences dataSaver;
    private String Gmail;
    private String getEmail;
    private String getDisplayName;
    private String getPhoneNumber;
    private String emailSocial;
    private String fullName;
    private int itemId;
    private int id;
    private String city;
    private String country;
    private String DeviceId;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 555;
    private GPSTracker gpsTracker;
    //  RelativeLayout terms_us;

    SignUpViewModel signUpViewModel;
    CountryListViewModel countryListViewModel;
    View view;


    String[] SpinnerGenderValue = {
            "Male",
            "Female"
    };


    public static String countrySelectedItemSpinner, city_SelectedItemSpinner, gender_SelectedItemSpinner;
    public int country_id, city_id;
    String gender_id;

    Context context;
    Spinner genderSpinner, countrySpinner, citySpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (SigningActivity) getActivity();
        context = this.getActivity();
       /* gpsTracker = new GPSTracker(activity);


        assert activity != null;
        @SuppressLint
                ("HardwareIds") final String android_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("DeviceId", android_id);
        DeviceId = android_id;*/

//
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
//                    PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{
//                                android.Manifest.permission.ACCESS_FINE_LOCATION},
//                        REQUEST_CODE_ASK_PERMISSIONS);
//                return;
//            }
//
//        }


//        if (gpsTracker.canGetLocation()) {
//            double latitude = gpsTracker.getLatitude();
//            double longitude = gpsTracker.getLongitude();
//            sendNotificationData(latitude,longitude);
//
//
//            Log.i(TAG, "onCreate: "+ latitude+longitude);
//        }else {
//            gpsTracker.showSettingsAlert();
//        }


        getGentSession();

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_up_fragment, container, false);
        init();
        countryListViewModel = ViewModelProviders.of(this).get(CountryListViewModel.class);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        getGenderSpinnerItems();
        getCountrySpinnerItems();


        Bundle bundle = getArguments();

        assert bundle != null;
        kProgressHUD = AppFunctions.getKProgressHUD(activity);
//
//        if (getArguments().getInt("itemId") != 0) {
//            itemId = getArguments().getInt("itemId");
//            Log.i("ITEM_ID", " " + itemId);
//
//            switch (itemId) {
//                case 1:
//                    connectFacebook();
//                    break;
//                case 2:
//                    connectGoogle();
//                    break;
//                case 3:
//                    break;
//            }
        //}
        dataSaver = getDefaultSharedPreferences(activity);

//        phone = view.findViewById(R.id.phone);
//        terms_us = view.findViewById(R.id.terms_us);
//        terms_us.setOnClickListener(view1 -> {
//            Intent intent = new Intent(activity, AllActivity.class);
//            intent.putExtra("statics", "TERMS_OF_USE");
//            startActivity(intent);
//        });

        register.setOnClickListener(view2 -> SignUp());
        Calligrapher calligrapher = new Calligrapher(activity);
        calligrapher.setFont(activity, "Ubuntu-R.ttf", true);

        return view;
    }

    private void getCitySpinnerItems() {
        //   Toast.makeText(activity, String.valueOf(country_id), Toast.LENGTH_SHORT).show();
        countryListViewModel.getCityList(context, country_id).observe(this, new Observer<List<CityListModel>>() {
            @Override
            public void onChanged(List<CityListModel> cityListModels) {
                ArrayList<String> cityArrayList = new ArrayList<>();
                for (int i = 0; i < cityListModels.size(); i++) {
                    cityArrayList.add(cityListModels.get(i).getName());
                }
                GenderSpinnerAdapter CitySpinnerAdapter = new GenderSpinnerAdapter(getContext(), R.layout.spinner_item);
                CitySpinnerAdapter.addAll(cityArrayList);
                CitySpinnerAdapter.add("City");
                citySpinner.setAdapter(CitySpinnerAdapter);
                citySpinner.setPrompt("City");

                citySpinner.setSelection(CitySpinnerAdapter.getCount());

                citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (citySpinner.getSelectedItem() == "City") {

                        } else {
                            city_SelectedItemSpinner = citySpinner.getSelectedItem().toString();
                            for (int i = 0; i < cityListModels.size(); i++) {
                                if (cityListModels.get(i).getName().equals(city_SelectedItemSpinner))
                                    city_id = Integer.valueOf(cityListModels.get(i).getId());

                            }

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

    }

    private void getCountrySpinnerItems() {
        countryListViewModel.getCountryList(getContext()).observe(this, new Observer<List<CountryListModel>>() {
            @Override
            public void onChanged(List<CountryListModel> countryListModels) {
                ArrayList<String> countryArraylist = new ArrayList<>();
                for (int i = 0; i < countryListModels.size(); i++) {
                    countryArraylist.add(countryListModels.get(i).getName());
                }
                if (context != null) {
                    GenderSpinnerAdapter countrySpinnerAdapter = new GenderSpinnerAdapter(getContext(), R.layout.spinner_item);

                    countrySpinnerAdapter.addAll(countryArraylist);
                    countrySpinnerAdapter.add("Country");
                    countrySpinner.setAdapter(countrySpinnerAdapter);
                    countrySpinner.setPrompt("Country");

                    countrySpinner.setSelection(countrySpinnerAdapter.getCount());

                    countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (countrySpinner.getSelectedItem() == "Country") {

                                //Do nothing.
                            } else {
                                countrySelectedItemSpinner = countrySpinner.getSelectedItem().toString();
                                for (int i = 0; i < countryListModels.size(); i++) {
                                    if (countryListModels.get(i).getName().equals(countrySelectedItemSpinner))
                                        ;
                                    country_id = Integer.valueOf(countryListModels.get(i).getId());

                                }
                                getCitySpinnerItems();

                                // Toast.makeText(activity,"country "+String.valueOf(country_id) , Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }
            }
        });

    }

    private void getGenderSpinnerItems() {
        GenderSpinnerAdapter genderSpinnerAdapter = new GenderSpinnerAdapter(getContext(), R.layout.spinner_item);

        genderSpinnerAdapter.addAll(SpinnerGenderValue);
        genderSpinnerAdapter.add("Gender");
        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setPrompt("Gender");

        genderSpinner.setSelection(genderSpinnerAdapter.getCount());

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (genderSpinner.getSelectedItem() == "Gender") {

                } else {
                    gender_SelectedItemSpinner = genderSpinner.getSelectedItem().toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void init() {
        register = view.findViewById(R.id.register);
        first_name = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.lastName);
        mobileNumber = view.findViewById(R.id.mobileNumber);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        genderSpinner = view.findViewById(R.id.spinner_gender);
        countrySpinner = view.findViewById(R.id.spinner_country);
        citySpinner = view.findViewById(R.id.spinner_city);
    }


    private void sendNotificationData(double latitude, double longitude) {


        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(activity, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            //sendData();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("adddd", e.toString());


        }
    }

    private void SignUp() {
        First_name = first_name.getText().toString();
        LastName = lastName.getText().toString();
        MobileNumber = mobileNumber.getText().toString();
        Password = password.getText().toString();
        ConfirmPassword = confirmPassword.getText().toString();
        Email = email.getText().toString();
        id = dataSaver.getInt(Static_variables.CUSTOMER_ID, 0);

        if (First_name.equals("") || LastName.equals("") || MobileNumber.equals("") || Password.equals("") ||
                ConfirmPassword.equals("") || country_id == 0 || city_id == 0 ||
                gender_SelectedItemSpinner.equals("")) {
            Toast.makeText(activity, activity.getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(activity, getActivity().getString(R.string.please_enter_valid_email), Toast.LENGTH_SHORT).show();
        } else if (!Password.equals(ConfirmPassword)) {
            Toast.makeText(activity, getActivity().getString(R.string.password_and_confirmation), Toast.LENGTH_SHORT).show();
        } else {
            kProgressHUD.show();
            User user = new User();
            user.setCustomerId(String.valueOf(id));//lesa el id
            user.setFirstName(first_name.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPhone(mobileNumber.getText().toString());
            user.setPassword(password.getText().toString());
            // user.setp(confirmPassword.getText().toString());
            if (gender_SelectedItemSpinner.equals("Male")) {
                gender_id = "1";
            } else if (gender_SelectedItemSpinner.equals("Female")) {
                gender_id = "2";
            }
            user.setGenderId(gender_id);
            user.setCountryId(String.valueOf(country_id));
            user.setCityId(String.valueOf(city_id));

            signUpViewModel.getSignUp(user, getContext()).observe(this, new Observer<SignUpModel>() {
                @Override
                public void onChanged(SignUpModel signUpModel) {
                    if (signUpModel != null) {
                        kProgressHUD.dismiss();
                        Toast.makeText(activity, signUpModel.getRegisterResult().getUserMessage(), Toast.LENGTH_SHORT).show();


                        if (signUpModel.getCustomer() != null) {
                            dataSaver.edit().putInt(Static_variables.CUSTOMER_ID, signUpModel.getCustomer().getCustomerId()).apply();
                            dataSaver.edit().putString(Static_variables.Email, signUpModel.getCustomer().getEmail()).apply();
                            dataSaver.edit().putString(Static_variables.Phone, signUpModel.getCustomer().getCustomerPhone()).apply();
                            dataSaver.edit().putString(Static_variables.Fname, signUpModel.getCustomer().getFName()).apply();
                            dataSaver.edit().putString(Static_variables.Lname, signUpModel.getCustomer().getLName()).apply();
                            dataSaver.edit().putString(Static_variables.ACCESS_TOKEN, signUpModel.getCustomer().getAccessToken()).apply();
                            dataSaver.edit()
                                    .putString(Static_variables.Cityname, user.getCityId()).apply();
                            dataSaver.edit()
                                    .putString(Static_variables.Countryname, user.getCountryId()).apply();
                            dataSaver.edit()
                                    .putString(Static_variables.Gender, user.getGenderId()).apply();

                            dataSaver.edit()
                                    .putString(Static_variables.DEVICE_ID, "").apply();

                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                }
            });
        }
    }


    private void getGentSession() {
        Call<Guest_session> addEVEnt_call = RetrofitClient.getInstance(activity)
                .GUEST_SESSION_CALL(DeviceId);
        addEVEnt_call.enqueue(new Callback<Guest_session>() {

            @Override
            public void onResponse(@NotNull Call<Guest_session> call, @NotNull Response<Guest_session> response) {

                if (response.isSuccessful()) {
                    Log.e("TAG", "isSuccessful");
                    assert response.body() != null;
                    if (response.body().getResult()) {

                        Log.i(TAG, "onResponse: " + response.body().getNewId());
                        int customer_id = response.body().getNewId();
                        dataSaver.edit()
                                .putInt(Static_variables.CUSTOMER_ID, customer_id).apply();
                        dataSaver.edit()
                                .putString(Static_variables.DEVICE_ID, DeviceId).apply();
//                        Toast.makeText(activity, response.body().getNewId()+"", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(activity, MainActivity.class));
//                        activity.finish();
                    }


                } else {

                }
            }

            @Override
            public void onFailure(@NotNull Call<Guest_session> call, Throwable t) {
                Log.e("TAG ", "onFailure");
                Toast.makeText(activity, "حدث خطأ", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void sendData() {
////        String fcmToken = FirebaseInstanceId.getInstance().getToken();
//
//        Call<Guest_session> addEVEnt_call = RetrofitClient.getInstance(activity)
//                .sendAddressFcm(id, "android", fcmToken, country + "_" + city);
//        addEVEnt_call.enqueue(new Callback<Guest_session>() {
//
//            @Override
//            public void onResponse(@NotNull Call<Guest_session> call, @NotNull Response<Guest_session> response) {
//
//                if (response.isSuccessful()) {
//                    Log.e("TAG", "isSuccessful");
//                    assert response.body() != null;
//                    if (response.body().getResult()) {
//                        Log.e("TAG", "true");
////                        Intent intent = new Intent(activity, MainActivity.class);
////                        startActivity(intent);
////                        activity.finishAffinity();
//                    }
//
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<Guest_session> call, Throwable t) {
//                Log.e("TAG ", "onFailure");
//                Toast.makeText(activity, "حدث خطأ", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void connectFacebook() {
       /* List<String> scopes = Arrays.asList("user_birthday");

        SimpleAuth.connectFacebook(scopes, new AuthCallback() {
            @Override
            public void onSuccess(SocialUser socialUser) {
                Log.d(TAG, "userId:" + socialUser.userId);
                Log.d(TAG, "email:" + socialUser.email);
                Log.d(TAG, "accessToken:" + socialUser.accessToken);
                Log.d(TAG, "profilePictureUrl:" + socialUser.profilePictureUrl);
                Log.d(TAG, "username:" + socialUser.username);
                Log.d(TAG, "fullName:" + socialUser.fullName);
                Log.d(TAG, "pageLink:" + socialUser.pageLink);
                String fullName = socialUser.fullName;
                Log.i("fsdfuidsyfiu", "onSuccess: " + fullName.substring(fullName.indexOf(" ")));
                Log.i("fsdfuidsyfiu", "onSuccess: " + fullName.substring(0, fullName.indexOf(" ")));

                activity.runOnUiThread(() -> {
                    firstName.setText(fullName);
//                    lastName.setText(fullName.substring(fullName.indexOf(" ")));
                    email.setText(socialUser.email);
                });
            }

            @Override
            public void onError(Throwable error) {
                Log.d(TAG, error.getMessage());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "Canceled");
            }
        });*/
    }

    private void connectGoogle() {

      /*  List<String> scopes = Arrays.asList("https://www.googleapis.com/auth/analytics.readonly");
        com.jaychang.sa.google.SimpleAuth.connectGoogle(scopes, new AuthCallback() {
            @Override
            public void onSuccess(SocialUser socialUser) {
                Log.d(TAG, "userId:" + socialUser.userId);
                Log.d(TAG, "email:" + socialUser.email);
                Log.d(TAG, "accessToken:" + socialUser.accessToken);
                Log.d(TAG, "profilePictureUrl:" + socialUser.profilePictureUrl);
                Log.d(TAG, "username:" + socialUser.username);
                Log.d(TAG, "fullName:" + socialUser.fullName);
                String fullName = socialUser.fullName;
                Log.i("fsdfuidsyfiu", "onSuccess: " + fullName.substring(fullName.indexOf(" ")));
                Log.i("fsdfuidsyfiu", "onSuccess: " + fullName.substring(0, fullName.indexOf(" ")));

                activity.runOnUiThread(() -> {
                    firstName.setText(fullName);
//                    lastName.setText(fullName.substring(fullName.indexOf(" ")));
                    email.setText(socialUser.email);
                });
*/
     /*       }

            @Override
            public void onError(Throwable error) {

            }

            @Override
            public void onCancel() {

            }
        });*/

    }

}
