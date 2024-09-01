package com.vincent.videocompressor;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.tools.*;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * @author tangxiaopeng
 * @dec 首页
 * @date 2018/10/16 18:37
 */
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_FOR_VIDEO_FILE = 1000;
    private TextView tv_input, tv_output, tv_indicator, tv_progress;
    private String outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

    private String inputPath;
    private String outputPath;

    private ProgressBar pb_compress;

    private long startTime, endTime;
    String destPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initView();
    }


    private void initView() {
        Button btn_select = (Button) findViewById(R.id.btn_select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLoacalVideo();
            }
        });


        Button btn_compress = (Button) findViewById(R.id.btn_compress);
        btn_compress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destPath = tv_output.getText().toString() + File.separator + "out_VID_" + new SimpleDateFormat("yyyyMMdd_HHmmss", getLocale()).format(new Date()) + ".mp4";
                VideoCompress.compressVideoMedium(tv_input.getText().toString(), destPath, new VideoCompress.CompressListener() {
                    @Override
                    public void onStart() {

                        tv_indicator.setText(String.format("Compressing...\nStart at: %s", new SimpleDateFormat("HH:mm:ss", getLocale()).format(new Date())));
                        pb_compress.setVisibility(View.VISIBLE);
                        startTime = System.currentTimeMillis();

                    }

                    @Override
                    public void onSuccess() {
                        String previous = tv_indicator.getText().toString();
                        tv_indicator.setText(String.format("%s\nCompress Success!\nEnd at: %s", previous, new SimpleDateFormat("HH:mm:ss", getLocale()).format(new Date())));
                        pb_compress.setVisibility(View.INVISIBLE);
                        endTime = System.currentTimeMillis();

//                        startActivity(new Intent(MainActivity.this, VideoActivity.class).putExtra("vvVideo", destPath));

                    }

                    @Override
                    public void onFail() {
                        tv_indicator.setText("Compress Failed!");
                        pb_compress.setVisibility(View.INVISIBLE);
                        endTime = System.currentTimeMillis();
                    }

                    @Override
                    public void onProgress(float percent) {
                        tv_progress.setText(String.format("%s%%", percent));
                    }
                });
            }
        });

        tv_input = (TextView) findViewById(R.id.tv_input);
        tv_output = (TextView) findViewById(R.id.tv_output);
        tv_output.setText(outputDir);
        tv_indicator = (TextView) findViewById(R.id.tv_indicator);
        tv_progress = (TextView) findViewById(R.id.tv_progress);

        pb_compress = (ProgressBar) findViewById(R.id.pb_compress);
    }

    private void addLoacalVideo() {
        Intent intentvideo = new Intent();
        intentvideo.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intentvideo.addCategory(Intent.CATEGORY_OPENABLE);
        intentvideo.setType("video/*");
        startActivityForResult(Intent.createChooser(intentvideo, "选择要导入的视频"), REQUEST_FOR_VIDEO_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOR_VIDEO_FILE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                inputPath = AndroidContent.absolutePathFromUri(this, data.getData());
                tv_input.setText(inputPath);
            }
        }
    }

    private Locale getLocale() {
        return getResources().getConfiguration().locale;
    }

}
