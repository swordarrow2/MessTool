package com.meng.api.lcsc;

import java.util.*;

public class LcscElement {
    public String name;
    public String pid;
    public String cid;
    public String pack;
    public String brand;
    public String image;
    public HashMap<String, String> pMap;

    public LcscElement(String name, String pid, String cid, String pack, String brand, String image, HashMap<String, String> pMap) {
        this.name = name;
        this.pid = pid;
        this.cid = cid;
        this.pack = pack;
        this.brand = brand;
        this.image = image;
        this.pMap = pMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LcscElement{");
        sb.append("name='").append(name).append('\'');
        sb.append(", pid='").append(pid).append('\'');
        sb.append(", cid='").append(cid).append('\'');
        sb.append(", pack='").append(pack).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", pMap=").append(pMap);
        sb.append('}');
        return sb.toString();
    }
}
