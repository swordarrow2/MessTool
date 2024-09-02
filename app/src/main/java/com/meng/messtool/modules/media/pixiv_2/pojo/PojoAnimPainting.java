package com.meng.messtool.modules.media.pixiv_2.pojo;

import java.util.*;

public class PojoAnimPainting {
    public String error = "true";
    public String message = "";
    public Body body = new Body();

    public class Body {

        public String src = "";
        public String originalSrc = "";
        public String mime_type = "";
        public ArrayList<Frames> frames = new ArrayList<Frames>();

        public class Frames {
            public String file = "";
            public String delay = "";
        }
    }
}
