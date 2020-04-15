package eg.com.cat.janssen.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Webservice;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText email;
    Button reset;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        email = (EditText) findViewById(R.id.input_email);
        reset = (Button) findViewById(R.id.btn_reset);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog


        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                reset(email.getText().toString().trim());
            }
        });


    }


    private void reset(String email) {

        if (email.isEmpty())
            Toast.makeText(this, "Please enter your credentials correctly ", Toast.LENGTH_LONG).show();

        else {

            progress.show();


            HashMap<String, String> data = new HashMap<>();

            data.put("email", email);


            Webservice.getInstance().getApi().Reset_Password(data).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        progress.dismiss();
                        Toast.makeText(ForgetPasswordActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    } else {
                        progress.dismiss();
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(ForgetPasswordActivity.this, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(ForgetPasswordActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(ForgetPasswordActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
                }
            });
        }

    }


}
