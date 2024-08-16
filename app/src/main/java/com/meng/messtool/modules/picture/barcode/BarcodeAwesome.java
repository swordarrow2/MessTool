package com.meng.messtool.modules.picture.barcode;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.customview.*;
import com.meng.tools.*;
import com.meng.tools.MaterialDesign.*;
import com.meng.tools.app.*;

import java.io.*;

public class BarcodeAwesome extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView qrCodeImageView;
    private MDEditText mengEtDotScale, mengEtContents, mengEtMargin, mengEtSize;
    private CheckBox ckbWhiteMargin;
    private Bitmap backgroundImage = null;

    private boolean generating = false;
    private CheckBox ckbAutoColor;
    private ScrollView scrollView;
    private CheckBox ckbBinarize;
    private CheckBox cbCrop;
    private MDEditText mengEtBinarize;
    private Button btnSave;
    private TextView imgPathTextView;
    private Bitmap bmpQRcode = null;
    private MengColorBar mColorBar;

    @Override
    public String getTitle() {
        return "Awesome二维码";
    }

    @Override
    public String getVersionName() {
        return "V1.0.0";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.function_picture_barcode_awesome_qr_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mColorBar = (MengColorBar) view.findViewById(R.id.gif_arb_qr_main_colorBar);
        scrollView = (ScrollView) view.findViewById(R.id.awesomeqr_main_scrollView);
        qrCodeImageView = (ImageView) view.findViewById(R.id.awesomeqr_main_qrcode);
        mengEtContents = (MDEditText) view.findViewById(R.id.awesomeqr_main_content);
        mengEtSize = (MDEditText) view.findViewById(R.id.awesomeqr_main_mengEdittext_size);
        mengEtMargin = (MDEditText) view.findViewById(R.id.awesomeqr_main_margin);
        mengEtDotScale = (MDEditText) view.findViewById(R.id.awesomeqr_main_dotScale);
        ckbWhiteMargin = (CheckBox) view.findViewById(R.id.awesomeqr_main_whiteMargin);
        ckbAutoColor = (CheckBox) view.findViewById(R.id.awesomeqr_main_autoColor);
        ckbBinarize = (CheckBox) view.findViewById(R.id.awesomeqr_main_binarize);
        mengEtBinarize = (MDEditText) view.findViewById(R.id.awesomeqr_main_mengEdittext_binarizeThreshold);
        btnSave = (Button) view.findViewById(R.id.awesomeqr_mainButton_save);
        imgPathTextView = (TextView) view.findViewById(R.id.awesomeqr_main_imgPathTextView);
        cbCrop = (CheckBox) view.findViewById(R.id.awesomeqr_main_crop);
        ckbAutoColor.setOnCheckedChangeListener(this);
        ckbBinarize.setOnCheckedChangeListener(this);
        ((Button) view.findViewById(R.id.awesomeqr_main_backgroundImage)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.awesomeqr_main_removeBackgroundImage)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.awesomeqr_main_generate)).setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.awesomeqr_main_autoColor:
                mColorBar.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                if (!isChecked) {
                    showToast("如果颜色搭配不合理,二维码将会难以识别");
                }
                break;
            case R.id.awesomeqr_main_binarize:
                mengEtBinarize.setEnabled(isChecked);
                mengEtBinarize.setVisible(isChecked);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.awesomeqr_main_backgroundImage:
                selectImage();
                break;
            case R.id.awesomeqr_main_removeBackgroundImage:
                backgroundImage = null;
                imgPathTextView.setVisibility(View.GONE);
                showToast(getResources().getString(R.string.Background_image_removed));
                break;
            case R.id.awesomeqr_main_generate:
                generate(mengEtContents.getString(),
                        mengEtSize.getInt(),
                        mengEtMargin.getInt(),
                        Float.parseFloat(mengEtDotScale.getString()),
                        mColorBar.getTrueColor(),
                        ckbAutoColor.isChecked() ? Color.WHITE : mColorBar.getFalseColor(),
                        backgroundImage,
                        ckbWhiteMargin.isChecked(),
                        ckbAutoColor.isChecked(),
                        ckbBinarize.isChecked(),
                        mengEtBinarize.getInt()
                );
                btnSave.setVisibility(View.VISIBLE);
                break;
            case R.id.awesomeqr_mainButton_save:
                try {
                    String s = FileTool.saveToFile(FileTool.getAppFile(FunctionSavePath.awesomeQR, FileTool.FileType.png), bmpQRcode);
                    showToast("已保存至" + s);
                    getActivity().getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(s))));//更新图库
                } catch (IOException e) {
                    showToast("保存出错");
                    break;
                }
                break;
        }
    }

    public void setDataStr(String s) {
        mengEtContents.setString(s);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.REQUEST_CODE_SELECT_FILE && data.getData() != null) {
                imgPathTextView.setVisibility(View.VISIBLE);
                String path = AndroidContent.absolutePathFromUri(getActivity().getApplicationContext(), data.getData());
                imgPathTextView.setText(String.format("当前图片：%s", path));
                if (cbCrop.isChecked()) {
                    Intent in = new Intent(getActivity(), CropActivity.class);
                    in.putExtra("path", path);
                    startActivityForResult(in, Constant.REQUEST_CODE_CROP_PICTURE);
                } else {
                    backgroundImage = BitmapFactory.decodeFile(path);
                }
            } else if (requestCode == Constant.REQUEST_CODE_CROP_PICTURE) {
                byte[] bis = data.getByteArrayExtra("bitmap");
                if (bis != null) {
                    backgroundImage = BitmapFactory.decodeByteArray(bis, 0, bis.length);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            showToast("取消选择图片");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void generate(final String contents, final int size, final int margin, final float dotScale,
                          final int colorDark, final int colorLight, final Bitmap background, final boolean whiteMargin,
                          final boolean autoColor, final boolean binarize, final int binarizeThreshold) {
        if (generating) return;
        generating = true;
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Bitmap b = AwesomeQRCode.create(contents, size, margin, dotScale, colorDark, colorLight, background, whiteMargin, autoColor, binarize, binarizeThreshold);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            qrCodeImageView.setImageBitmap(b);
                            bmpQRcode = b;
                            scrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.fullScroll(View.FOCUS_DOWN);
                                }
                            });
                            generating = false;
                        }
                    });
                } catch (Exception e) {
                    showToast(e.toString());
                    generating = false;
                }
            }
        });

    }
}
