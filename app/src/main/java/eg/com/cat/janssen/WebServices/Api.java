package eg.com.cat.janssen.WebServices;

import java.util.Map;

import eg.com.cat.janssen.model.AllDoctorsResponseModel;
import eg.com.cat.janssen.model.CityChartResponseModel;
import eg.com.cat.janssen.model.CountryChartModel;
import eg.com.cat.janssen.model.CountryResponseModel;
import eg.com.cat.janssen.model.DeletePatientResponseModel;
import eg.com.cat.janssen.model.DocModelResponse;
import eg.com.cat.janssen.model.FullChartResponseModel;
import eg.com.cat.janssen.model.LoginResponseModel;
import eg.com.cat.janssen.model.MonthChartResponseModel;
import eg.com.cat.janssen.model.UserFullChart;
import eg.com.cat.janssen.model.yearChartResponseModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @Headers("Content-Type: application/json")
    @POST("user/add")
    Call<ResponseBody> Register(@Body Map<Object, Object> headers);


    @POST("add-doctor")
    Call<ResponseBody> addDoc(@Body Map<Object, Object> headers);


    @POST("update-user")
    Call<ResponseBody> update_user(@Body Map<String, String> headers);

    @POST("change-password")
    Call<ResponseBody> update_pass(@Body Map<String, String> headers);

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginResponseModel> Login(@Body Map<String, String> headers);

    @POST("reset-password")
    Call<ResponseBody> Reset_Password(@Body Map<String, String> headers);


    @POST("update-user-image")
    Call<ResponseBody> update_user_image(@Body Map<String, String> headers);


    @POST("update-player-id")
    Call<ResponseBody> update_player_id(@HeaderMap Map<String, String> headers);


    @GET("countries")
    Call<CountryResponseModel> getCountries();


    @POST("search")
    Call<DocModelResponse> searchDoctor(@Body Map<String, String> headers);


    @GET("by-city")
    Call<CityChartResponseModel> getCityChart();

    @GET("by-country")
    Call<CountryChartModel> getCountryChart();

    @GET("by-month")
    Call<MonthChartResponseModel> getMonthChart();

    @GET("by-year")
    Call<yearChartResponseModel> getYearChart();

    @GET("full-chart")
    Call<FullChartResponseModel> getFullChart();

    @GET("user/{user}/logs")
    Call<AllDoctorsResponseModel> getAllDocs(@Path(value = "user", encoded = true) String id);


    @POST("patient/{patient}/delete")
    Call<DeletePatientResponseModel> deleteDocs(@Path(value = "patient", encoded = true) String id, @Body Map<String, String> headers);

    @GET("user-chart/{user}")
    Call<UserFullChart> getUserChart(@Path(value = "user", encoded = true) String id);

    @POST("update-player-id")
    Call<ResponseBody> updatePlayerId(@Body Map<String, String> headers);


}
