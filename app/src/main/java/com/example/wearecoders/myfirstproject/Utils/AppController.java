package com.example.wearecoders.myfirstproject.Utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.example.wearecoders.myfirstproject.Models.SignBean;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by We Are Coders on 2/7/2018.
 */

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
    private static Config config;
    private SharedPreferences preferences;
    public static boolean ONLINE = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("demo.realm").build();
        Realm.setDefaultConfiguration(config);
        mInstance = this;
        AppVisibilityDetector.init(AppController.this, new AppVisibilityDetector.AppVisibilityCallback() {
            @Override
            public void onAppGotoForeground() {
                check();
                ONLINE = true;
            }

            @Override
            public void onAppGotoBackground() {
                Log.e("background", " yes");
                ONLINE = false;
            }
        });

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    private void check() {
        String s = getPref().getString(Constants.LOGGED_IN, null);
        if (s != null) {
            SignBean signBean = new Gson().fromJson(s, SignBean.class);
            //  Log.e(TAG,""+signBean.getUser_name()+signBean.getLoginToken());
            Call<ResponseBody> call = getConfig().check(signBean.getLoginToken(), signBean.getFirst_name());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String s1 = response.body().string();
                        if (s1.contains("false")) {
                            sendBroadcast(new Intent().setAction(Constants.FILTER_CHECK));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public Config getConfig() {
        if (config == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            config = retrofit.create(Config.class);
        }
        return config;
    }

    public SharedPreferences getPref() {
        if (preferences == null)
            preferences = getSharedPreferences(Constants.SHAREPREF, MODE_PRIVATE);
        return preferences;
    }


}
