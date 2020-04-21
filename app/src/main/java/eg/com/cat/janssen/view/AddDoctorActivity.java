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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Webservice;
import eg.com.cat.janssen.model.CountryResponseModel;
import eg.com.cat.janssen.model.DocModel;
import eg.com.cat.janssen.model.UserModel;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDoctorActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener, View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {

    final Calendar myCalendar = Calendar.getInstance();
    EditText input_name, input_doc_id, input_hospital, input_date_rec;
    Spinner input_business_sector, input_patient_profile;
    TextView countrySpinner, input_city;
    Button addDoc;
    int countrySelection;
    ArrayAdapter<String> countryArray, businessSectorArray, patientProfileArray;
    ArrayList<String> cityName= new ArrayList<>();
    ArrayList<String> cityId;
    String cityID;
    SpinnerDialog country_Spinner, citySpinner;
    String item = "0";
    CheckBox checkBox;
    SharedPreferences mPrefs;
    ProgressDialog progress;
    UserModel currentuser;
    DocModel docModel;
    ArrayList<String> dept = new ArrayList<String>();
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
        input_city = findViewById(R.id.input_city);
        addDoc = findViewById(R.id.btn_add_doc);
        countrySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCountrySpinner();
                country_Spinner.showSpinerDialog();
            }
        });
        input_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cityName.size() != 0) {
                    initCitySpinner();
                    citySpinner.showSpinerDialog();
                } else {
                    Toast.makeText(AddDoctorActivity.this, "Please Select Country First Thanks.", Toast.LENGTH_LONG).show();

                }

            }
        });


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


    private void initCountrySpinner() {

        country_Spinner = new SpinnerDialog(AddDoctorActivity.this, dept, "Select Hospital");// With No Animation

        country_Spinner.setCancellable(true); // for cancellable
        country_Spinner.setShowKeyboard(false);// for open keyboard by default

        country_Spinner.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position) {
                countrySpinner.setText(dept.get(position));
                progress.show();
                item = String.valueOf(position);
                if (countryResponseModel != null) {
                    prepareCitiesList(item);
                }
                progress.dismiss();

                // selectedHospitalId = hospitals.get(position).getId();


                //  initDoctorsSpinner(prepareDoctorsStringList(hospitals.get(position).getDoctors()));

            }
        });

    }

    private void setUserData() {
        docModel = DocModel.getInstance();
        input_name.setText(docModel.getName());
        input_hospital.setText(docModel.getHospital_name());
        input_date_rec.setText(docModel.getDate_of_recruitment());
        countrySpinner.setText(docModel.getCountry());
        input_city.setText(docModel.getCity());


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
        dept1.add("Experienced");
        patientProfileArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dept1);
        patientProfileArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_patient_profile.setAdapter(patientProfileArray);


    }


    private void prepareCitiesList(String item) {
        cityId = new ArrayList<>();
        for (int i = 0; i < countryResponseModel.getData().get(Integer.parseInt(item)).getCities().size(); ++i) {
            cityId.add(String.valueOf(countryResponseModel.getData().get(Integer.parseInt(item)).getCities().get(i).getId()));
            cityName.add(countryResponseModel.getData().get(Integer.parseInt(item)).getCities().get(i).getName());
        }

    }

    private void initCitySpinner() {

        citySpinner = new SpinnerDialog(AddDoctorActivity.this, cityName, "Select Hospital");// With No Animation
        citySpinner.setCancellable(true); // for cancellable
        citySpinner.setShowKeyboard(false);// for open keyboard by default

        citySpinner.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                input_city.setText(cityName.get(position));
                progress.show();
                cityID = String.valueOf(cityId.get(position));
                progress.dismiss();

                // selectedHospitalId = hospitals.get(position).getId();


                //  initDoctorsSpinner(prepareDoctorsStringList(hospitals.get(position).getDoctors()));

            }
        });

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

                            for (int i = 0; i < response.body().getData().size(); ++i) {
                                dept.add(response.body().getData().get(i).getName());
                            }

                           /* countryArray = new ArrayAdapter<>(AddDoctorActivity.this, android.R.layout.simple_spinner_item, dept);

                            countryArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            countrySpinner.setAdapter(countryArray);*/

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
