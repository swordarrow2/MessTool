package com.meng.messtool.modules.media.pixiv_2.pojo;

import java.util.*;

public class PojoStaticPainting {

    public String error = "true";
    public String message = "";
    public ArrayList<Body> body = new ArrayList<Body>();

    public class Body {
        public int width = 0;
        public int height = 0;
        public Urls urls = new Urls();
    }

    public class Urls {
        public String small = "";
        public String regular = "";
        public String original = "";
    }
}
