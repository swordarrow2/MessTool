package com.meng.tools.ffmpeg;

import android.content.*;

import com.meng.tools.*;

import java.io.*;

import static com.meng.messtool.ApplicationHolder.*;

public class FFmpeg {

    private static FFmpeg instance = null;

    public void init(Context context) throws IOException {
        File ffmpegFile = new File(context.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + "ffmpeg");
        if (!ffmpegFile.exists()) {
            FileTool.copyAssetsToData(context, "ffmpeg");
        }
        if (ffmpegFile.canExecute() || ffmpegFile.setExecutable(true)) {
            showToast("FFmpeg初始化成功");
            return;
        }
        throw new IllegalStateException("ffmpeg init failed!");
    }

    public static FFmpeg getInstance(Context context) {
        if (instance == null) {
            instance = new FFmpeg();
        }
        return instance;
    }
}
