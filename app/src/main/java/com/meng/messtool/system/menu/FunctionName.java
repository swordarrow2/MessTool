package com.meng.messtool.system.menu;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;

import com.meng.drawer.*;
import com.meng.messtool.*;
import com.meng.messtool.modules.chat.editor.*;
import com.meng.messtool.modules.chat.simulator.*;
import com.meng.messtool.modules.electronic.*;
import com.meng.messtool.modules.electronic.calculate.*;
import com.meng.messtool.modules.electronic.elementbox.*;
import com.meng.messtool.modules.electronic.usbserial.*;
import com.meng.messtool.modules.electronic.usbserial2.*;
import com.meng.messtool.modules.fpvtool.*;
import com.meng.messtool.modules.fpvtool.gui.*;
import com.meng.messtool.modules.media.*;
import com.meng.messtool.modules.media.barcode.*;
import com.meng.messtool.modules.media.gif.*;
import com.meng.messtool.modules.media.pixiv.*;
import com.meng.messtool.modules.media.pixiv_2.*;
import com.meng.messtool.modules.media.saucenao.*;
import com.meng.messtool.modules.media.wallpaper.*;
import com.meng.messtool.modules.rpgdecry.*;
import com.meng.messtool.system.*;
import com.meng.messtool.system.base.*;
import com.meng.messtool.system.tester.*;

public enum FunctionName {

    /*
     *@author 清梦
     *@date 2024-06-26 09:48:59
     */
    FUNCTION_TEST_DATABASE("db test", FunctionGroup.GROUP_DEVELOPING, DatabaseTestFragment.class),
//    FUNCTION_LIST_MEDICINE("查看药品", FunctionGroup.GROUP_DEVELOPING, ShowAllMedicineFragment.class),

//    FUNCTION_FPV_MSPV1("msp v1",FunctionGroup.GROUP_FPV,MspV1TestFragment.class),
//    FUNCTION_FPV_MSPV2("msp v2",FunctionGroup.GROUP_FPV,MspV2TestFragment.class),
//    FUNCTION_FPV_MSP_SENSOR("msp sensor",FunctionGroup.GROUP_FPV,MspV2TestFragment.class),

    /********************MEDIA*********************/
    FUNCTION_LIBGDX_TEST("libgdx", FunctionGroup.GROUP_DEVELOPING, new Runnable() {
        @Override
        public void run() {
            ApplicationHolder.getActivity().startActivity(new Intent(ApplicationHolder.getActivity(), LibgdxTestActivity.class));
        }
    }),
    FUNCTION_PICTURE_BARCODE("条码", FunctionGroup.GROUP_MEDIA, new Runnable() {

        @Override
        public void run() {
            ListView lv = new ListView(ApplicationHolder.getActivity());
            final AlertDialog ad = new AlertDialog.Builder(ApplicationHolder.getActivity()).setTitle("选择操作").setView(lv).show();
            lv.setAdapter(new ArrayAdapter<>(ApplicationHolder.getActivity(), android.R.layout.simple_list_item_1, ApplicationHolder.getActivity().getResources().getStringArray(R.array.create_type)));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                    ad.dismiss();
                    switch (p3) {
                        case 0:
                            MFragmentManager.getInstance().showFragment(BarcodeNormal.class);
                            break;
                        case 1:
                            MFragmentManager.getInstance().showFragment(BarcodeAwesome.class);
                            break;
                        case 2:
                            MFragmentManager.getInstance().showFragment(BarcodeAwesomeArb.class);
                            break;
                        case 3:
                            MFragmentManager.getInstance().showFragment(BarcodeAwesomeGif.class);
                            break;
                        case 4:
                            MFragmentManager.getInstance().showFragment(BarcodeAwesomeArbGif.class);
                            break;
                        case 5:
                            MFragmentManager.getInstance().showFragment(BarcodeReaderCamera.class);
                            break;
                        case 6:
                            MFragmentManager.getInstance().showFragment(BarcodeReaderGallery.class);
                            break;
                    }
                }
            });

        }
    }),
    FUNCTION_PICTURE_CRYPT("加密", FunctionGroup.GROUP_MEDIA, PictureCrypt.class),
    FUNCTION_PICTURE_GRAY("灰度图", FunctionGroup.GROUP_MEDIA, GrayImage.class),
    FUNCTION_PICTURE_ENCODE_GIF("合成GIF", FunctionGroup.GROUP_MEDIA, GIFCreator.class),
    FUNCTION_PICTURE_PIXIV_DOWNLOAD_V2("PIXIV下载V2", FunctionGroup.GROUP_MEDIA, PixivDownloadMainV2.class),
    FUNCTION_PICTURE_SAUCENAO("SauceNAO搜图", FunctionGroup.GROUP_MEDIA, SauceNaoMain.class),

    FUNCTION_VIDEO_FORMAT_CONVERT("视频格式转换", FunctionGroup.GROUP_DEVELOPING, FfmpegFragment.class),
    FUNCTION_VIDEO_RTMP_PUSH("RTMP推流", FunctionGroup.GROUP_MEDIA, RtmpFragment.class),
    FUNCTION_VIDEO_WALLPAPER("视频动态壁纸", FunctionGroup.GROUP_MEDIA, WallpaperMain.class),

    FUNCTION_AUDIO_ANDROID_TTS("文本语音合成", FunctionGroup.GROUP_MEDIA, TtsFragment.class),


    /********************FPV*********************/

    FUNCTION_FC_CONFIG("fc config", FunctionGroup.GROUP_DEVELOPING, FcConfig.class),
    FUNCTION_PID_SIMULATOR("pid simulator", FunctionGroup.GROUP_DEVELOPING, PIDSimulator.class),
    FUNCTION_FPV_CONFIG("飞控配置器", R.drawable.ic_quadcopter, FunctionGroup.GROUP_DEVELOPING, FpvConfigMainTabFragment.class),


    /********************ELECTRONIC*********************/

    FUNCTION_ELEMENT_BOX_ARRAY("元件盒", FunctionGroup.GROUP_ELECTRONIC, ElementManagerFragment.class),
    FUNCTION_ELEMENT_STM32_CHOOSE("STM32选型", FunctionGroup.GROUP_DEVELOPING, Stm32chooseHelper.class),

    FUNCTION_ELECTRONIC_SEARCH_SEMIEE("搜索半导小芯", FunctionGroup.GROUP_DEVELOPING, SearchSemieeFragment.class),
    FUNCTION_ELECTRONIC_BOOST_PART_CHOOSE("boost元件选型", FunctionGroup.GROUP_ELECTRONIC, DcdcBoostCalculateFragment.class),
    FUNCTION_ELECTRONIC_BUCK_PART_CHOOSE("buck元件选型", FunctionGroup.GROUP_ELECTRONIC, DcdcBuckCalculateFragment.class),
    FUNCTION_ELECTRONIC_FARAD_TEST("法拉电容估算", FunctionGroup.GROUP_ELECTRONIC, FaradCapacitanceCalculate.class),
    FUNCTION_ELECTRONIC_SERIAL_PORT2("串行端口2", FunctionGroup.GROUP_DEVELOPING, DevicesFragment.class),

    /********************TOY*********************/

    FUNCTION_TOY_CHAT_SIMULATOR("聊天模拟器", FunctionGroup.GROUP_TOY, ChatSimulator.class),
    FUNCTION_TOY_CHAT_SCRIPT_EDITOR("聊天脚本编辑器", FunctionGroup.GROUP_TOY, ChatEditor.class),
    FUNCTION_TOY_RPG_CRYPT("RPG文件解密", FunctionGroup.GROUP_DEVELOPING, RpgCrypter.class),

    /********************GROUP_LONG_TIME_NO_USE*********************/

    FUNCTION_PICTURE_PIXIV_DOWNLOAD("PIXIV下载", FunctionGroup.GROUP_LONG_TIME_NO_USE, PixivDownloadMain.class),

    /********************DEPRCATED*********************/

    FUNCTION_ELECTRONIC_SERIAL_PORT("串行端口", FunctionGroup.GROUP_DEPRECATED, UsbSerialFragment.class),
    FUNCTION_AUDIO_VITS_TTS("VITS语音合成", FunctionGroup.GROUP_DEPRECATED, VitsConnectFragment.class),

    /********************SYSTEM*********************/

    FUNCTION_SYSTEM_ABOUT("关于", FunctionGroup.GROUP_SYSTEM, About.class),
    FUNCTION_SYSTEM_SETTINGS("设置", FunctionGroup.GROUP_SYSTEM, new Runnable() {

        @Override
        public void run() {
            MFragmentManager.getInstance().showSettingFragment();
        }
    }),
    FUNCTION_SYSTEM_BACKGROUND_TASK("后台任务", FunctionGroup.GROUP_SYSTEM, new Runnable() {

        @Override
        public void run() {
            ((MainActivity) ApplicationHolder.getActivity()).openRightDrawer();
        }
    }),
    FUNCTION_SYSTEM_EXIT("退出", FunctionGroup.GROUP_SYSTEM, new Runnable() {

        @Override
        public void run() {
            ApplicationHolder.getActivity().finish();
        }
    });

    public static final String TAG = "FunctionName";

    private String name = null;
    private FunctionGroup group = FunctionGroup.GROUP_DEFAULT;
    private Runnable runnable = null;
    private Class<? extends BaseFragment> clazz = null;
    private int iconId = -1;

    FunctionName(String name, FunctionGroup group, Runnable runnable) {
        this.name = name;
        this.group = group;
        this.runnable = runnable;
    }

    FunctionName(String name, FunctionGroup group, Class<? extends BaseFragment> clazz) {
        this.name = name;
        this.group = group;
        this.clazz = clazz;
    }

    FunctionName(String name, int iconResourceId, FunctionGroup group, Class<? extends BaseFragment> clazz) {
        this.name = name;
        this.group = group;
        this.clazz = clazz;
        iconId = iconResourceId;
    }

    public int getIconId() {
        return iconId;
    }

    public String getName() {
        return name;
    }

    public FunctionGroup getGroup() {
        return group;
    }

    public void doAction() {
        if (clazz != null) {
            MFragmentManager.getInstance().showFragment(clazz);
        } else if (runnable != null) {
            runnable.run();
        } else {
            throw new IllegalStateException("class and runnable both are null");
        }
    }

}
