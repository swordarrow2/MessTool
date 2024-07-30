package com.meng.messtool.modules.fpvtool;

import java.util.*;

public class FcConfigHelper {

    /*
     *@author 清梦
     *@date 2024-07-15 21:59:45
     */
    public static final String TAG = "FcConfigHelper";

    public static class Hardware {
        public HashMap<String, Serial> serials = new HashMap<>();
        public HashMap<String, Localizer> localizers = new HashMap<>();
        public HashMap<String, Receiver> receivers = new HashMap<>();

    }

    public static class Serial {
        public String num;
        public String hardware;
        public String baud;

        public Serial(String num, String hardware) {
            this.num = num;
            this.hardware = hardware;
        }
    }


    public static class Receiver {
        public String name;
        public String protocol;

        public Receiver(String name, String protocol) {
            this.name = name;
            this.protocol = protocol;
        }
    }


    public static class Localizer {
        public String model;
        public String baud;
        public String protocol;
        public String[] feature;

        public Localizer(String model, String baud, String protocol, String... feature) {
            this.model = model;
            this.baud = baud;
            this.protocol = protocol;
            this.feature = feature;
        }
    }

}
