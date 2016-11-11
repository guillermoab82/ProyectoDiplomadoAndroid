package mx.unam.posgrado.eventoscep.data;

import android.os.Build;
import mx.unam.posgrado.eventoscep.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GuillermoAB on 27/09/2016.
 */

public class EventData {
    static String  buildconfigIN;
    public static Retrofit getRetofitInstance(Integer nURL){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(httpLoggingInterceptor);
   if (nURL == 1) { buildconfigIN = BuildConfig.URL;} else {buildconfigIN = BuildConfig.URL1;}
        return new Retrofit.Builder().baseUrl(buildconfigIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
    }
}
