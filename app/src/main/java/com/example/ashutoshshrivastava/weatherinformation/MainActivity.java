package com.example.ashutoshshrivastava.weatherinformation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText editText,editText2;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText3);
        editText2 = findViewById(R.id.editText4);
   button=findViewById(R.id.button2);



   button.setOnClickListener(new View.OnClickListener() {
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("https://devru-latitude-longitude-find-v1.p.mashape.com/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       @Override
       public void onClick(View v) {



           String para2=editText.getText().toString().trim();
           String para1=editText2.getText().toString().trim();
           String paramter=para1 + "+" + para2;
           String zero="+";
           if(paramter.compareTo(zero)==0 || para2.compareTo("")==0 || para1.compareTo("")==0)
           {
               Toast.makeText(MainActivity.this,"Please enter in both the fields",Toast.LENGTH_SHORT).show();

           }
           else {


               latlongservice latlongserviceinterface = retrofit.create(latlongservice.class);
               latlongserviceinterface.getlatlon(" Fm7BtZLe9EmshYcc4HDw7LPN7Ofsp1iZ2OEjsnt5YozbekWyfx", paramter).enqueue(new Callback<latlong1>() {
                   @Override
                   public void onResponse(Call<latlong1> call, Response<latlong1> response) {
                       latlong1 obj = response.body();
                    if(obj.Results.isEmpty())
                    {
                        Toast.makeText(MainActivity.this,"City Not Found!\nPlease enter the correct city name",Toast.LENGTH_LONG).show();
                    }
                    else {
                        String lat = obj.Results.get(0).lat;
                        String lon = obj.Results.get(0).lon;


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://api.openweathermap.org/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        Weather1stService weather1stService = retrofit.create(Weather1stService.class);
                        weather1stService.getWeather(lat, lon, "13f775ac6ebb4507e26376db6e3530a6").enqueue(new Callback<Weather1stActivity>() {
                            @Override
                            public void onResponse(Call<Weather1stActivity> call, Response<Weather1stActivity> response) {
                                Weather1stActivity obbj = response.body();
                                String main = obbj.weather.get(0).main;
                                String Description = obbj.weather.get(0).description;
                                String Humidity = obbj.main.humidity;
                                String temp = obbj.main.temp;
                                String windspeed = obbj.wind.speed;

                                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                intent.putExtra("main", main);
                                intent.putExtra("Description", Description);
                                intent.putExtra("Humidity", Humidity);
                                intent.putExtra("temp", temp);
                                intent.putExtra("windspeed", windspeed);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Weather1stActivity> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                       }


                   @Override
                   public void onFailure(Call<latlong1> call, Throwable t) {
                       Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_LONG).show();

                   }
               });

           }
       }
   });

    }

}



/////////////////////////////////////////////CREATED BY ASHU SRV//////////////////////////////////////////////////////////