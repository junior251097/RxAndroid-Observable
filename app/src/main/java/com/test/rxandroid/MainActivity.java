package com.test.rxandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

        String token = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjVBQTFBNDMzRDQxNjI5NzI4NTk1QUEzQzAyMEVDRDJCRTg3Mzc1NDYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJXcUdrTTlRV0tYS0ZsYW84QWc3TkstaHpkVVkifQ.eyJuYmYiOjE1ODU5NTgzODcsImV4cCI6MTU4NjAwODM4NywiaXNzIjoiaHR0cDovL29uYm9hcmRpbmctbGItZGV2LTE3Njk5NDkyMzUudXMtZWFzdC0xLmVsYi5hbWF6b25hd3MuY29tL2lkZW50aXR5IiwiYXVkIjpbImh0dHA6Ly9vbmJvYXJkaW5nLWxiLWRldi0xNzY5OTQ5MjM1LnVzLWVhc3QtMS5lbGIuYW1hem9uYXdzLmNvbS9pZGVudGl0eS9yZXNvdXJjZXMiLCJjYXBwdGFhcGkiLCJvbmJvYXJkaW5nYXBpIl0sImNsaWVudF9pZCI6IndlYiIsInN1YiI6IiIsImF1dGhfdGltZSI6MTU4NTk1ODM4NywiaWRwIjoibG9jYWwiLCJ1c2VySWQiOiJ1c3VwZXJ2aXNvciIsInVzZXJOYW1lIjoiR2VzdGlvbiB5IFNpc3RlbWFzIiwidXNlciI6Ikdlc3Rpb24geSBTaXN0ZW1hcyIsImNvcnJlbyI6Im1pbGFncm9zQGdlc3Rpb255c2lzdGVtYXMuY29tIiwiY2hhbmdlUGFzc3dvcmQiOiIwIiwiZXhwaXJlZFBhc3N3b3JkIjoiMCIsInJvbElkIjoiUjAwMDMiLCJyb2xOYW1lIjoiU3VwZXJ2aXNvciIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJjYXBwdGFhcGkiLCJvbmJvYXJkaW5nYXBpIl0sImFtciI6WyJteXNxbGRiIl19.fuHwejqu9Oq5UJiCaoDVb-TFUGvhN4QT0FwOCHbizQNz0X-Tq0QmzbwNT1P1EQWy1AcpAGIhp0o72ZHUySe8Z-tAoMUKtZaaNqGliRzBHSesnL0I_qaQhC0__q5ZuQpwOM0XrA9hn71wFCU-nczAcBm6Kc-EQERJh7xqrAJYOmSRKlgqSll-hMtjyrxO404qCpUtnV7sAwDTTvfQF2F-u0gYgSz0qx9KDr_MAzNUHLew7d-MdHXl02-E8JIKsCHyDe1VWwb9ShZBIvE_pAxgIOd02UfErc7nk8nBJvsysOPR48jolBaU7R3yDMZwtEgU6TbGX_o3IAaslq8ZPxz4ag";

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
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Toast.makeText(getApplicationContext(),"onSubscribe", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Object o) {
                        Toast.makeText(getApplicationContext(),"onNext", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),"onError", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(),"onComplete", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.btn_call_service)
    public void call(){
        callService();
    }
}
