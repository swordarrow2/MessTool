package com.meng.messtool;

//public class UpdateInfo {
//
//    /*
//     *@author 清梦
//     *@date 2024-08-24 12:26:52
//     */
//    public static final String TAG = "UpdateInfo"; 
//
//    public boolean error = false;
//    private int[] newVersion;
//    private int[] nowVersion;
//    private String newVersionLink;
//
//    public boolean newFunction = false;
//    public boolean optimize = false;
//    public boolean bugFix = false;
//
//    public MainActivity activity;
//    public UpdateInfo(MainActivity activity) {
//        this.activity = activity;
//        try {
//            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
//            String[] nowVersionStr = packageInfo.versionName.split("\\.");
//            nowVersion = new int[]{Integer.parseInt(nowVersionStr[0]), Integer.parseInt(nowVersionStr[1]), Integer.parseInt(nowVersionStr[2])};
//        } catch (PackageManager.NameNotFoundException e) {
//            //   LogTool.e("检查更新出错");
//            e.printStackTrace();
//            error = true;
//        }
//    }
//
//    public void checkUpdate() {
//        try {
//            if (error) {
//                activity.showToast("检查更新出错");
//                return;
//            }
//            Connection connection = Jsoup.connect("https://github.com/swordarrow2/MessTool/releases/latest");
//            connection.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
//            connection.ignoreContentType(true).method(Connection.Method.GET).followRedirects(false);
//            Connection.Response response = connection.execute();
//            Map<String, String> head = response.headers();
//            setNewVersionLink(head.get("Location"));
//        } catch (Exception e) {
//            activity.showToast("检查更新出错");
//            e.printStackTrace();
//            return;
//        }
//        if (!SharedPreferenceHelper.getVersion().equals(getVersionName())) {
//            activity.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        if (newFunction || optimize || bugFix) {
//                            new AlertDialog.Builder(activity)
//                                .setTitle("发现新版本")
//                                .setMessage(getUpdateNote())
//                                .setPositiveButton("现在更新", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface p1, int p2) {
//                                        Intent intent = new Intent();
//                                        intent.setAction("android.intent.action.VIEW");
//                                        Uri contentUrl = Uri.parse(getNewVersionLink());
//                                        intent.setData(contentUrl);
//                                        activity.startActivity(intent);
//                                    }
//                                }).setNeutralButton("下次提醒我", null)
//                                .setNegativeButton("忽略本次更新", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        SharedPreferenceHelper.setVersion(getVersionName());
//                                    }
//                                }).show();
//                        }
//                    }
//                });
//        }
//    }
//
//    public boolean setNewVersionLink(String newVersionLink) {
//        try {
//            this.newVersionLink = newVersionLink;
//            String[] newVersionStr = newVersionLink.substring(newVersionLink.lastIndexOf("/") + 1).split("\\.");
//            newVersion = new int[]{Integer.parseInt(newVersionStr[0]), Integer.parseInt(newVersionStr[1]), Integer.parseInt(newVersionStr[2])};
//            newFunction = newVersion[0] > nowVersion[0];
//            optimize = newVersion[1] > nowVersion[1];
//            bugFix = newVersion[2] > nowVersion[2];
//            return true;
//        } catch (Exception e) {
//            //    LogTool.e(e.toString());
//            return false;
//        }
//    }
//
//    public String getNewVersionLink() {
//        return newVersionLink;
//    }
//
//    public String getVersionName() {
//        return newVersionLink.substring(newVersionLink.lastIndexOf("/") + 1);
//    }
//
//    public String getUpdateNote() {
//        StringBuilder stringBuilder = new StringBuilder("本次更新的主要目的是\n");
//        if (newFunction) stringBuilder.append("添加新功能\n");
//        if (optimize) stringBuilder.append("性能优化\n");
//        if (bugFix) stringBuilder.append("修理bug\n");
//        stringBuilder.append("现在要更新吗");
//        return stringBuilder.toString();
//    }
//
//}
