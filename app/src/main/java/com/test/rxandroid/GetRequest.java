package com.test.rxandroid;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GetRequest {

    @GET("api/Parameter/{id}")
    Observable<List<ParameterResponse>> getParameters(@Header("Authorization") String token,
                                                      @Path("id")String idParameter);
}
