package com.wangban.yzbbanban.myapplication_achieve_picture;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

import com.wangban.yzbbanban.myapplication_achieve_picture.adapter.PictureAdapter;
import com.wangban.yzbbanban.myapplication_achieve_picture.app.PictureApplication;
import com.wangban.yzbbanban.myapplication_achieve_picture.dal.PictureDao;
import com.wangban.yzbbanban.myapplication_achieve_picture.entity.Pictures;

public class MainActivity extends Activity {
    private ImageView ivImage;
    private TextView tvPath1;
    private TextView tvPath2;
    public static final String TAG = "supergirl";
    private GridView gvLocalImage;
    private PictureApplication app;
    private List<Pictures> pics;
    private PictureAdapter adapter;
    private Handler handler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvPath1 = (TextView) findViewById(R.id.tv_path1);
        tvPath2 = (TextView) findViewById(R.id.tv_path2);
        gvLocalImage = (GridView) findViewById(R.id.gv_image);

        PictureDao dao = new PictureDao();
        //Log.i(TAG, "run: helle~~~~~11111");
        app = (PictureApplication) getApplication();
        //Log.i(TAG, "run: helle~~~~~22222");
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                   // Log.i(TAG, "run: helle~~~~~33333");
                    pics = app.getPictures();
                   // Log.i(TAG, "run: helle~~~~~44444");
                    //Log.i(TAG, "run: "+pics.get(0).getPath());


                    adapter = new PictureAdapter(MainActivity.this, pics);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gvLocalImage.setAdapter(adapter);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }


}