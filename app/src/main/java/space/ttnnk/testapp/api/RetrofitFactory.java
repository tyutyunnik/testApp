package space.ttnnk.testapp.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitFactory {
    private static final String BASE_URL = "https://demo3005513.mockable.io/api/v1/";

    private static TestApi testApi;
    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder retrofitBuilder =  new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {

        OkHttpClient client = okHttpClient.connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = retrofitBuilder.baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(serviceClass);
    }

    public static TestApi getTestApi() {
        if (testApi == null) {
            testApi = createService(TestApi.class, BASE_URL);
        }
        return testApi;
    }
}
