package com.itheima.bipmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        initView();
    }

    private void initView() {
        bt = (Button) findViewById( R.id.bt );

        bt.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
             startActivity( new Intent( Main2Activity.this,MainActivity.class ) );
                break;
        }
    }
}
