package com.meng.messtool.modules.fpvtool;

import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.system.base.*;
import com.meng.tools.*;

public class FcConfig extends BaseFragment {

    /*
     *@author 清梦
     *@date 2024-07-15 23:34:36
     */
    public static final String TAG = "FcConfig";

    @Override
    public String getTitle() {
        return "BetaFlight配置生成器";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        return tv;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FcConfigHelper.Hardware h = new FcConfigHelper.Hardware();

        h.serials.put("USB-VCP", new FcConfigHelper.Serial("USB-VCP", "Receiver"));
        h.serials.put("1", new FcConfigHelper.Serial("1", "GPS"));
        h.serials.put("2", new FcConfigHelper.Serial("2", "VTX"));
        h.serials.put("3", new FcConfigHelper.Serial("3", "MSP"));


        h.localizers.put("BZ-251", new FcConfigHelper.Localizer("BZ-251", "115200", "UBLOX7", new String[]{"GPS", "GALILEO", "BDS", "GLONASS"}));
        h.localizers.put("BN-180", new FcConfigHelper.Localizer("BN-180", "9600", "NMEA-183", new String[]{"GPS", "GALILEO", "BDS", "GLONASS", "SBAS", "QZSS"}));
        h.receivers.put("EP1 2400 RX", new FcConfigHelper.Receiver("EP1 2400 RX", "CRSF"));
        h.receivers.put("EP2 2400 RX", new FcConfigHelper.Receiver("EP2 2400 RX", "CRSF"));
        String json = GSON.toJson(h);
        showToast(FileTool.saveToFile(FileTool.getAppFile("fc/", "fc_test.json"), json));
    }
}
