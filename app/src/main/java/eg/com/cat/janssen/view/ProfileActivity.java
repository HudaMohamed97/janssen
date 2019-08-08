package eg.com.cat.janssen.view;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

import eg.com.cat.janssen.MainActivity;
import eg.com.cat.janssen.Presenter.FileUtils;
import eg.com.cat.janssen.Presenter.ServiceGenerator;
import eg.com.cat.janssen.R;
import eg.com.cat.janssen.WebServices.Api;
import eg.com.cat.janssen.WebServices.Webservice;
import eg.com.cat.janssen.model.UserModel;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eg.com.cat.janssen.WebServices.Services.MAIN_PICTURES_URL;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    ImageView picimage;
    ProgressDialog progress;
    EditText input_name, input_address, input_phone, input_old_password, input_password, input_password_conf;
    String user_id;
    Button btn_update_pass, btn_logout, btn_update_info;
    SharedPreferences mPrefs;
    UserModel currentuser;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mPrefs = getSharedPreferences("id", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        currentuser = gson.fromJson(json, UserModel.class);

        // currentuser = LoginActivity.currentuser;

        setupView();
        setUserImage();
        setData();
    }

    private void setData() {
        input_name.setText(currentuser.getName());
        input_address.setText(currentuser.getAddress());
        input_phone.setText(currentuser.getPhone());
    }

    private void setUserImage() {

        if (currentuser.getPhoto() != null && currentuser.getPhoto().equals("")) {
            {
                Picasso.get().load(MAIN_PICTURES_URL + currentuser.getPhoto())
                        .fit()
                        //       .centerInside()
                        .noFade()
                        .into(picimage);
            }

        }

    }

    private void setupView() {

        picimage = findViewById(R.id.picimage);
        input_name = findViewById(R.id.input_name);
        input_address = findViewById(R.id.input_address);
        input_phone = findViewById(R.id.input_phone);
        input_old_password = findViewById(R.id.input_old_password);
        input_password = findViewById(R.id.input_password);
        input_password_conf = findViewById(R.id.input_password_conf);
        btn_update_pass = findViewById(R.id.btn_update_pass);
        btn_logout = findViewById(R.id.btn_logout);
        btn_update_info = findViewById(R.id.btn_update_info);


        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog


        picimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EasyPermissions.hasPermissions(ProfileActivity.this
                        , Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                    openGalleryIntent.setType("image/*");
                    startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
                } else {
                    // Do not have permissions, request them now
                    EasyPermissions.requestPermissions(ProfileActivity.this, "Gallery Permission",
                            READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
                }


            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_logout.startAnimation(buttonClick);
                gotologin();
            }
        });

        btn_update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_update_info.startAnimation(buttonClick);
                updateInfo();
            }
        });

        btn_update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_update_pass.startAnimation(buttonClick);
                updateUserPass();
            }
        });

    }

    private void gotologin() {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void updateInfo() {

        progress.show();
        updateUserData();
        progress.dismiss();

    }

    private void updateUserData() {

        HashMap<String, String> user = new HashMap<>();

        user.put("user_id", String.valueOf(currentuser.getId()));
        user.put("name", input_name.getText().toString().trim());
        user.put("address", input_address.getText().toString().trim());
        user.put("phone,", input_phone.getText().toString().trim());

        Webservice.getInstance().getApi().update_user(user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (!response.isSuccessful()) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ProfileActivity.this, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(ProfileActivity.this, "Successfull.", Toast.LENGTH_LONG).show();
                    //   currentuser = response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void updateUserPass() {

        HashMap<String, String> user = new HashMap<>();

        user.put("user_id", user_id);
        user.put("oldPassword", input_old_password.getText().toString().trim());
        user.put("newPassword", input_password.getText().toString().trim());
        user.put("newPassword_confirmation,", input_password_conf.getText().toString().trim());


        Webservice.getInstance().getApi().update_pass(user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (!response.isSuccessful()) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ProfileActivity.this, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(ProfileActivity.this, "Successfull.", Toast.LENGTH_LONG).show();
                    //   currentuser = response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "failure , check your connection", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void uploadFile(Uri fileUri) {

        progress.show();


        // create upload service client
        Api service =
                ServiceGenerator.createService(Api.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);

        //   File imagefile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() ,file.getAbsolutePath() );

        final Bitmap selectedImage = BitmapFactory.decodeFile(file.getAbsolutePath());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 30, stream);
        byte[] byteArray = stream.toByteArray();
        String strBase64 = Base64.encodeToString(byteArray, 0);

        // strBase64

        HashMap<String, String> data = new HashMap<>();

        data.put("user_id", String.valueOf(currentuser.getId()));
        data.put("image", strBase64);

        Call<ResponseBody> call = service.update_user_image(data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Log.v("Upload", "success");
                Toast.makeText(ProfileActivity.this, "picture uploaded successfully", Toast.LENGTH_LONG).show();
                picimage.setImageBitmap(selectedImage);


                SharedPreferences loginPreferences;

                loginPreferences = ProfileActivity.this.getSharedPreferences("loginPrefs", MODE_PRIVATE);

                //    login(loginPreferences.getString("username", "") , loginPreferences.getString("password", ""));

                progress.dismiss();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Log.e("Upload error:", t.getMessage());
                Toast.makeText(ProfileActivity.this, "Check internet", Toast.LENGTH_LONG).show();
                progress.dismiss();

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            uploadFile(uri);
            progress.show();
        }
    }


}
