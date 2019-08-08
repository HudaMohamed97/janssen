package eg.com.cat.janssen.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Webservice;
import eg.com.cat.janssen.model.CountryResponseModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText username, fullname, email, password, confirm_password, address, phone;
    LinearLayout cityLinearView;
    Button register;
    Spinner countrySpinner;
    ArrayAdapter<String> countryArray;
    List<String> city;
    String item = "0";
    CheckBox checkBox;

    ProgressDialog progress;
    private Handler handler;
    private CountryResponseModel countryResponseModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);


        username = findViewById(R.id.input_username);
        fullname = findViewById(R.id.input_name);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        confirm_password = findViewById(R.id.input_password_conf);
        register = findViewById(R.id.btn_register);
        address = findViewById(R.id.input_address);
        phone = findViewById(R.id.input_phone);
        countrySpinner = findViewById(R.id.input_country);
        cityLinearView = findViewById(R.id.input_city);
        countrySpinner.setOnItemSelectedListener(RegisterationActivity.this);


        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        getCountryList();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

    private void prepareCitiesList(String item) {

        cityLinearView.removeAllViews();
        city = new ArrayList<>();


        LinkedHashMap<String, String> cities = new LinkedHashMap<String, String>();

        for (int i = 0; i < countryResponseModel.getData().get(Integer.parseInt(item)).getCities().size(); ++i)
            cities.put(
                    String.valueOf(countryResponseModel.getData().get(Integer.parseInt(item)).getCities().get(i).getId()),
                    countryResponseModel.getData().get(Integer.parseInt(item)).getCities().get(i).getName()
            );

        Set<?> set = cities.entrySet();
        Iterator<?> i = set.iterator();
        while (i.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
            checkBox = new CheckBox(this);
            checkBox.setId(Integer.parseInt(me.getKey().toString()));
            checkBox.setText(me.getValue().toString());
            checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
            cityLinearView.addView(checkBox);
        }

    }

    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("*************id******" + button.getId());
                System.out.println("and text***" + button.getText().toString());

                if (city.contains(button.getId()))
                    city.remove(button.getId());
                else
                    city.add(String.valueOf(button.getId()));
            }
        };
    }


    private void register() {

        progress.show();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }

        };

        new Thread() {
            public void run() {

                HashMap<Object, Object> user = new HashMap<>();

                user.put("name", fullname.getText().toString().trim());
                user.put("username", username.getText().toString().trim());
                user.put("email", email.getText().toString().trim());
                user.put("password", password.getText().toString().trim());
                user.put("password_confirmation", confirm_password.getText().toString().trim());
                user.put("phone", phone.getText().toString().trim());
                user.put("address", "Android");
                user.put("country_id", Integer.valueOf(item) + 1);
                user.put("cities", city);
                user.put("employee_id", address.getText().toString().trim());


                //Log.d("RegisterationActivity", "run: " + city);
                //Log.d("userR",   user.toString());


                Webservice.getInstance().getApi().Register(user).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (!response.isSuccessful()) {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(RegisterationActivity.this, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(RegisterationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            progress.dismiss();

                        } else {
                            progress.dismiss();

                            Toast.makeText(RegisterationActivity.this, "Registration Successful, moving you to homepage now.", Toast.LENGTH_LONG).show();
                            goToLogin();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RegisterationActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                        progress.dismiss();

                    }
                });


            }
        }.start();
    }

    private void goToLogin() {

        Intent k = new Intent(RegisterationActivity.this, LoginActivity.class);
        startActivity(k);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        progress.show();


        // On selecting a spinner item
        item = String.valueOf(position);

        prepareCitiesList(item);
        // Showing selected spinner item
        //  Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        progress.dismiss();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getCountryList() {
        progress.show();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }

        };


        new Thread() {
            public void run() {

                Webservice.getInstance().getApi().getCountries().enqueue(new Callback<CountryResponseModel>() {
                    @Override
                    public void onResponse(Call<CountryResponseModel> call, Response<CountryResponseModel> response) {
                        String message = null;
                        int state = 0;
                        try {
                            state = response.body().getState();
                            countryResponseModel = response.body();
                            //Log.d("Response", message + state);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (state == 0) {
                            Toast.makeText(RegisterationActivity.this, message, Toast.LENGTH_SHORT).show();

                        } else if (state == 1) {
                            Toast.makeText(RegisterationActivity.this, message, Toast.LENGTH_SHORT).show();

                            List<String> dept = new ArrayList<String>();

                            for (int i = 0; i < response.body().getData().size(); ++i)
                                dept.add(response.body().getData().get(i).getName());

                            countryArray = new ArrayAdapter<String>(RegisterationActivity.this, android.R.layout.simple_spinner_item, dept);

                            countryArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            countrySpinner.setAdapter(countryArray);

                        }
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<CountryResponseModel> call, Throwable t) {

                        Toast.makeText(RegisterationActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                        progress.dismiss();

                    }


                });

            }

        }.start();
    }
}
