package com.meng.tools.app.update;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.media.*;
import android.net.*;

import com.meng.messtool.system.base.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.util.*;

public class UpdateChecker {

    /*
     *@author 清梦
     *@date 2024-08-27 22:28:16
     */
    public static final String TAG = "UpdateChecker";

    private BaseActivity activity;
    private String currentVersion;

    private String packageName;

    public UpdateChecker(BaseActivity activity) {
        this.activity = activity;
    }

    private String getLastVersion() {
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            currentVersion = packageInfo.versionName;
            packageName = packageInfo.packageName;
            UpdateNotes updateNotes = getUpdateNotes();
            if (updateNotes == null) {
                return null;
            }
            UpdateNotes.Node lastNode = updateNotes.getLastNote();
            return lastNode.version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            activity.showToast("检查更新出错:获取版本信息失败");
            return null;
        }
    }

    private String getLastVersionLink() {
        String link = "https://github.com/swordarrow2/MessTool/releases/latest";
        try {
            String redirectLink = MNetwork.getRealUrl(link);
            String tag = redirectLink.substring(redirectLink.lastIndexOf("/") + 1);
            String exContent = MNetwork.httpGet("https://github.com/swordarrow2/MessTool/releases/expanded_assets/" + tag);
            Document doc = Jsoup.parse(exContent);
            Elements table = doc.getElementsByTag("a");
            String href = table.first().attr("href");
            return "https://www.github.com" + href;
        } catch (Exception e) {
            e.printStackTrace();
            return link;
        }
    }

    public void checkUpdate() {
        MediaCodecList list = new MediaCodecList(0);
        MediaCodec mAudioEncoder;
        String AUDIO_MIME = "audio/mp4a-latm";
        StringBuilder builder = new StringBuilder();
        for (MediaCodecInfo c : list.getCodecInfos()) {
            builder.append(c.getName()).append(Arrays.asList(c.getSupportedTypes()));
        }
        activity.showToast(builder.toString());
//        try {
//            mAudioEncoder = MediaCodec.createByCodecName()
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//        MediaCodecInfo codecInfo = mAudioEncoder.getCodecInfo();
//        activity.showToast(Arrays.asList(codecInfo.getSupportedTypes()).toString());
        final String lastVersion = getLastVersion();
        if (lastVersion == null) {
            return;
        }
        if (SharedPreferenceHelper.getVersion().equals(lastVersion)) {
            return;
        }
        if (currentVersion.equals(lastVersion)) {
            return;
        }
        final String lastVersionDownloadLink = getLastVersionLink();
        final UpdateNotes notes = getUpdateNotes();
        if (notes == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new AlertDialog.Builder(activity)
                        .setTitle("发现新版本")
                        .setMessage(notes.toString())
                        .setPositiveButton("现在更新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p1, int p2) {
                                new AlertDialog.Builder(activity)
                                        .setTitle("软件更新")
                                        .setMessage("跳转至软件下载:" + lastVersionDownloadLink)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dia, int which) {
                                                Intent intent = new Intent();
                                                intent.setAction("android.intent.action.VIEW");

                                                Uri contentUrl = Uri.parse(lastVersionDownloadLink);
                                                intent.setData(contentUrl);
                                                activity.startActivity(intent);
                                            }
                                        })
                                        .setNegativeButton("取消", null)
                                        .create().show();
                            }
                        }).setNeutralButton("下次提醒我", null)
                        .setNegativeButton("忽略本次更新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferenceHelper.setVersion(lastVersion);
                            }
                        }).show();
            }
        });
    }

    public UpdateNotes getUpdateNotes() {
        UpdateNotes fromJson;
        try {
            fromJson = GSON.fromJson(MNetwork.httpGet("https://swordarrow2.github.io/app_update/" + packageName + ".json"), UpdateNotes.class);
        } catch (Exception e) {
            try {
                fromJson = GSON.fromJson(MNetwork.httpGet("https://swordarrow2.github.io/example.json"), UpdateNotes.class);
            } catch (Exception e2) {
                return null;
            }
        }
//        UpdateNotes fromJson=new UpdateNotes();
//        fromJson.noteList = new ArrayList<UpdateNotes.Node>();
//        fromJson.noteList.add(new UpdateNotes.Node("0.0.2", "忘了"));
//        fromJson.noteList.add(new UpdateNotes.Node("1.0.2", "记录更新历史，添加了“关于”界面"));
//        fromJson.noteList.add(new UpdateNotes.Node("1.0.3", "第一次启动需要手动进入系统设置打开权限的bug"));
//        fromJson.noteList.add(new UpdateNotes.Node("2.0.0", "添加“待验证”分组，用来放置各种原因导致长时间未验证是否可用的功能","添加“玩具”分组，用来放置单纯为了好玩的功能","添加“聊天模拟器”功能"));
//        fromJson.noteList.add(new UpdateNotes.Node("2.1.0", "优化“聊天模拟器”消息解析功能"));
//        fromJson.noteList.add(new UpdateNotes.Node("2.2.0", "聊天模拟器”新增几个脚本动作","“聊天模拟器”完善例子"));
//        fromJson.noteList.add(new UpdateNotes.Node("2.3.0", "“聊天模拟器”的脚本编辑器"));
//        fromJson.noteList.add(new UpdateNotes.Node("2.4.0", "更新检测功能优化"));
//        FileTool.saveToFile(FileTool.getAppFile("", "example.json"), GSON.toJson(fromJson));
//

        int index = 0;
        for (UpdateNotes.Node node : fromJson.noteList) {
            index++;
            if (node.version.equals(currentVersion)) {
                break;
            }
        }
        if (index == fromJson.noteList.size()) {
            index = 0;
        }
        fromJson.noteList = new LinkedList<>(fromJson.noteList.subList(index, fromJson.noteList.size()));
        return fromJson;
    }
}
