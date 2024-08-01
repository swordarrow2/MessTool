package com.meng.messtool.modules.wallpaper;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.tools.*;

import static com.meng.messtool.ApplicationHolder.*;

public class WallpaperMain extends BaseFragment {

    private VideoWallpaper mVideoWallpaper = new VideoWallpaper();
    private Button select, silence, voice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wallpaper_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        select = (Button) view.findViewById(R.id.wallpaper_mainButtonselect);
        silence = (Button) view.findViewById(R.id.wallpaper_mainButtonsilence);
        voice = (Button) view.findViewById(R.id.wallpaper_mainButtonvoice);
        select.setOnClickListener(onclick);
    }

    OnClickListener onclick = new OnClickListener() {

        @Override
        public void onClick(View p1) {
            switch (p1.getId()) {
                case R.id.wallpaper_mainButtonselect:
                    selectVideo();
                    break;
                case R.id.wallpaper_mainButtonsilence:
                    VideoWallpaper.setVoiceSilence(getActivity());
                    break;
                case R.id.wallpaper_mainButtonvoice:
                    VideoWallpaper.setVoiceNormal(getActivity());
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data.getData() != null) {
            if (requestCode == Constant.REQUEST_CODE_SELECT_FILE) {
                final String path = AndroidContent.absolutePathFromUri(getActivity(), data.getData());
                if (path == null) {
                    showToast("选择视频出错");
                    return;
                }
                mVideoWallpaper.setToWallPaper(getActivity(), path);
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            showToast("取消选择视频");
        } else {
            selectImage();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

