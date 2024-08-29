package com.meng.messtool.modules.electronic;

import com.meng.messtool.system.base.*;
import java.util.*;

public class Stm32choose extends BaseFragment {

    /*
     *@author 清梦
     *@date 2024-08-09 07:48:58
     */
    public static final String TAG = "stm32choose";

    @Override
    public String getTitle() {
        return "STM32型号对比器";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }


    public String analyze(String model) {
        if (!model.startsWith("STM32")) {
            return "NOT STM32";
        }
        StringBuilder builder = new StringBuilder();
        String type_s = String.valueOf(model.charAt(5));
        if (type.containsKey(type_s)) {
            builder.append(type.get(type_s));
        } else {
            builder.append(type_s).append(" not support.");
        }
        builder.append("\n");
        String special_s = model.substring(6, 9);
        if (special.containsKey(special_s)) {
            builder.append(special.get(special_s));
        } else {
            builder.append(special_s).append(" not support.");
        }
        builder.append("\n");
        String footCount_s = String.valueOf(model.charAt(9));
        if (footCount.containsKey(footCount_s)) {
            builder.append(footCount.get(footCount_s));
        } else {
            builder.append(footCount_s).append(" not support.");
        }
        builder.append("\n");
        String flash_s = String.valueOf(model.charAt(10));
        if (flashCapacity.containsKey(flash_s)) {
            builder.append(flashCapacity.get(flash_s));
        } else {
            builder.append(flash_s).append(" not support.");
        }
        builder.append("\n");
        String pack_s = String.valueOf(model.charAt(11));
        if (pack.containsKey(pack_s)) {
            builder.append(pack.get(pack_s));
        } else {
            builder.append(pack_s).append(" not support.");
        }
        builder.append("\n");
        String temperature_s = String.valueOf(model.charAt(12));
        if (temperature.containsKey(temperature_s)) {
            builder.append(temperature.get(temperature_s));
        } else {
            builder.append(temperature_s).append(" not support.");
        }
        builder.append("\n");

        return builder.toString();
    }


    private HashMap<String, String> type = new HashMap<String, String>() {
        {
            put("A", "汽车级");
            put("F", "基础型");
            put("L", "超低功耗");
            put("S", "标准型");
            put("T", "触摸感应");
            put("W", "无线产品");
            put("xP", "fastrom");
        }
    };

    private HashMap<String, String> special = new HashMap<String, String>() {
        {
            put("051", "");
            put("103", "");
            put("303", "");
            put("405", "");
            put("407", "");
            put("152", "");
            put("750", "");
        }
    };

    private HashMap<String, String> footCount = new HashMap<String, String>() {
        {
            put("D", "14");
            put("Y", "20");
            put("F", "20");
            put("E", "24/25");
            put("G", "28");
            put("K", "32");
            put("T", "36");
            put("H", "40");
            put("S", "44");
            put("C", "48/49");
            put("U", "63");
            put("R", "64/66");
            put("J", "72");
            put("M", "80");
            put("O", "90");
            put("V", "100");
            put("Q", "132");
            put("Z", "144");
            put("A", "169");
            put("I", "176/201");
            put("B", "208");
            put("N", "216");
            put("X", "256");

//            put("8", "48");
//            put("9", "64");
//            put("A", "80");
        }
    };
    private HashMap<String, String> flashCapacity = new HashMap<String, String>() {
        {
            put("0", "1KiB");
            put("1", "2KiB");
            put("2", "4KiB");
            put("3", "8KiB");
            put("4", "16KiB");
            put("5", "24KiB");
            put("6", "32KiB");
            put("7", "48KiB");
            put("8", "64KiB");
            put("9", "72KiB");
            put("A", "96KiB");
            put("B", "128KiB");
            put("Z", "192KiB");
            put("C", "256KiB");
            put("D", "384KiB");
            put("E", "512KiB");
            put("F", "768KiB");
            put("G", "1024KiB");
            put("H", "1536KiB");
            put("I", "2048KiB");
        }
    };

    private HashMap<String, String> pack = new HashMap<String, String>() {
        {
            put("B", "plastic DIP");
            put("D", "ceramic DIP");
            put("G", "ceramic QFP");
            put("H", "LFBGA/TFBGA");
            put("I", "UFBGA Pitch 0.5");
            put("J", "UFBGA Pitch 0.8");
            put("K", "UFBGA Pitch 0.65");
            put("M", "plastic SO");
            put("P", "TTSOP");
            put("Q", "plastic QFP");
            put("T", "LQFP");
            put("U", "UFQFPN");
            put("V", "VFQFPN");
            put("Y", "WLCSP");
        }
    };

    private HashMap<String, String> temperature = new HashMap<String, String>() {
        {
            put("6", "-40℃~85℃");
            put("A", "-40℃~85℃");
            put("7", "-40℃~105℃");
            put("B", "-40℃~105℃");
            put("3", "-40℃~125℃");
            put("C", "-40℃~125℃");
            put("D", "-40℃~150℃");
        }
    };
}
