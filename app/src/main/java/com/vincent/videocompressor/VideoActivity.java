package com.vincent.videocompressor;

import android.os.*;
import android.support.v7.app.*;
import android.widget.*;

import com.meng.messtool.*;

public class VideoActivity extends AppCompatActivity {

    String geturl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView vvVideo = (VideoView) findViewById(R.id.vvVideo);
        geturl = getIntent().getStringExtra("vvVideo");
        vvVideo.setVideoPath(geturl);
        vvVideo.start();
    }
}
