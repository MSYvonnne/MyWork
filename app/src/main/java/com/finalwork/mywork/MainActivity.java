package com.finalwork.mywork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void onClick(View btn) {
        Intent rate= new Intent(this,List1Activity.class);

        startActivityForResult(rate,1);
    }


}
