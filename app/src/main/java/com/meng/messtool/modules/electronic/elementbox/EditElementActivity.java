package com.meng.messtool.modules.electronic.elementbox;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import com.meng.api.lcsc.*;
import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;
import com.meng.tools.MaterialDesign.*;
import com.meng.tools.app.*;
import com.meng.tools.functional.*;
import java.io.*;
import org.jsoup.helper.*;

public class EditElementActivity extends BaseActivity implements View.OnClickListener {

    private ScrollView scrollView;
    private MDEditText et_name;
    private MDEditText et_print;
    private MDEditText et_brand;
    private MDEditText et_describe;
    private MDEditText et_package;
    private MDEditText et_slot_id;
    private MDEditText et_shop_name;
    private MDEditText et_id_in_shop;
    private MDEditText et_count;
    private Button selectImage;
    private Button scan;
    private ImageButton auto;
    private ImageView imageView;
    private Element element;
    private Button ok;
    private Consumer<String> callBack;
    private byte[] image;
    private boolean addMode = true;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.function_electronic_add_element);
        initView();
        initData();
    }

    private void initData() {
        if (addMode) {
            element = new Element(0, null, null, null, null, null, null, null, 0, null, null);
        } else {
            element = (Element) AppStack.pop();
            et_name.setEnabled(false);
            et_name.setText(element._name);
            et_print.setText(element._print);
            et_brand.setText(element._brand);
            et_describe.setText(element._describe);
            et_package.setText(element._package);
            et_slot_id.setText(element._slot_id);
            et_shop_name.setText(element._shop_name);
            et_id_in_shop.setText(element._id_in_shop);
            et_count.setText(String.valueOf(element._rest));
            image = element._picture;
            if (element._picture != null) {
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(element._picture, 0, element._picture.length));
            }
            if (guessNeedAutoFix()) {
                auto.performClick();
            }
        }
    }

    private void initView() {
        scrollView = (ScrollView) findViewById(R.id.add_element_scrollview);
        et_name = (MDEditText) findViewById(R.id.add_elementEditText_name);
        et_print = (MDEditText) findViewById(R.id.add_elementEditText_print);
        et_brand = (MDEditText) findViewById(R.id.add_elementEditText_brand);
        et_describe = (MDEditText) findViewById(R.id.add_elementEditText_describe);
        et_package = (MDEditText) findViewById(R.id.add_elementEditText_package);
        et_slot_id = (MDEditText) findViewById(R.id.add_elementEditText_slot_id);
        et_shop_name = (MDEditText) findViewById(R.id.add_elementEditText_shop_name);
        et_id_in_shop = (MDEditText) findViewById(R.id.add_elementEditText_id_in_shop);
        et_count = (MDEditText) findViewById(R.id.add_elementEditText_count);
        selectImage = (Button) findViewById(R.id.add_elementButton_select_image);
        scan = (Button) findViewById(R.id.add_elementButton_scan);
        imageView = (ImageView) findViewById(R.id.add_element_thumbnail);
        auto = (ImageButton) findViewById(R.id.add_element_auto_button);
        ok = (Button) findViewById(R.id.add_elementButton_save);
        addMode = getIntent().getBooleanExtra("addMode", true);
        scan.setOnClickListener(this);
        auto.setOnClickListener(this);
        selectImage.setOnClickListener(this);
        ok.setOnClickListener(this);
        et_shop_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("lcsc".equals(s.toString())) {
                    auto.setVisibility(View.VISIBLE);
                } else {
                    auto.setVisibility(View.GONE);
                }
            }
        });
    }

    private boolean guessNeedAutoFix() {
        if ("lcsc".equals(et_shop_name.getString()) && !StringUtil.isBlank(et_id_in_shop.getString()) && StringUtil.isBlank(et_describe.getString())) {
            return true;
        }
//        if(){}
        return false;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.add_element_auto_button:
                ThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            if (!"lcsc".equals(et_shop_name.getString())) {
//                                MainActivity.instance.showToast("目前仅立创商城可用自动填充");
//                            } else {
                            generalAutoFix();
//                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            showToast("自动填充失败");
                        }
                    }
                });
                break;
            case R.id.add_elementButton_scan:
                callBack = new Consumer<String>() {
                    @Override
                    public void accept(String v1) {
                        if (StringUtil.isBlank(v1)) {
                            showToast("扫描结果为空");
                            return;
                        }
                        if (v1.contains("pc:C")) {

                            et_name.setEnabled(false);
                            et_brand.setEnabled(false);

                            String lcJson = LcPojo.prepareLcJson(v1);
                            LcPojo pojo = GSON.fromJson(lcJson, LcPojo.class);
                            et_name.setText(pojo.pm);
                            et_shop_name.setText("lcsc");
                            et_id_in_shop.setText(pojo.pc);
                            et_count.setText(pojo.qty);
                            ThreadPool.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        generalAutoFix();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        showToast("自动填充失败");
                                    }
                                }
                            });
                        } else {
                            showToast("没有符合的解析规则：" + v1);
                        }
                    }
                };
                scanBarcode();
                break;
            case R.id.add_elementButton_select_image:
                callBack = new Consumer<String>() {
                    @Override
                    public void accept(String v1) {
                        ((Button) v).setText(String.format("当前图片：%s", v1));
                        try {
                            image = FileTool.readBytes(v1);
                            if (image != null) {
                                imageView.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                selectImage();
                break;
            case R.id.add_elementButton_save:
                if (addMode) {
                    element._name = et_name.getString();
                }
                element._brand = et_brand.getString();
                element._describe = et_describe.getString();
                element._package = et_package.getString();
                element._slot_id = et_slot_id.getString();
                element._shop_name = et_shop_name.getString();
                element._id_in_shop = et_id_in_shop.getString();
                element._rest = et_count.getInt();
                element._print = et_print.getString();
                element._picture = image;
                AppStack.push(element);
                setResult(RESULT_OK);
                finish();
                break;
        }
    }

    private void generalAutoFix() throws IOException {
        showToast("正在自动填充");
        final LcscElement lce = LcscApi.searchLcscCid(et_id_in_shop.getString());
        if (lce == null) {
            showToast("无搜索结果");
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                et_name.setText(lce.name);
                StringBuilder builder = new StringBuilder();
                for (String s : lce.pMap.keySet()) {
                    builder.append(s).append(" ").append(lce.pMap.get(s)).append(", ");
                }
                builder.setLength(builder.length() - 1);
                et_describe.setText(builder.toString());
                et_package.setText(lce.pack);
                et_brand.setText(lce.brand);
                et_id_in_shop.setText(lce.cid);
                ThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        final byte[] bmpArray = MNetwork.httpGetRaw(lce.image);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    image = bmpArray;
                                    if (image != null) {
                                        imageView.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                scrollView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        scrollView.fullScroll(View.FOCUS_DOWN);
                                    }
                                }, 500);
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_CODE_SELECT_FILE:
                    if (data.getData() == null) {
                        break;
                    }
                    callBack.accept(AndroidContent.absolutePathFromUri(this, data.getData()));
                    break;
                case Constant.REQUEST_CODE_SCAN_BARCODE:
                    callBack.accept(data.getStringExtra("result"));
                    break;
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            showToast("取消操作");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
