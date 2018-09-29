package com.example.ashutoshshrivastava.weatherinformation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ashutoshshrivastava on 29/08/18.
 */

public interface Weather1stService {

    @GET("/data/2.5/weather")
    Call<Weather1stActivity> getWeather(@Query("lat") String number,@Query("lon") String number2,@Query("appid") String number3);
}
/////////////////////////////////////////////CREATED BY ASHU SRV//////////////////////////////////////////////////////////a