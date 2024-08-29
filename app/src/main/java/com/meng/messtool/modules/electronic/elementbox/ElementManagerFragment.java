package com.meng.messtool.modules.electronic.elementbox;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;
import com.meng.messtool.customview.MaterialDesign.*;

/*
 *@author 清梦
 *@date 2024-07-28 20:08:30
 */
public class ElementManagerFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private FloatingButton floatingButton;
    private ListView listView;
    private ELementAdapter eLementAdapter;
    private boolean addMode = true;

    @Override
    public String getTitle() {
        return "元件盒";
    }

    @Override
    public String getVersionName() {
        return "V0.1.0";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ElectronicDatabase.getInstance().init(getActivity());
        return inflater.inflate(R.layout.function_electronic_main, null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingButton = (FloatingButton) view.findViewById(R.id.fab_select);
        listView = (ListView) view.findViewById(R.id.function_electronic_main_list);
        eLementAdapter = new ELementAdapter(getActivity());
        listView.setAdapter(eLementAdapter);
        floatingButton.show(true);
        floatingButton.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.function_electronic_main_list:
                addMode = false;
                AppStack.push(eLementAdapter.getItem(position));
                Intent intent = new Intent(getContext(), EditElementActivity.class);
                intent.putExtra("addMode", addMode);
                startActivityForResult(intent, Constant.REQUEST_CODE_EDIT_ELEMENT);
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.function_electronic_main_list:
                Element element = eLementAdapter.getItem(position);
                ScrollView scrollview = (ScrollView) getActivity().getLayoutInflater().inflate(R.layout.function_electronic_show_element, null);
                LinearLayout linearlayout = (LinearLayout) scrollview.getChildAt(0);
                for (int i = 0; i < linearlayout.getChildCount(); i++) {
                    linearlayout.getChildAt(i).setEnabled(false);
                }
                MDEditText et_name;
                MDEditText et_print;
                MDEditText et_brand;
                MDEditText et_describe;
                MDEditText et_package;
                MDEditText et_slot_id;
                MDEditText et_shop_name;
                MDEditText et_id_in_shop;
                MDEditText et_count;
                ImageView imageView;
                et_name = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_name);
                et_print = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_print);
                et_brand = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_brand);
                et_describe = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_describe);
                et_package = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_package);
                et_slot_id = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_slot_id);
                et_shop_name = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_shop_name);
                et_id_in_shop = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_id_in_shop);
                et_count = (MDEditText) scrollview.findViewById(R.id.add_elementEditText_count);
                imageView = (ImageView) scrollview.findViewById(R.id.add_element_thumbnail);
                et_name.setText(element._name);
                et_print.setText(element._print);
                et_brand.setText(element._brand);
                et_describe.setText(element._describe);
                et_package.setText(element._package);
                et_slot_id.setText(element._slot_id);
                et_shop_name.setText(element._shop_name);
                et_id_in_shop.setText(element._id_in_shop);
                et_count.setText(String.valueOf(element._rest));
                if (element._picture != null) {
                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(element._picture, 0, element._picture.length));
                }
                new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.ic_launcher).setView(scrollview)
                        .setTitle("详细内容").setPositiveButton("确定", null).show();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_select:
                addMode = true;
                Intent intent = new Intent(getContext(), EditElementActivity.class);
                intent.putExtra("addMode", addMode);
                startActivityForResult(intent, Constant.REQUEST_CODE_EDIT_ELEMENT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_CODE_EDIT_ELEMENT:
                    Element newElement = (Element) AppStack.pop();
                    ElectronicDatabase dataBase = ElectronicDatabase.getInstance();
                    if (addMode) {
                        boolean success = dataBase.addELement(newElement);
                        if (!success) {
                            Element originElement = dataBase.getElement(newElement._name);
                            newElement._id = originElement._id;
                            newElement._rest += originElement._rest;
                            dataBase.updateELementById(newElement);
                            dataBase.addUse(new Use(0,
                                    originElement._id,
                                    newElement._rest,
                                    "lcsc",
                                    System.currentTimeMillis()));
                            showToast("仅更新数量");
                        } else {
                            Element originElement = dataBase.getElement(newElement._name);
                            dataBase.addUse(new Use(0,
                                    originElement._id,
                                    newElement._rest,
                                    "lcsc",
                                    System.currentTimeMillis()));
                            showToast("添加成功");
                        }
                    } else {
                        Element originElement = dataBase.getElement(newElement._name);
                        newElement._id = originElement._id;
                        dataBase.updateELementById(newElement);
                        showToast("更新成功");
                    }
                    eLementAdapter.cleanThumb(newElement);
                    eLementAdapter.notifyDataSetChanged();
                    break;
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            showToast("取消操作");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
