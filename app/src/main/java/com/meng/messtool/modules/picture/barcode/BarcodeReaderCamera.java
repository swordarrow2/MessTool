package com.meng.messtool.modules.picture.barcode;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;

public class BarcodeReaderCamera extends BaseFragment implements View.OnClickListener {
    private Button btnCopy;
    private Button btnRescan;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.function_picture_barcode_read_camera_result, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvResult = (TextView) view.findViewById(R.id.function_picture_barcode_read_camera_resultTextView_result);
        tvFormat = (TextView) view.findViewById(R.id.function_picture_barcode_read_camera_resultTextView_format);
        btnCopy = (Button) view.findViewById(R.id.function_picture_barcode_read_camera_resultButton_copy);
        btnRescan = (Button) view.findViewById(R.id.function_picture_barcode_read_camera_resultButton_restart);
        btnCopy.setOnClickListener(this);
        btnRescan.setOnClickListener(this);
        scanBarcode();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.function_picture_barcode_read_camera_resultButton_copy:
                AndroidContent.copyToClipboard(tvResult.getText().toString());
                break;
            case R.id.function_picture_barcode_read_camera_resultButton_restart:
                scanBarcode();
                break;
        }
    }

    protected void handleResult(String resultString, String format) {
        if (!"".equals(resultString)) {
            tvFormat.setText(String.format("二维码类型%s", format));
            tvResult.setText(resultString);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null && requestCode == Constant.REQUEST_CODE_SCAN_BARCODE) {
            tvResult.setText(data.getStringExtra("result"));
            tvFormat.setText(data.getStringExtra("format"));
        }
    }
}
