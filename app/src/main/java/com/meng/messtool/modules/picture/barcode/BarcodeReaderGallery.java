package com.meng.messtool.modules.picture.barcode;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import com.google.zxing.*;
import com.meng.messtool.*;
import com.meng.messtool.system.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

public class BarcodeReaderGallery extends BaseFragment implements View.OnClickListener {
    private Button btnCreateAwesomeQR;
    private TextView tvResult;
    private TextView tvFormat;

    @Override
    public String getTitle() {
        return "读取二维码";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    public void setResult(String resultString, String format) {
        handleResult(resultString, format);
    }

    public String getLastResult() {
        return tvResult.getText().toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.function_picture_barcode_read_gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnOpenGallery = (Button) view.findViewById(R.id.read_galleryButton);
        tvResult = (TextView) view.findViewById(R.id.read_galleryTextView_result);
        tvFormat = (TextView) view.findViewById(R.id.read_galleryTextView_format);
        btnCreateAwesomeQR = (Button) view.findViewById(R.id.read_galleryButton_createAwesomeQR);
        btnOpenGallery.setOnClickListener(this);
        btnCreateAwesomeQR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read_galleryButton:
                selectImage();
                break;
            case R.id.read_galleryButton_createAwesomeQR:
                MFragmentManager.getInstance().showFragment(BarcodeAwesome.class);
                MFragmentManager.getInstance().getFragment(BarcodeAwesome.class).setDataStr(tvResult.getText().toString());
                break;
        }
    }

    protected void handleResult(String resultString, String format) {
        if (!"".equals(resultString)) {
            tvFormat.setText(String.format("二维码类型%s", format));
            tvResult.setText(resultString);
            btnCreateAwesomeQR.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null && requestCode == Constant.REQUEST_CODE_SELECT_FILE) {
            Uri inputUri = data.getData();
            String path = AndroidContent.absolutePathFromUri(getActivity(), inputUri);
            if (!TextUtils.isEmpty(path)) {
                Result result = QrUtils.decodeImage(path);
                if (result != null) {
                    SystemTools.doVibrate(getActivity(), 200);
                    handleResult(result.getText(), result.getBarcodeFormat().toString());
                } else {
                    showToast("此图片无法识别");
                }
            } else {
                showToast("图片路径未找到");
            }
        }
    }
}
