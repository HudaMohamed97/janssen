package eg.com.cat.janssen.Presenter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static eg.com.cat.janssen.WebServices.Services.MAIN_URL;

public class ServiceGenerator {


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(MAIN_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


}
