package com.meng.messtool.modules.media.convert;

import android.media.*;

import com.meng.messtool.system.base.*;

import java.util.*;


/*
 *package  com.meng.messtool.modules.media.convert
 *@author  清梦
 *@date    2024/9/1 22:49
 */
public class MediaConvert extends BaseFragment {
    @Override
    public String getVersionName() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    private void test() {
        MediaCodecList list = new MediaCodecList(0);
        MediaCodec mAudioEncoder;
        String AUDIO_MIME = "audio/mp4a-latm";
        StringBuilder builder = new StringBuilder();
        for (MediaCodecInfo c : list.getCodecInfos()) {
            builder.append(c.getName()).append(Arrays.asList(c.getSupportedTypes()));
        }
        showToast(builder.toString());
    }
}
