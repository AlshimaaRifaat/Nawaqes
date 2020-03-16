package com.ibsvalleyn.outlet.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.gson.Gson;
import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.MainActivity;
import com.ibsvalleyn.outlet.activities.SigningActivity;
import com.ibsvalleyn.outlet.api.RetrofitClient;
import com.ibsvalleyn.outlet.helper.Static_variables;
import com.ibsvalleyn.outlet.models.LoginResponse;
import com.facebook.CallbackManager;
import com.ibsvalleyn.outlet.models.Password_fullrecovery_model;
import com.ibsvalleyn.outlet.models.Password_recovery_model;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.facebook.GraphRequest.TAG;
import static com.ibsvalleyn.outlet.helper.AppFunctions.getKProgressHUD;

public class Sign_IN_Fragment extends Fragment {
    private SigningActivity activity;
    private CallbackManager callbackManager;
    private final static int faceBook = 64206;
    public static final int SIGN_IN_CODE = 777;
    TextView signUp, forgot_password;
    Button login;
    EditText password, user_name;
    private KProgressHUD kProgressHUD;
    String pass_word, user_mail;
    SharedPreferences dataSaver;
    private ImageView facebook, google_plus;
    private AlertDialog builder;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 555;
    private ImageView arrow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (SigningActivity) getActivity();

        assert activity != null;


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

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_fragment, container, false);
        dataSaver = getDefaultSharedPreferences(activity);

        // signUp = view.findViewById(R.id.sign_up);
        login = view.findViewById(R.id.login);
        user_name = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        kProgressHUD = getKProgressHUD(activity);
        //  facebook = view.findViewById(R.id.facebook);
        // google_plus = view.findViewById(R.id.google_plus);
        login.setOnClickListener(view13 -> userLogin());
        arrow = view.findViewById(R.id.arrow);

        arrow.setOnClickListener(view1 -> activity.finish());
        forgot_password = view.findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(view12 -> postOrder());

//        signUp.setOnClickListener(view1 -> {
//            activity.StackFragmentputParcelable(new Sign_UP_Fragment(), 3);
//
//        });

        printHashKey(activity);
//        google_plus.setOnClickListener(view1 -> activity.StackFragmentputParcelable(new Sign_UP_Fragment(), 2)
//        );
//        Calligrapher calligrapher = new Calligrapher(activity);
//        calligrapher.setFont(activity, "ubunto_r.ttf", true);

//        facebook.setOnClickListener(view1 -> activity.StackFragmentputParcelable(new Sign_UP_Fragment(), 1));
        Calligrapher calligrapher = new Calligrapher(activity);
        calligrapher.setFont(activity, "Ubuntu-B.ttf", true);

        return view;
    }

    public void userLogin() {
        pass_word = password.getText().toString();
        user_mail = user_name.getText().toString();

        if (user_mail.equals("") || pass_word.equals("")) {
            Toast.makeText(activity, "Fill all fields", Toast.LENGTH_SHORT).show();

        } else if (!Patterns.EMAIL_ADDRESS.matcher(user_name.getText().toString()).matches()) {
            Toast.makeText(activity, "please enter valid email", Toast.LENGTH_SHORT).show();

        } else {
            kProgressHUD.show();
            Call<LoginResponse> addEVEnt_call = RetrofitClient.getInstance(activity)
                    .userLogin(user_mail, pass_word);
            addEVEnt_call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.isSuccessful()) {
                        kProgressHUD.dismiss();

                        assert response.body() != null;

                        Log.e("TAGdddd", "isSuccessful"+new Gson().toJson(response.body()));


                        if (response.body().getLoginResult().getLogin_result()) {
                            Log.e("TAG", "isSuccessful");
                            Intent intent = new Intent(activity, MainActivity.class);
                            startActivity(intent);
                            activity.finishAffinity();
                            dataSaver.edit()
                                    .putString(Static_variables.DEVICE_ID, "").apply();
                            Toast.makeText(activity, response.body().getLoginResult().getUser_message(), Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Customer_id " + response.body().getCustomerInfo().getCustomerId());
                            int customer_id = response.body().getCustomerInfo().getCustomerId();
                            dataSaver.edit()
                                    .putInt(Static_variables.CUSTOMER_ID, customer_id).apply();

                            dataSaver.edit()
                                    .putString(Static_variables.Email, response.body().getCustomerInfo().getEmail()).apply();

                            dataSaver.edit()
                                    .putString(Static_variables.Phone, response.body().getCustomerInfo().getCustomerPhone()).apply();

                            dataSaver.edit()
                                    .putString(Static_variables.Fname, response.body().getCustomerInfo().getFirstname()).apply();

                            dataSaver.edit()
                                    .putString(Static_variables.Lname, response.body().getCustomerInfo().getLastname()).apply();
                            dataSaver.edit()
                                    .putString(Static_variables.Cityname, response.body().getCustomerInfo().getCityname()).apply();
                            dataSaver.edit()
                                    .putString(Static_variables.Countryname, response.body().getCustomerInfo().getCountryname()).apply();
                            dataSaver.edit()
                                    .putString(Static_variables.Gender, response.body().getCustomerInfo().getGender()).apply();

                            Log.e("TAG", "ACCESS_TOKEN " + response.body().getLoginResult().getAccess_token());

                            String access_token = response.body().getLoginResult().getAccess_token();

                            dataSaver.edit()
                                    .putString(Static_variables.ACCESS_TOKEN, access_token)
                                    .apply();

                        } else {
                            Toast.makeText(activity, response.body().getLoginResult().getUser_message(), Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        kProgressHUD.dismiss();
                        Log.e("TAG", "notSuccessful" + response.message());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("TAG ", "onFailure " + t.getMessage());
                    kProgressHUD.dismiss();
                    Toast.makeText(activity, "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void postOrder() {

        final AlertDialog builder = new AlertDialog.Builder(activity).create();
        Calligrapher calligrapher = new Calligrapher(activity);
        final View v = LayoutInflater.from(activity).inflate(R.layout.forget_password_layout, null);

        Button recover = v.findViewById(R.id.recover);
        EditText email = v.findViewById(R.id.email);

        recover.setOnClickListener(view ->
                {

                    String emailAddress = email.getText().toString();
                    if (emailAddress.equals("")) {
                        Toast.makeText(activity, "Fill  field", Toast.LENGTH_SHORT).show();

                    } else {
                        builder.dismiss();
                        SendEmailToRecover(emailAddress);
                    }
                }
        );

        builder.setView(v);
        builder.show();
    }

    private void SendEmailToRecover(String emailAddress) {


        kProgressHUD.show();
        Call<Password_recovery_model> addEVEnt_call = RetrofitClient.getInstance(activity)
                .PASSWORD_RECOVERY_MODEL_CALL(emailAddress);
        addEVEnt_call.enqueue(new Callback<Password_recovery_model>() {

            @Override
            public void onResponse(Call<Password_recovery_model> call, Response<Password_recovery_model> response) {
                kProgressHUD.dismiss();

                if (response.isSuccessful()) {

                    if (response.body().getResult()) {

                        RecoverData(emailAddress);
                        Toast.makeText(activity, "" + response.body().getUserMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    kProgressHUD.dismiss();
                    Log.e("TAG", "notSuccessful");
                }
            }

            @Override
            public void onFailure(Call<Password_recovery_model> call, Throwable t) {
                Log.e("TAG ", "onFailure " + t.getMessage());
                kProgressHUD.dismiss();
                Toast.makeText(activity, "حدث خطأ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void RecoverData(String emailAddress) {

        builder = new AlertDialog.Builder(activity).create();

        final View v = LayoutInflater.from(activity).inflate(R.layout.verification_code_password, null);

        PinEntryEditText codeEt = v.findViewById(R.id.codeEt);
        EditText new_password = v.findViewById(R.id.new_password);
        EditText confirm_password = v.findViewById(R.id.confirm_password);

        Button Send = v.findViewById(R.id.Send);

        Send.setOnClickListener(view ->
                {
                    String newPassword = new_password.getText().toString();
                    String confirmPassword = confirm_password.getText().toString();
                    String Code = codeEt.getText().toString();

                    if (confirmPassword.equals("") || newPassword.equals("") || Code.equals("")) {
                        Toast.makeText(activity, "Fill all fields", Toast.LENGTH_SHORT).show();

                    } else if (!confirmPassword.equals(newPassword)) {
                        Toast.makeText(activity, "Password and Confirmation password doesn't match", Toast.LENGTH_SHORT).show();

                    } else {
                        ConfirmationPassword(confirmPassword, Code, emailAddress, builder);

                    }
                }
        );

        builder.setView(v);
        builder.show();
    }

    private void ConfirmationPassword(String confirmPassword, String codeEt, String emailAddress, AlertDialog builder) {
        Log.i(TAG, "ConfirmationPassword: " + confirmPassword + codeEt + emailAddress);

        kProgressHUD.show();
        Call<Password_fullrecovery_model> addEVEnt_call = RetrofitClient.getInstance(activity)
                .PASSWORD_FULLRECOVERY_MODEL_CALL(codeEt, emailAddress, confirmPassword);
        addEVEnt_call.enqueue(new Callback<Password_fullrecovery_model>() {

            @Override
            public void onResponse(Call<Password_fullrecovery_model> call, Response<Password_fullrecovery_model> response) {

                if (response.isSuccessful()) {
                    kProgressHUD.dismiss();

                    if (response.body().getResult()) {
                        Toast.makeText(activity, response.body().getUserMessage(), Toast.LENGTH_SHORT).show();
                        builder.dismiss();


                    } else {
                        Toast.makeText(activity, response.body().getUserMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    kProgressHUD.dismiss();
                    Log.e("TAG", "notSuccessful" + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Password_fullrecovery_model> call, Throwable t) {
                Log.e("TAG ", "onFailure " + t.getMessage());
                kProgressHUD.dismiss();
                Toast.makeText(activity, "حدث خطأ", Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    public void onActivityResult(int requestCode, int responseCode, @Nullable Intent intent) {
//        super.onActivityResult(requestCode, responseCode, intent);
//
//        switch (requestCode) {
//            case faceBook:
//                callbackManager.onActivityResult(requestCode, responseCode, intent);
//                break;
//            case SIGN_IN_CODE:
//
//                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
//                try {
//                    GoogleSignInAccount account = task.getResult(ApiException.class);
//                    firebaseAuthWithGoogle(account);
//                } catch (ApiException e) {
//                    Log.w(TAG, "Google sign in failed", e);
//
//                    break;
//                }
//        }
//    }

    public static void printHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getApplicationContext().getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                //W62F69Fwcf9tITS2u0UI5xo1kaY
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
//
//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        acct.getIdToken();
//        Log.d("ASdasdsadsad", "" + new Gson().toJson(acct));
//
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
//        kProgressHUD.show();
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(activity, task -> {
//                    if (task.isSuccessful()) {
//                        Log.d(TAG, "signInWithCredential:success");
//                        String token = FirebaseInstanceId.getInstance().getToken();
//                        Log.d("sadsadsad", "" + token);
//
//                        Log.d("", "");
//                        System.out.println(token);
//
//                        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
//                        assert mUser != null;
//
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        updateUI(user);
//                    } else {
//                        Log.w("SADSADSADSAD", "signInWithCredential:failure", task.getException());
//                        updateUI(null);
//                    }
//
//                    kProgressHUD.dismiss();
//                });
//    }
//
//    private void updateUI(FirebaseUser user) {
//
//        kProgressHUD.dismiss();
//        if (user != null) {
//            user.getProviderId();
//            Log.d("asdsjkhjkhkad", "" + user.getProviderId());
////Log.d("asdsad",""+user.getIdToken(true).getResult().getToken());
//
//            Log.i("ASDSAddDSA", "updateUI: " + new Gson().toJson(user));
//
//            Toast.makeText(activity, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
//            activity.StackFragmentputParcelableGoole(new Sign_UP_Fragment(), user);
//
//        }
//    }


}