package com.example.kh.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean a = true;
    private Handler handler;
   @BindView(R.id.txt)
   TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        handler = new Handler(getApplicationContext().getMainLooper() );
    }

    @OnClick(R.id.btnStart)
    public void btnStart(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(a){
                    try {
                        Thread.sleep(1000);
                        Log.i(TAG, "run: "+Thread.currentThread().getId());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                txt.setText(""+Thread.currentThread().getId());

                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    @OnClick(R.id.btnStop)
    public void btnStop(){
        a=false;
    }
}
