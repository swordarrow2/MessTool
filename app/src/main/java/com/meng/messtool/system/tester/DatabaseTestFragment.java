package com.meng.messtool.system.tester;

import android.os.*;
import android.view.*;
import android.widget.*;
import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.app.*;
import com.meng.tools.update.*;

public class DatabaseTestFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.text_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.aboutTextView);
        ThreadPool.execute(new Runnable(){

                @Override
                public void run() {
                    final UpdateChecker uc = new UpdateChecker((BaseActivity)getActivity());
                    final UpdateNotes updateNotes = uc.getUpdateNotes();                          
                    getActivity().runOnUiThread(new Runnable(){

                            @Override
                            public void run() {
                                   textView.setText(updateNotes.toString());
                            }
                        });                                                      
                }
            });
        //  DataBaseHelperOld.init(getActivity());
        //  DataBaseHelperOld.insertData(String.valueOf(1234567));
        //textView.setText(DataBaseHelperOld.searchFailedPic().toString());
//        final PixivDataBase pdb = DataBaseHelper.getInstance(PixivDataBase.class);

//        pdb.init(getActivity());
//        pdb.addFailed("11dads");
//        String text = ElementManagerFragment.LcPojo.prepareLcJson("{pbn:PICK240523100003,on:SO2405230848,pc:C7420349,pm:HL3401A,qty:50,mc:null,cc:1,pdi:114931470,hp:0,wc:JS}");
//        textView.setText(GSON.fromJson(text, ElementManagerFragment.LcPojo.class).toString());
//        final ElectronicDataBase edb = DataBaseHelper.getInstance(ElectronicDataBase.class);
        //textView.setText(edb.getElementLastUse(1).toString());
//        edb.addELement(new ElectronicDataBase.Element(
//                0,//_id integer primary key autoincrement
//                "_name",//_name varchar(255)
//                "_type",//_type varchar(255)
//                "_brand_cn",//_brand_cn varchar(255)
//                "_brand_en",//_brand_en varchar(255)
//                "_describe",//_describe varchar(512)
//                "_package",//_package varchar(255)
//                0,//_slot_id integer
//                "_shop_name",//_shop_name varchar(255)
//                "_id_in_shop",//_id_in_shop varchar(255)
//                "_sn1",//_sn1 varchar(255)
//                "_sn2",//_sn2 varchar(255)
//                10,// count integer
//                new byte[0]//_picture_file mediumblob
//        ));

        textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                pdb.addFailed(String.valueOf(System.currentTimeMillis() / 1000));
//                edb.addELement(new ElectronicDataBase.Element(
//                        0,//_id integer primary key autoincrement
//                        "_name1",//_name varchar(255)
//                        "_type1",//_type varchar(255)
//                        "_brand_cn1",//_brand_cn varchar(255)
//                        "_brand_en1",//_brand_en varchar(255)
//                        "_describe1",//_describe varchar(512)
//                        "_package1",//_package varchar(255)
//                        1,//_slot_id integer
//                        "_shop_name1",//_shop_name varchar(255)
//                        "_id_in_shop1",//_id_in_shop varchar(255)
//                        "_sn11",//_sn1 varchar(255)
//                        "_sn21",//_sn2 varchar(255)
//                        20,// count integer
//                        new byte[0]//_picture_file mediumblob
//                ));
//                edb.addUse(new ElectronicDataBase.Use(
//                        0,//_id integer primary key autoincrement
//                        1,//_element_id integer
//                        5,//_count integer
//                        10,//_rest integer
//                        System.currentTimeMillis()//_time long
//                ));
                    //ArrayList<String> res = pdb.getAllFailed();
//                ArrayList<ElectronicDataBase.Element> res = edb.getAllELement();
//                textView.setText(res.toString());
                }
            });
    }

    @Override
    public String getVersionName() {
        return "V0.0.0";
    }

    @Override
    public String getTitle() {
        return "测试";
    }
}
