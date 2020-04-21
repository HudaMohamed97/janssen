package eg.com.cat.janssen.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eg.com.cat.janssen.Presenter.MyRecyclerViewAdapter;
import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Webservice;
import eg.com.cat.janssen.model.DocModel;
import eg.com.cat.janssen.model.DocModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchDocActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    Button btn_search_doc, btn_add_doc;
    EditText docName;
    ProgressDialog progress;
    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<String> animalNames = new ArrayList<>();
    ArrayList<DocModel> searchDocResponseModel;
    JSONArray data;
    String userid;
    DocModel Response;
    DocModelResponse docModelResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doc);


        docName = findViewById(R.id.input_doc_name);
        btn_search_doc = findViewById(R.id.btn_search_doc);
        recyclerView = findViewById(R.id.rvAnimals);
        btn_add_doc = findViewById(R.id.btn_add_doc);


        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        SharedPreferences mPrefs = getSharedPreferences("id", MODE_PRIVATE);
        userid = mPrefs.getString("userid", "");


        btn_search_doc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchForDoc(docName.getText().toString().trim());
            }
        });


        btn_add_doc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent k = new Intent(SearchDocActivity.this, AddDoctorActivity.class);
                startActivity(k);
            }
        });


    }


    private void setupRCV(List<String> searchDocResponseModel) {
        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, searchDocResponseModel);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    private void searchForDoc(String docName) {

        progress.show();


        HashMap<String, String> datax = new HashMap<>();

        datax.put("user_id", userid);
        datax.put("text", docName);


        Webservice.getInstance().getApi().searchDoctor(datax).enqueue(new Callback<DocModelResponse>() {
            @Override
            public void onResponse(Call<DocModelResponse> call, Response<DocModelResponse> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    Toast.makeText(SearchDocActivity.this, "Success", Toast.LENGTH_LONG).show();

                    if (response.body().getState() == 0) {
                        progress.dismiss();
                        //  goToAddDoc();
                        Toast.makeText(SearchDocActivity.this, "Doctor Doesn't exist, Please add it.", Toast.LENGTH_LONG).show();

                    } else {
                        progress.dismiss();
                        List<String> names = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); ++i) {
                            names.add(response.body().getData().get(i).getName());
                        }
                        setupRCV(names);

                        docModelResponse = response.body();
                    }


                } else {
                    progress.dismiss();

                    try {
                        Toast.makeText(SearchDocActivity.this, "Doctor Doesn't exist, Please add it.", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(SearchDocActivity.this, "Doctor Doesn't exist, Please add it.", Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<DocModelResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(SearchDocActivity.this, "Check your internet", Toast.LENGTH_LONG).show();
                // goToAddDoc();
            }
        });
    }

    private void goToAddDoc() {
        Intent k = new Intent(SearchDocActivity.this, AddDoctorActivity.class);
        startActivity(k);
        finish();
    }


    @Override
    public void onItemClick(View view, int position) {

      /*  Toast.makeText(this
                , "You clicked " + adapter.getItem(position) + " on row number " + position
                , Toast.LENGTH_SHORT).show();
*/

        DocModel docModel = DocModel.getInstance();
        docModel.setId(docModelResponse.getData().get(position).getId());
        docModel.setName(docModelResponse.getData().get(position).getName());
        docModel.setDate_of_recruitment(docModelResponse.getData().get(position).getDate_of_recruitment());
        docModel.setHospital_name(docModelResponse.getData().get(position).getHospital_name());
        docModel.setCity(docModelResponse.getData().get(position).getCity());
        docModel.setCountry(docModelResponse.getData().get(position).getCountry());



      /*  SharedPreferences mPrefs = getSharedPreferences("id", MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        Gson gson = new Gson();
        String json = null; // myObject - instance of MyObject
        try {
            json = gson.toJson( data.get(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        prefsEditor.putString("DocObj", json);
        prefsEditor.apply();*/

        goToAddDoc();

    }

    @Override
    public void onBackPressed() {

        Intent k = new Intent(SearchDocActivity.this, HomeActivity.class);
        startActivity(k);
        finish();
    }
}
