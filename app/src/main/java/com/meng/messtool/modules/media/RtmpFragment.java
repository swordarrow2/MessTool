package com.meng.messtool.modules.ffmpeg;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.messtool.system.task.*;
import com.meng.tools.*;
import com.meng.messtool.customview.MaterialDesign.*;
import com.meng.tools.app.*;
import com.meng.tools.ffmpeg.*;

import java.io.*;
import java.lang.Process;

public class RtmpFragment extends BaseFragment {

    private MDEditText etRtmpServer;
    private MDEditText etPushCode;
    private Button btnSelectFile;
    private Button btnStart;
    private LinearLayout rootLayout;

    private File fileToPush;

    @Override
    public String getTitle() {
        return "ffmpeg rtmp推流";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.video_rtmp_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etRtmpServer = (MDEditText) view.findViewById(R.id.rtmp_mainEditTextUrl);
        etPushCode = (MDEditText) view.findViewById(R.id.rtmp_mainEditTextCode);
        btnSelectFile = (Button) view.findViewById(R.id.rtmp_mainButtonSelect);
        btnStart = (Button) view.findViewById(R.id.rtmp_mainButtonStart);
        rootLayout = (LinearLayout) view.findViewById(R.id.rtmp_mainLinearLayout);
        btnStart.setOnClickListener(click);
        btnSelectFile.setOnClickListener(click);
        try {
            FFmpeg.getInstance(this.getActivity()).init(getActivity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        etRtmpServer.setText(SharedPreferenceHelper.getRtmpAddr());
        etPushCode.setText(SharedPreferenceHelper.getRtmpCode());
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rtmp_mainButtonSelect:
                    selectVideo();
                    v.setEnabled(true);
                    break;
                case R.id.rtmp_mainButtonStart:
                    String rtmp = etRtmpServer.getString();
                    String pushCode = etPushCode.getString();
                    v.setEnabled(false);
                    if (!rtmp.startsWith("rtmp://")) {
                        showToast("不是合法的rtmp地址");
                        return;
                    }
                    if (rtmp.equals("rtmp://")) {
                        showToast("地址不能为空");
                        return;
                    }
                    if (pushCode.equals("")) {
                        showToast("推流码不能为空");
                        return;
                    }
                    SharedPreferenceHelper.setRtmpAddr(rtmp);
                    SharedPreferenceHelper.setRtmpCode(pushCode);
                    try {
                        push(rtmp, pushCode);
                        showToast("开始向" + rtmp + pushCode + "推流");
                    } catch (IOException e) {
                        ExceptionCatcher.getInstance().uncaughtException(Thread.currentThread(), e);
                    }
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.REQUEST_CODE_SELECT_FILE && data.getData() != null) {
                fileToPush = new File(AndroidContent.absolutePathFromUri(getActivity().getApplicationContext(), data.getData()));
                btnSelectFile.setText(String.format("已选择%s", fileToPush.getName()));
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            showToast("取消选择文件");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void push(String addr, String code) throws IOException {
        Process process = Runtime.getRuntime().exec(
                getActivity().getFilesDir().getAbsolutePath() + File.separator + "ffmpeg -re -i " + fileToPush.getAbsolutePath() +
                        " -c copy -acodec aac -f flv " +
                        addr + code  // "rtmp://live-push.bilivideo.com/live-bvc/" + "?streamname=live_64483321_2582558&key=7776fcf83eb2bb7883733f598285caf7&schedule=rtmp"
        );
        new FFmpegBackTask(process, "直播推流" + System.currentTimeMillis()).setTitle("推流至" + addr).start();
    }
}
