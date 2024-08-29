package com.meng.api.lcsc;

import com.meng.tools.*;
import com.meng.tools.app.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class LcscApi {
    public static LcscElement searchLcscCid(String cid) throws IOException {
        List<LcscElement> tmpl = searchLcsc(cid);
        Iterator<LcscElement> iterator = tmpl.iterator();
        while (iterator.hasNext()) {
            LcscElement le = iterator.next();
            if (!le.cid.equalsIgnoreCase(cid)) {
                iterator.remove();
            }
        }
        if (tmpl.size() == 1) {
            return tmpl.get(0);
        } else {
            return null;
        }
    }

    public static List<LcscElement> searchLcsc(String keyword) throws IOException {
        List<LcscElement> list = new ArrayList<>();
        File file = FileTool.getAppFile(FunctionSavePath.cache, keyword, "html");
        if (!file.exists() || file.length() == 0) {
            String s = Jsoup.connect("https://so.szlcsc.com/global.html?k=" + keyword)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36")
                    .execute().body();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(s.getBytes(StandardCharsets.UTF_8));
            }
        }
        String html;
        try (FileInputStream fin = new FileInputStream(file)) {
            byte[] bs = new byte[(int) file.length()];
            fin.read(bs);
            html = new String(bs, StandardCharsets.UTF_8);
        }
        Document doc = Jsoup.parse(html);
        Elements tables = doc.getElementsByClass("inside inside-page tab-data no-one-hk list-items");
        System.out.println("tables.size():" + tables.size());
        for (Element table : tables) {

            ArrayList<String> tlist = new ArrayList<>();
            Element img = table.getElementsByTag("img").first();

            Elements describes = table.getElementsByClass("description");
            if (describes != null && describes.size() >= 1) {
                Element describe = describes.first();
                if (describe != null) {
                    Elements describSpans = describe.getElementsByTag("span");
                    if (describSpans.size() >= 2) {
                        Element element = describSpans.get(1);
                        String text = element.text();
                        tlist.add("描述:");
                        tlist.add(text);
                    }
                }
            }

            Elements uls = table.getElementsByClass("l02-zb");
            Element ul2 = uls.get(1);
            for (Element li : ul2.getElementsByClass("li-ellipsis")) {
                Elements spans = li.getElementsByTag("span");
                for (Element span : spans) {
                    tlist.add(span.text());
                }
            }

            Element ul3 = uls.get(2);
            for (Element li : ul3.getElementsByClass("li-ellipsis")) {
                Elements spans = li.getElementsByTag("span");
                if (spans.size() >= 1) {
                    Element span = spans.first();
                    tlist.add(span.text());
                }

                Elements as = li.getElementsByTag("a");
                if (as.size() > 0) {
                    tlist.add(as.first().text());
                }
            }
            HashMap<String, String> paramMap = new HashMap<>();
            for (int ii = 0; ii < tlist.size() - 2; ) {
                String key = tlist.get(ii++);
                String value = tlist.get(ii++);
                if ("编号:".equals(key)) {
                    break;
                }
                paramMap.put(key, value);
            }
            LcscElement element = new LcscElement(
                    table.attr("data-productModel"),
                    table.attr("pid"),
                    table.attr("data-productCode"),
                    table.attr("data-encapStandard"),
                    table.attr("data-brandName"),
                    img.attr("xpath"),
                    paramMap
            );
            list.add(element);
        }
        return list;
    }
}
