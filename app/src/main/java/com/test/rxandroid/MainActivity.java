package com.test.rxandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_call_service)
    TextView btn_call_service;

    private static final OkHttpClient httpClient  = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @SuppressLint("CheckResult")
    public void callService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://onboarding-lb-dev-1769949235.us-east-1.elb.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();

        GetRequest getRequest = retrofit.create(GetRequest.class);

        List<Observable<?>> requests = new ArrayList<>();

        String token = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjIzNTJjM2Q2NDIwYTI0YmFiZTRmMTU1ZDRiODI1ZGY5IiwidHlwIjoiSldUIn0.eyJuYmYiOjE1ODIxNTYxNzYsImV4cCI6MTU4MjIwNjE3NiwiaXNzIjoiaHR0cDovL29uYm9hcmRpbmctbGItZGV2LTE3Njk5NDkyMzUudXMtZWFzdC0xLmVsYi5hbWF6b25hd3MuY29tL2lkZW50aXR5IiwiYXVkIjpbImh0dHA6Ly9vbmJvYXJkaW5nLWxiLWRldi0xNzY5OTQ5MjM1LnVzLWVhc3QtMS5lbGIuYW1hem9uYXdzLmNvbS9pZGVudGl0eS9yZXNvdXJjZXMiLCJvbmJvYXJkaW5nYXBpIl0sImNsaWVudF9pZCI6IndlYiIsInN1YiI6IiIsImF1dGhfdGltZSI6MTU4MjE1NjE3NiwiaWRwIjoibG9jYWwiLCJ1c2VySWQiOiJneXMiLCJ1c2VyTmFtZSI6Ikdlc3Rpb24geSBTaXN0ZW1hcyIsInVzZXIiOiJHZXN0aW9uIHkgU2lzdGVtYXMiLCJjb3JyZW8iOiJneXNAZ2VzdGlvbnlzaXN0ZW1hcy5jb20iLCJjaGFuZ2VQYXNzd29yZCI6IjAiLCJleHBpcmVkUGFzc3dvcmQiOiIwIiwicm9sSWQiOiJSMDAwMiIsInJvbE5hbWUiOiJHZXN0b3IiLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIiwib25ib2FyZGluZ2FwaSJdLCJhbXIiOlsibXlzcWxkYiJdfQ.cIRqZsFpatwJ_w4UsZL4ebLpPaFTTixoYYzviS8x_bXkIMWwaZIWgkoDpjMRWcXwxW6-1GsIOuuxItxMBTCj88plXKeC3KB27fV6_8YPZZf70W0W40YTwfngd7Rr_sw0wpVDvxt3y6kjb-kV6fHfcNGmAs6LeCIqMBtVH9-yQKjBqW__Dczx5wsmlY-rNECvmNVOiSz5jS_JJlzIvgxNoJpVBArvJnZo955KEfOhYcY_4A8qNR7_FRvKwUGgcNgrnNn-7uTmyqw20fYAEp_RCcqg8HUNNk3wh7vMCrCqJvTtHhUYuMrAQcjRy395fWP9MEYS6hCDx3OWg36l1-uRuA";

        requests.add(getRequest.getParameters(token, Constants.PARAMETER_DEPARTMENT));
        requests.add(getRequest.getParameters(token, Constants.PARAMETER_PROVINCE));
        requests.add(getRequest.getParameters(token, Constants.PARAMETER_DISTRICT));

        Observable.zip(requests,
                new Function<Object[], Object>() {
                    @Override
                    public Object apply(Object[] objects) throws Exception {
                        // Objects[] is an array of combined results of completed requests

                        // do something with those results and emit new event
                        return new Object();
                    }
                }).subscribe(
                // Will be triggered if all requests will end successfully (4xx and 5xx also are successful requests too)
                new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //Do something on successful completion of all requests
                    }
                },

                // Will be triggered if any error during requests will happen
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        //Do something on error completion of requests
                    }
                }
        );
    }

    @OnClick(R.id.btn_call_service)
    public void call(){
        callService();
    }
}
