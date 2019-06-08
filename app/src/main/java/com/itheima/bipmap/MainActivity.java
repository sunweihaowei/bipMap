package com.itheima.bipmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //网络可能获取不到，所以要刷新
    private ImageView iv;
    private Handler handler=new Handler(  ){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            if (msg.what==1){
                Bitmap bitmap= (Bitmap) msg.obj;
                iv.setImageBitmap( bitmap );
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initView();
        new Thread(  ){
            @Override
            public void run() {
                super.run();
                OkHttpClient okHttpClient=new OkHttpClient();
                final Request request=new Request.Builder().url( "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1766145647,4287610426&fm=26&gp=0.jpg" ).build();
                Call call=okHttpClient.newCall( request );
                call.enqueue( new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText( MainActivity.this,"hello,you failure",Toast.LENGTH_LONG ).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap= BitmapFactory.decodeStream( inputStream );
                        Message message=handler.obtainMessage();
                        message.what=1;
                        message.obj=bitmap;
                        handler.sendMessage( message );
                    }
                } );
            }
        }.start();
    }

    private void initView() {
        iv = (ImageView) findViewById( R.id.iv );
    }
}
