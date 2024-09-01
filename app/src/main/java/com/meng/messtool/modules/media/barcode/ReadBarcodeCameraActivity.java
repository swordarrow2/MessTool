package com.meng.messtool.modules.media.barcode;

import android.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.os.*;
import android.support.annotation.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import com.google.zxing.*;
import com.meng.messtool.R;
import com.meng.tools.app.*;
import com.meng.tools.zxing.camera.*;
import com.meng.tools.zxing.decoding.*;
import com.meng.tools.zxing.view.*;

import java.util.*;

public class ReadBarcodeCameraActivity extends Activity {
    private final int REQUEST_PERMISSION_CAMERA = 1000;
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private boolean flashLightOpen = false;
    private ImageButton flashIbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_barcode_read_camera);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        CameraManager.init(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
        }
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        flashIbtn = (ImageButton) findViewById(R.id.flash_ibtn);
        flashIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashIbtn.setImageResource(flashLightOpen ? R.drawable.ic_flash_off_white_24dp : R.drawable.ic_flash_on_white_24dp);
                toggleFlashLight();
            }
        });
    }

    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        SystemTools.doVibrate(this, 200);
        handleResult(result.getText(), result.getBarcodeFormat().toString());
    }

    public void handleResult(final String result, String format) {
        if (TextUtils.isEmpty(result)) {
            restartPreview();
        } else {
            Intent re = new Intent();
            re.putExtra("result", result);
            re.putExtra("format", format);
            setResult(RESULT_OK, re);
            finish();
        }
    }

    protected void setViewfinderView(ViewfinderView view) {
        viewfinderView = view;
    }

    public void toggleFlashLight() {
        setFlashLightOpen(!flashLightOpen);
    }

    public void setFlashLightOpen(boolean open) {
        if (flashLightOpen == open) return;
        flashLightOpen = !flashLightOpen;
        CameraManager.get().setFlashLight(open);
    }

    public boolean isFlashLightOpen() {
        return flashLightOpen;
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (Exception e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
        }
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    protected void restartPreview() {
        // 当界面跳转时 handler 可能为null
        if (handler != null) {
            Message restartMessage = Message.obtain();
            restartMessage.what = R.id.restart_preview;
            handler.handleMessage(restartMessage);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(getClass().toString(), "camera open");
        //LogTool.t("camera open");
//        View sv = this.getView();
//        if (sv == null) {
//            return;
//        }
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }

                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    if (!hasSurface) {
                        hasSurface = true;
                        initCamera(holder);
                    }
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    hasSurface = false;
                }

            });
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(getClass().toString(), "camera close");
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        if (flashIbtn != null) {
            flashIbtn.setImageResource(R.drawable.ic_flash_off_white_24dp);
        }
        CameraManager cm = CameraManager.get();
        if (cm != null) {
            cm.closeDriver();
        }
    }

    @Override
    public void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && requestCode == REQUEST_PERMISSION_CAMERA) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 未获得Camera权限
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("请在系统设置中为App开启摄像头权限后重试")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
                            }
                        }).show();
            }
        }
    }
}
