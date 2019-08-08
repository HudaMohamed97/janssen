package eg.com.cat.janssen.view;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

import eg.com.cat.janssen.Presenter.DoctorPatientDataAdapter;
import eg.com.cat.janssen.Presenter.SwipeController;
import eg.com.cat.janssen.Presenter.SwipeControllerActions;
import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Webservice;
import eg.com.cat.janssen.model.AllDoctorsResponseModel;
import eg.com.cat.janssen.model.DeletePatientResponseModel;
import eg.com.cat.janssen.model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDocActivity extends AppCompatActivity {

    DoctorPatientDataAdapter mAdapter;
    AllDoctorsResponseModel allDoctorsResponseModel;
    ProgressDialog progress;
    UserModel currentuser;
    SharedPreferences mPrefs;
    RecyclerView recyclerView;
    SwipeController swipeController = null;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doc);

        mPrefs = getSharedPreferences("id", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        currentuser = gson.fromJson(json, UserModel.class);


        setupView();
        getAllDoctors();

    }

    private void setupView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);


    }


    private void getAllDoctors() {
        progress.show();

        String userId = String.valueOf(currentuser.getId());

        Webservice.getInstance().getApi().getAllDocs(userId).enqueue(new Callback<AllDoctorsResponseModel>() {
            @Override
            public void onResponse(Call<AllDoctorsResponseModel> call, Response<AllDoctorsResponseModel> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    Toast.makeText(ListDocActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();

                    if (response.body().getState() == 0) {
                        progress.dismiss();
                    } else {
                        progress.dismiss();
                        allDoctorsResponseModel = response.body();
                        setupRecyclerView();
                    }


                } else {
                    progress.dismiss();

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDocActivity.this, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDocActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<AllDoctorsResponseModel> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

    private void setupRecyclerView() {


        mAdapter = new DoctorPatientDataAdapter(allDoctorsResponseModel);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeController = new SwipeController(allDoctorsResponseModel, new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {

                removepost(currentuser.getId(), allDoctorsResponseModel.getData().get(position).getId());

                mAdapter.allDoctorsResponseModel.getData().remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());


            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);


        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));


        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));

        recyclerView.setHasFixedSize(true);


        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                mAdapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });

    }


    private void removepost(int userId, int patientId) {

        progress.show();


        HashMap<String, String> data = new HashMap<>();

        data.put("user_id", String.valueOf(userId));


        Webservice.getInstance().getApi().deleteDocs(String.valueOf(patientId), data).enqueue(new Callback<DeletePatientResponseModel>() {
            @Override
            public void onResponse(Call<DeletePatientResponseModel> call, Response<DeletePatientResponseModel> response) {
                progress.dismiss();
                Toast.makeText(ListDocActivity.this, response.body().getData(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DeletePatientResponseModel> call, Throwable t) {
                progress.dismiss();

            }
        });
    }
}

