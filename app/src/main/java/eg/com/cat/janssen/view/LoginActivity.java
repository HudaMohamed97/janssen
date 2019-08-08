package eg.com.cat.janssen.view;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Webservice;
import eg.com.cat.janssen.model.LoginResponseModel;
import eg.com.cat.janssen.model.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    public static UserModel currentuser;
    EditText email, password;
    Button login;
    ImageView forgetPassword, register;
    CheckBox checkRemeber;
    ProgressDialog progress;
    private View mLoginFormView;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        checkRemeber = findViewById(R.id.chckRemember);
        forgetPassword = findViewById(R.id.forget_pass);

        mLoginFormView = findViewById(R.id.login_form);


        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog


        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();


        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin) {
            email.setText(loginPreferences.getString("username", ""));
            password.setText(loginPreferences.getString("password", ""));
            checkRemeber.setChecked(true);
        }


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login.startAnimation(buttonClick);
                login(email.getText().toString().trim(), password.getText().toString().trim());
            }
        });


        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity

                register.startAnimation(buttonClick);

                Intent k = new Intent(LoginActivity.this, RegisterationActivity.class);
                startActivity(k);
            }
        });


        forgetPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                forgetPassword.startAnimation(buttonClick);

                Intent k = new Intent(LoginActivity.this, ForgetPasswordActivity.class);

                startActivity(k);

            }
        });


    }


    public void login(final String user, final String pass) {

        final HashMap<String, String> data = new HashMap<>();

        data.put("email", user);
        data.put("password", pass);


        if (user.isEmpty() || pass.isEmpty())
            Toast.makeText(this, "Please enter your credentials correctly ", Toast.LENGTH_LONG).show();

        else {


            progress.show();

            Webservice.getInstance().getApi().Login(data).enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {


                    if (!response.isSuccessful()) {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(LoginActivity.this, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        progress.dismiss();


                    } else {
                        Toast.makeText(LoginActivity.this, "Data Get Successfully", Toast.LENGTH_LONG).show();


                        currentuser = response.body().getData();

                        final String userIdString = Integer.toString(response.body().getData().getId());

                        SharedPreferences mPrefs = getSharedPreferences("id", MODE_PRIVATE);

                        SharedPreferences.Editor prefsEditor = mPrefs.edit();

                        Gson gson = new Gson();
                        String json = gson.toJson(currentuser); // myObject - instance of MyObject
                        prefsEditor.putString("MyObject", json);
                        prefsEditor.putString("userid", userIdString);
                        prefsEditor.apply();

                        if (checkRemeber.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("username", user);
                            loginPrefsEditor.putString("password", pass);

                            loginPrefsEditor.commit();
                        } else {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }


                        progress.dismiss();


                        //     updatePlayerId(userIdString);
                        gotoHome();

                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                    //Log.e("login", "onFailure: ", t);
                    progress.dismiss();

                }
            });

        }
    }

    private void gotoHome() {

        Intent k = new Intent(LoginActivity.this, HomeActivity.class);
        k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(k);
        finish();
    }

    private void updatePlayerId(String user_id) {


      /*  OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        status.getPermissionStatus().getEnabled();

        String playerId = status.getSubscriptionStatus().getUserId();
       */

        HashMap<String, String> user = new HashMap<>();

        user.put("player_id", "playerId");
        user.put("user_id", user_id);

        Webservice.getInstance().getApi().update_player_id(user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (!response.isSuccessful()) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Successfull.", Toast.LENGTH_LONG).show();
                    //   currentuser = response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
            }
        });

    }


}

