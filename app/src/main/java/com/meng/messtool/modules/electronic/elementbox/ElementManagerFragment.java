package com.meng.messtool.modules.electronic.elementbox;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.tools.*;
import com.meng.tools.MaterialDesign.*;

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
                    Element element = (Element) AppStack.pop();
                    ElectronicDatabase dataBase = ElectronicDatabase.getInstance();
                    if (addMode) {
                        boolean su = dataBase.addELement(element);
                        if (!su) {
                            element._rest += dataBase.getElement(element._name)._rest;
                            dataBase.updateELement(element);
                            MainActivity.instance.showToast("仅更新数量");
                        }
                        dataBase.addUse(new Use(0,
                                dataBase.getElement(element._name)._id,
                                element._rest,
                                "lcsc",
                                System.currentTimeMillis()));
                        MainActivity.instance.showToast("添加成功");
                    } else {
                        dataBase.updateELement(element);
                        MainActivity.instance.showToast("更新成功");
                    }
                    eLementAdapter.cleanThumb(element);
                    eLementAdapter.notifyDataSetChanged();
                    break;
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            MainActivity.instance.showToast("取消操作");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
