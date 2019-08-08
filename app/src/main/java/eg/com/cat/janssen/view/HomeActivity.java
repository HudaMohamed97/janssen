package eg.com.cat.janssen.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Webservice;
import eg.com.cat.janssen.model.CityChartResponseModel;
import eg.com.cat.janssen.model.CountryChartModel;
import eg.com.cat.janssen.model.FullChartResponseModel;
import eg.com.cat.janssen.model.MonthChartResponseModel;
import eg.com.cat.janssen.model.UserFullChart;
import eg.com.cat.janssen.model.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView profile, floatingAddButton, fab_doc , ic_reloac;
    UserModel currentuser;
    SharedPreferences mPrefs;
    Spinner chartType;
    TextView patientcount;
    ArrayAdapter<String> chart;
    ProgressDialog progress;
    private Handler handler;
    private CityChartResponseModel cityChart;
    private CountryChartModel countryChart;
    private MonthChartResponseModel monthChart;
    private eg.com.cat.janssen.model.yearChartResponseModel yearChartResponseModel;
    private FullChartResponseModel fullChartResponseModel;
    private UserFullChart userFullChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mPrefs = getSharedPreferences("id", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        currentuser = gson.fromJson(json, UserModel.class);


        changePlayerId();
        setupView();
        getChartByFilter(0);
    }


    public void changePlayerId() {


        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Log.d("debug", "User:" + userId);
                if (registrationId != null) {
                    Log.d("debug", "oneSignal registrationId:" + registrationId);


                    Map<String, String> map = new HashMap<>();

                    String playerId = userId;

                    if (!playerId.equals("")) {
                        //Log.d("PlayerId", playerId);


                        map.put("user_id", String.valueOf(currentuser.getId()));
                        map.put("player_id", playerId);

//            dialog.show();
                        Webservice.getInstance().getApi().updatePlayerId(map).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                String message = null;
                                int state = 0;
                                try {
                                    JSONObject res = new JSONObject(response.body().string());
                                    message = res.getString("Message");
                                    state = res.getInt("state");
                                    //Log.d("Response", message + state);


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (state == 0) {
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                } else if (state == 1) {
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
//                    dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                Toast.makeText(HomeActivity.this, "Network Error , please connect to internet", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();

                            }


                        });
                    }
                }

            }
        });

    }

    private void setupView() {

        profile = findViewById(R.id.ic_profile);
        floatingAddButton = findViewById(R.id.fab);
        chartType = findViewById(R.id.chartSpinner);
        ic_reloac = findViewById(R.id.ic_reloac);
        fab_doc = findViewById(R.id.fab_doc);
        chartType.setOnItemSelectedListener(HomeActivity.this);
        patientcount = findViewById(R.id.patientcount);
        progress = new ProgressDialog(this);

        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog


        List<String> dept = new ArrayList<String>();

        if (currentuser.getAdmin()) {
            dept.add("By Country");
            dept.add("By Month");
            getAdminFullChart();

        } else {
            dept.add("By Month");
            dept.add("By City");
            dept.add("All Country");
            getUserFullChart();
        }

        chart = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dept);

        chart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chartType.setAdapter(chart);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });
        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddDoctor();
            }
        });
        fab_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToListDoctors();
            }
        });

        ic_reloac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });

    }

    private void reload() {

        finish();
        startActivity(getIntent());

    }

    private void getUserFullChart() {

        final String fuck = String.valueOf(currentuser.getId());

        progress.show();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }

        };

        new Thread() {
            public void run() {


                Webservice.getInstance().getApi().getUserChart(fuck).enqueue(new Callback<UserFullChart>() {
                    @Override
                    public void onResponse(Call<UserFullChart> call, Response<UserFullChart> response) {
                        if (response.isSuccessful()) {
                            userFullChart = response.body();
                            setUserFullChartMonth();
                            progress.dismiss();
                        } else {
                            progress.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserFullChart> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                        progress.dismiss();

                    }
                });
            }
        }.start();


    }

    private void setUserFullChartMonth() {

        BarChart barChart = findViewById(R.id.chart);
        barChart.clear();


        if (userFullChart != null) {

            patientcount.setText(userFullChart.getTotalCount() + " " + "Patients");


            List<String> xValues = new ArrayList<>(); // "Denmark", "Finland", ...

            for (int i = 1; i <= 12; ++i) {
                xValues.add(String.valueOf(i));
            }


            List<BarEntry> entries = new ArrayList<>();
            BarDataSet barDataSet[] = new BarDataSet[userFullChart.getData().getCities().size()];


            for (int i = 0; i < userFullChart.getData().getCities().size(); ++i) {

                entries.add(new BarEntry(1, userFullChart.getData().getCities().get(i).getMonths().get_$1()));
                entries.add(new BarEntry(2, userFullChart.getData().getCities().get(i).getMonths().get_$2()));
                entries.add(new BarEntry(3, userFullChart.getData().getCities().get(i).getMonths().get_$3()));
                entries.add(new BarEntry(4, userFullChart.getData().getCities().get(i).getMonths().get_$4()));
                entries.add(new BarEntry(5, userFullChart.getData().getCities().get(i).getMonths().get_$5()));
                entries.add(new BarEntry(6, userFullChart.getData().getCities().get(i).getMonths().get_$6()));
                entries.add(new BarEntry(7, userFullChart.getData().getCities().get(i).getMonths().get_$7()));
                entries.add(new BarEntry(8, userFullChart.getData().getCities().get(i).getMonths().get_$8()));
                entries.add(new BarEntry(9, userFullChart.getData().getCities().get(i).getMonths().get_$9()));
                entries.add(new BarEntry(10, userFullChart.getData().getCities().get(i).getMonths().get_$10()));
                entries.add(new BarEntry(11, userFullChart.getData().getCities().get(i).getMonths().get_$11()));
                entries.add(new BarEntry(12, userFullChart.getData().getCities().get(i).getMonths().get_$12()));

                barDataSet[i] = new BarDataSet(entries, userFullChart.getData().getCities().get(i).getName());
                barDataSet[i].setColor(ColorTemplate.MATERIAL_COLORS[i%ColorTemplate.MATERIAL_COLORS.length]);
            }


            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.disableGridDashedLine();
            xAxis.setGranularity(1f);
            xAxis.setTextSize(17);

            xAxis.setGranularityEnabled(true);
            final String xVal[] = xValues.toArray(new String[0]);


            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    if (value >= 0) {
                        if (xVal.length > (int) value) {
                            return xVal[(int) value-1];
                        } else return "";
                    } else {
                        return "";
                    }
                }
            });



            BarData data = new BarData(barDataSet);
            data.setBarWidth(0.5f);
            data.setValueTextSize(17);
            //     barChart.getDescription().setTextSize(17); //sets the size of the label text in density pixels min = 6f, max = 24f, default is 10f, font size will be in dp
            barChart.getDescription().setEnabled(false);


            barChart.setData(data);
            barChart.invalidate(); // refresh

        }

    }

    private void goToListDoctors() {
        Intent k = new Intent(HomeActivity.this, ListDocActivity.class);
        startActivity(k);
    }

    private void goToAddDoctor() {
        Intent k = new Intent(HomeActivity.this, SearchDocActivity.class);
        startActivity(k);
    }

    private void goToProfile() {

        Intent k = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(k);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        getChartByFilter(i);
    }

    private void getChartByFilter(int i) {
        // 0 for country
        // 1 for month

        if (currentuser.getAdmin()) {
            if (i == 0)
                setAdminFullChartCountry();
            else if (i == 1)
                setAdminFullChartMonth();
        } else {
            if (i == 0)
                setUserFullChartMonth();
            else if (i == 1)
                setUserFullChartCity();
            else if (i == 2) {
                getAdminFullChart();
                setAdminFullChartCountry();
            }

        }

    }

    private void setUserFullChartCity() {


        if (userFullChart != null) {

            BarChart barChart = findViewById(R.id.chart);
            barChart.invalidate(); // refresh

            barChart.clear();

            patientcount.setText(userFullChart.getTotalCount() + " " + "Patients");

            List<BarEntry> entries = new ArrayList<>();
            String[] country = new String[userFullChart.getData().getCities().size()];

            for (int i = 0; i < userFullChart.getData().getCities().size(); ++i) {
                country[i] = (userFullChart.getData().getCities().get(i).getName());
                entries.add(new BarEntry(i, userFullChart.getData().getCities().get(i).getCount()));
            }

            BarDataSet dataSet = new BarDataSet(entries, "Cities");

            dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

            XAxis xAxis = barChart.getXAxis();

            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.disableGridDashedLine();
            xAxis.setTextSize(17);

         //   xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            final String xVal[] = country;


            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    if (value >= 0) {
                        if (xVal.length > (int) value) {
                            return xVal[(int) value];
                        } else return "";
                    } else {
                        return "";
                    }
                }
            });

            BarData lineData = new BarData(dataSet);
            lineData.setBarWidth(0.5f);
            lineData.setValueTextSize(17);

            barChart.setData(lineData);
            barChart.getDescription().setEnabled(false);

            barChart.invalidate(); // refresh
        }
    }

    private void setAdminFullChartMonth() {

        BarChart barChart = findViewById(R.id.chart);
        barChart.invalidate(); // refresh

        barChart.clear();

        if (fullChartResponseModel != null) {
            List<String> xValues = new ArrayList<>(); // "Denmark", "Finland", ...

            for (int i = 1; i <= 12; ++i) {
                xValues.add(String.valueOf(i));
            }


            List<BarEntry> entries = new ArrayList<>();
            BarDataSet barDataSet[] = new BarDataSet[fullChartResponseModel.getData().size()];

            for (int i = 0; i < fullChartResponseModel.getData().size(); ++i) {

                entries.add(new BarEntry(1, fullChartResponseModel.getData().get(i).getMonths().get_$1()));
                entries.add(new BarEntry(2, fullChartResponseModel.getData().get(i).getMonths().get_$2()));
                entries.add(new BarEntry(3, fullChartResponseModel.getData().get(i).getMonths().get_$3()));
                entries.add(new BarEntry(4, fullChartResponseModel.getData().get(i).getMonths().get_$4()));
                entries.add(new BarEntry(5, fullChartResponseModel.getData().get(i).getMonths().get_$5()));
                entries.add(new BarEntry(6, fullChartResponseModel.getData().get(i).getMonths().get_$6()));
                entries.add(new BarEntry(7, fullChartResponseModel.getData().get(i).getMonths().get_$7()));
                entries.add(new BarEntry(8, fullChartResponseModel.getData().get(i).getMonths().get_$8()));
                entries.add(new BarEntry(9, fullChartResponseModel.getData().get(i).getMonths().get_$9()));
                entries.add(new BarEntry(10, fullChartResponseModel.getData().get(i).getMonths().get_$10()));
                entries.add(new BarEntry(11, fullChartResponseModel.getData().get(i).getMonths().get_$11()));
                entries.add(new BarEntry(12, fullChartResponseModel.getData().get(i).getMonths().get_$12()));

                barDataSet[i] = new BarDataSet(entries, fullChartResponseModel.getData().get(i).getName());
                barDataSet[i].setColor(ColorTemplate.MATERIAL_COLORS[i%ColorTemplate.MATERIAL_COLORS.length]);

            }


            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.disableGridDashedLine();
            xAxis.setTextSize(17);
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            final String xVal[] = xValues.toArray(new String[0]);

            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    if (value >= 0) {
                        if (xVal.length > (int) value) {
                            return xVal[(int) value-1];
                        } else return "";
                    } else {
                        return "";
                    }
                }
            });

            BarData data = new BarData(barDataSet);
            data.setValueTextSize(17);
            data.setBarWidth(0.5f);
            barChart.getDescription().setEnabled(false);

           // barChart.getDescription().setTextSize(17); //sets the size of the label text in density pixels min = 6f, max = 24f, default is 10f, font size will be in dp
            barChart.setData(data);
            barChart.invalidate(); // refresh

        }
    }

    private void setAdminFullChartCountry() {

        if (fullChartResponseModel != null) {
            BarChart barChart = findViewById(R.id.chart);
            barChart.invalidate(); // refresh
            barChart.clear();

            patientcount.setText(fullChartResponseModel.getTotalCount() + " " + "Patients");

            List<BarEntry> entries = new ArrayList<>();
            String[] country = new String[fullChartResponseModel.getData().size()];

            for (int i = 0; i < fullChartResponseModel.getData().size(); ++i) {
                country[i] = (fullChartResponseModel.getData().get(i).getName());
                entries.add(new BarEntry(i, fullChartResponseModel.getData().get(i).getCount()));
            }

            BarDataSet dataSet = new BarDataSet(entries, "Countries");

            dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

            XAxis xAxis = barChart.getXAxis();


            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.disableGridDashedLine();
            xAxis.setTextSize(17);

            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            final String xVal[] = country;

            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    if (value >= 0) {
                        if (xVal.length > (int) value) {
                            return xVal[(int) value];
                        } else return "";
                    } else {
                        return "";
                    }
                }
            });

            BarData lineData = new BarData(dataSet);
            lineData.setBarWidth(0.5f);
            lineData.setValueTextSize(17);
            barChart.getDescription().setEnabled(false);

            barChart.setData(lineData);
            barChart.invalidate(); // refresh

        }
    }

    private void getAdminFullChart() {
        progress.show();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }

        };

        new Thread() {
            public void run() {


                Webservice.getInstance().getApi().getFullChart().enqueue(new Callback<FullChartResponseModel>() {
                    @Override
                    public void onResponse(Call<FullChartResponseModel> call, Response<FullChartResponseModel> response) {
                        if (response.isSuccessful()) {
                            fullChartResponseModel = response.body();
                            setAdminFullChartCountry();
                            progress.dismiss();
                        } else {
                            progress.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<FullChartResponseModel> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                        progress.dismiss();

                    }
                });
            }
        }.start();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
