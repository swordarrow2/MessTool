package com.meng.messtool.modules.electronic.elementbox;

class LcPojo {

//   {"pbn":"PICK240523100003",
//   "on":"SO2405230848", "pc":"C7420349", "pm":"HL3401A",
//   "qty":50, "mc":null, "cc":1, "pdi":114931470, "hp":0, "wc":"JS"}

    public String cc;
    public String hp;
    public String mc;
    public String on;
    public String pbn;
    public String pc;
    public String pdi;
    public String pm;
    public String qty;
    public String wc;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LcPojo{");
        sb.append("cc='").append(cc).append('\'');
        sb.append(", hp='").append(hp).append('\'');
        sb.append(", mc='").append(mc).append('\'');
        sb.append(", on='").append(on).append('\'');
        sb.append(", pbn='").append(pbn).append('\'');
        sb.append(", pc='").append(pc).append('\'');
        sb.append(", pdi='").append(pdi).append('\'');
        sb.append(", pm='").append(pm).append('\'');
        sb.append(", qty='").append(qty).append('\'');
        sb.append(", wc='").append(wc).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static String prepareLcJson(String lcJson) {
        return lcJson.replace("{", "{\"")
                .replace("}", "\"}")
                .replace(":", "\":\"")
                .replace(",", "\",\"");
    }
}