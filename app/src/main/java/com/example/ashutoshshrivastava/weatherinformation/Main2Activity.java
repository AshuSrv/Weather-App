package com.example.ashutoshshrivastava.weatherinformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Main2Activity extends AppCompatActivity {
ImageView imageView;
TextView textView,textView2,textView3,textView4,textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.textView2);
        textView2=findViewById(R.id.Description);
        textView3=findViewById(R.id.Humidity);
        textView4=findViewById(R.id.WindSpeed);
        textView5=findViewById(R.id.Pressure);

        Intent intent = getIntent();
        String main=intent.getStringExtra("main");
        String Description=intent.getStringExtra("Description");
        String Humidity=intent.getStringExtra("Humidity");
        String temp=intent.getStringExtra("temp");
        String WindSpeed=intent.getStringExtra("windspeed");

        Float result = Float.valueOf(temp);
        result = result-250;
        result=result*5;
        result=result/9;
       result= Float.valueOf(new DecimalFormat("##.#").format(result));
        String S=Float.toString(result);

        textView.setText(main);
        textView2.setText(Description);
        textView3.setText(Humidity+"%");
        textView4.setText(S + " Celcius");
        textView5.setText(WindSpeed+" km/h");

    }


}


/////////////////////////////////////////////CREATED BY ASHU SRV//////////////////////////////////////////////////////////