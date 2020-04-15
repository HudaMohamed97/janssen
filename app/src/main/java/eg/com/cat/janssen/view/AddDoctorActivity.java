package eg.com.cat.janssen.view;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Webservice;
import eg.com.cat.janssen.model.CountryResponseModel;
import eg.com.cat.janssen.model.DocModel;
import eg.com.cat.janssen.model.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDoctorActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener, View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {

    final Calendar myCalendar = Calendar.getInstance();
    EditText input_name, input_doc_id, input_hospital, input_date_rec;
    Spinner input_business_sector, input_patient_profile, countrySpinner;
    LinearLayout cityLinearView;
    Button addDoc;
    ArrayAdapter<String> countryArray, businessSectorArray, patientProfileArray;
    List<String> city;
    String cityID;
    String item = "0";
    CheckBox checkBox;
    SharedPreferences mPrefs;
    ProgressDialog progress;
    UserModel currentuser;
    DocModel docModel;
    private Handler handler;
    private CountryResponseModel countryResponseModel;
    private String business_sectorItem = "MOH", patient_profileItem = "Bio-Naive";
    private String docID = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        // goToSearchDoctorScreen();


        mPrefs = getSharedPreferences("id", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("DocObj", "");


        gson = new Gson();
        json = mPrefs.getString("MyObject", "");
        currentuser = gson.fromJson(json, UserModel.class);

        input_name = findViewById(R.id.input_name);
        input_hospital = findViewById(R.id.input_hospital);
        input_date_rec = findViewById(R.id.input_date_rec);
        input_business_sector = findViewById(R.id.input_business_sector);
        input_patient_profile = findViewById(R.id.input_patient_profile);

        countrySpinner = findViewById(R.id.input_country);
        cityLinearView = findViewById(R.id.input_city);
        addDoc = findViewById(R.id.btn_add_doc);

        countrySpinner.setOnItemSelectedListener(AddDoctorActivity.this);
        input_business_sector.setOnItemSelectedListener(AddDoctorActivity.this);
        input_patient_profile.setOnItemSelectedListener(AddDoctorActivity.this);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        input_date_rec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddDoctorActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        getCountryList();

        setSpinners();

        addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        setUserData();
    }


    private void setUserData() {

        docModel = DocModel.getInstance();

        input_name.setText(docModel.getName());
        input_hospital.setText(docModel.getHospital_name());
        input_date_rec.setText(docModel.getDate_of_recruitment());

    }

    private void setSpinners() {

        List<String> dept = new ArrayList<String>();

        dept.add("MOH");
        dept.add("GOV");
        dept.add("PVT");


        businessSectorArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dept);

        businessSectorArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_business_sector.setAdapter(businessSectorArray);


        List<String> dept1 = new ArrayList<String>();

        dept1.add("Bio-Naive");
        dept1.add("Bio-Exp Anti TNF");
        dept1.add("Bio Exp IL17");
        dept1.add("Bio - Exp IL 12-23");


        patientProfileArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dept1);

        patientProfileArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_patient_profile.setAdapter(patientProfileArray);


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

                cityID = String.valueOf(button.getId());
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

                user.put("name", input_name.getText().toString().trim());
                user.put("user_id", currentuser.getId());
                user.put("hospital_name", input_hospital.getText().toString().trim());
                user.put("business_sector", input_business_sector.getSelectedItem().toString());
                user.put("date_of_recruitment", input_date_rec.getText().toString().trim());
                user.put("patient_profile", input_patient_profile.getSelectedItem().toString());
                user.put("doctor_id", docModel.getId());
                user.put("country_id", Integer.valueOf(item) + 1);
                user.put("city_id", cityID);
                //Log.d("AddDoctorActivity", "run: " + city);
                //Log.d("userR", user.toString());


                Webservice.getInstance().getApi().addDoc(user).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(AddDoctorActivity.this, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(AddDoctorActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            progress.dismiss();
                        } else {
                            progress.dismiss();
                            Toast.makeText(AddDoctorActivity.this, "Doctor Added successfully", Toast.LENGTH_LONG).show();
                            docModel = null;
                            goToLogin();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddDoctorActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                        progress.dismiss();

                    }
                });


            }
        }.start();
    }

    private void goToLogin() {
        Intent k = new Intent(AddDoctorActivity.this, HomeActivity.class);
        startActivity(k);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        progress.show();
        if (countryResponseModel != null) {
            item = String.valueOf(position);
            prepareCitiesList(item);
        }
        progress.dismiss();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getCountryList() {

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
                            Toast.makeText(AddDoctorActivity.this, message, Toast.LENGTH_SHORT).show();

                        } else if (state == 1) {
                            Toast.makeText(AddDoctorActivity.this, message, Toast.LENGTH_SHORT).show();

                            List<String> dept = new ArrayList<String>();

                            for (int i = 0; i < response.body().getData().size(); ++i)
                                dept.add(response.body().getData().get(i).getName());

                            countryArray = new ArrayAdapter<String>(AddDoctorActivity.this, android.R.layout.simple_spinner_item, dept);

                            countryArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            countrySpinner.setAdapter(countryArray);

                        }
                    }

                    @Override
                    public void onFailure(Call<CountryResponseModel> call, Throwable t) {

                        Toast.makeText(AddDoctorActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }.start();
    }


    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        input_date_rec.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, i);
        myCalendar.set(Calendar.MONTH, i1);
        myCalendar.set(Calendar.DAY_OF_MONTH, i2);
        updateLabel();
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public void onBackPressed() {

        docModel.setName("");
        docModel.setHospital_name("");
        docModel.setId(0);
        docModel.setDate_of_recruitment("");

        Intent k = new Intent(AddDoctorActivity.this, SearchDocActivity.class);
        startActivity(k);
        finish();
    }
}
