package com.meng.messtool.modules.media.pixiv_2;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.google.gson.*;
import com.google.gson.internal.*;
import com.meng.messtool.*;
import com.meng.messtool.customview.MaterialDesign.*;
import com.meng.messtool.modules.media.pixiv_2.pojo.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import org.jsoup.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PixivDownloadMainV2 extends BaseFragment {

    public EditText editTextURL;
    public ExecutorService threadPool;
    private ListView downloadedList;
    private ListView likeList;
    private LinearLayout taskLinearLayout;
    private PojoLocalFavorite pojoLocalFavorite;
    private CheckBox checkBoxIsUID;
    private ImageView imageView;
    private String token = "";
    private FloatingMenu menuStar;
    private FloatingButton fabStartDownload;
    private FloatingButton fabAddMine;
    private FloatingButton fabAddPixiv;
    private ArrayAdapter likeAdapter;
    private PixivDatabase database;

    @Override
    public String getTitle() {
        return "pixiv下载器";
    }

    @Override
    public String getVersionName() {
        return "V1.0.0";
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PixivDatabase.getInstance().init(getActivity());
        database = PixivDatabase.getInstance();
        database.init(getActivity());
        return inflater.inflate(R.layout.picture_pixiv_download, container, false);
    }

    private void init(View view) {
        TabHost tabHost = (TabHost) view.findViewById(R.id.pixiv_download_main_tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("zero").setIndicator("主页").setContent(R.id.pixiv_download_main_browser));
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("正在下载").setContent(R.id.pixiv_download_main_downloading));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("已下载").setContent(R.id.pixiv_download_main_downloaded));
        tabHost.addTab(tabHost.newTabSpec("three").setIndicator("收藏").setContent(R.id.pixiv_download_main_like));
        initPage1(view);
        initPage2(view);
        initPage3(view);
        initPage4(view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabStartDownload.show(true);
            }
        }, 150);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                menuStar.showMenuButton(true);
            }
        }, 300);
        File preDownloadJson = new File(FileTool.getPreDownloadJsonPath());
        if (preDownloadJson.exists()) {
            try {
                pojoLocalFavorite = GSON.fromJson(FileTool.readString(preDownloadJson), PojoLocalFavorite.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            likeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pojoLocalFavorite.info);
            likeList.setAdapter(likeAdapter);
        }
        threadPool = Executors.newFixedThreadPool(Integer.parseInt(SharedPreferenceHelper.getThreads()));
        checkFailed();
    }

    private void initPage4(View view) {
        likeList = (ListView) view.findViewById(R.id.like_files_list);
        likeList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> p1, View p2, final int p3, long p4) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("开始下载？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                createDownloadTask(p1.getItemAtPosition(p3).toString());
                                showToast("正在读取信息");
                                fabStartDownload.setShowProgressBackground(true);
                                fabStartDownload.setIndeterminate(true);
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        likeList.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> p1, View p2, final int p3, long p4) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("确定删除吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p1, int p2) {
                                pojoLocalFavorite.info.remove(p3);
                                try {
                                    FileTool.savePreDownload(GSON.toJson(pojoLocalFavorite));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                likeList.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pojoLocalFavorite.info));
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });
    }

    private void initPage3(View view) {
        downloadedList = (ListView) view.findViewById(R.id.saved_files_list);
        String[] filesName = FileTool.getAppFile(FileSavePath.PIXIV_ZIP, "").list();
        Arrays.sort(filesName);
        downloadedList.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, filesName));
    }

    private void initPage2(View view) {
        taskLinearLayout = (LinearLayout) view.findViewById(R.id.pixiv_download_main_downloadlist_task);
    }

    private void initPage1(View view) {
        editTextURL = (EditText) view.findViewById(R.id.pixiv_download_main_edittext_url);
        checkBoxIsUID = (CheckBox) view.findViewById(R.id.pixiv_download_main_checkbox_user);
        imageView = (ImageView) view.findViewById(R.id.imageview);
        menuStar = (FloatingMenu) view.findViewById(R.id.menu_star);
        fabStartDownload = (FloatingButton) view.findViewById(R.id.fab_start_download);
        fabAddMine = (FloatingButton) view.findViewById(R.id.fab_add_mine);
        fabAddPixiv = (FloatingButton) view.findViewById(R.id.fab_add_pixiv);

        menuStar.setAnimated(true);
        menuStar.hideMenuButton(false);
        menuStar.setClosedOnTouchOutside(true);
        menuStar.setIconAnimated(false);

        fabAddMine.setOnClickListener(onClickListener);
        fabAddPixiv.setOnClickListener(onClickListener);
        fabStartDownload.setOnClickListener(onClickListener);
        fabStartDownload.hide(false);
    }

    private void checkFailed() {
        final ArrayList<String> failedURLs = database.getAllFailedId();
        if (failedURLs.size() > 0) {
            new android.app.AlertDialog.Builder(getActivity())
                    .setTitle("Boom")
                    .setMessage("发现了上次的出错任务")
                    .setPositiveButton("现在重新下载", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface p1, int p2) {
                            for (String url : failedURLs) {
                                createDownloadTask(url);
                                database.deleteFailedId(url);
                            }
                        }
                    }).setNeutralButton("下次提醒我", null)
                    .setNegativeButton("放弃这些任务", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (String url : failedURLs) {
                                database.deleteFailedId(url);
                            }
                        }
                    }).show();
        }
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_add_mine:
                    File jsonFile = new File(FileTool.getPreDownloadJsonPath());
                    if (jsonFile.exists()) {
                        try {
                            pojoLocalFavorite = GSON.fromJson(FileTool.readString(jsonFile), PojoLocalFavorite.class);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        pojoLocalFavorite = new PojoLocalFavorite();
                        pojoLocalFavorite.info = new ArrayList<>();
                    }
                    pojoLocalFavorite.info.add(editTextURL.getText().toString());
                    try {
                        FileTool.savePreDownload(GSON.toJson(pojoLocalFavorite));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    //  likeAdapter.notifyDataSetChanged();
                    //	  editTextURL.setText("");
                    showToast("添加成功");
                    likeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pojoLocalFavorite.info);
                    likeList.setAdapter(likeAdapter);
                    menuStar.close(true);
                    break;
                case R.id.fab_add_pixiv:
                    showToast("有待填坑");
                    /*  new Thread(new Runnable() {

					 @Override
					 public void run() {
					 addFa(getPixivId(editTextURL.getText().toString()));
					 }
					 }).start();*/
                    menuStar.close(true);
                    break;
                case R.id.fab_start_download:
                    startDownload();
                    break;
            }
        }
    };

    public void startDownload() {
        String text = editTextURL.getText().toString();
        //	  editTextURL.setText("");
        if (text.equals("")) {
            showToast("ID不能为空");
            return;
        }
        showToast("正在读取信息");
        fabStartDownload.setShowProgressBackground(true);
        fabStartDownload.setIndeterminate(true);
        if (checkBoxIsUID.isChecked()) {
            createDownloadAllPictureTask(text);
        } else {
            createDownloadTask(text);
        }
    }

//    TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//		  }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//		  }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            new Thread(new Runnable() {
//
//				  @Override
//				  public void run() {
//					  final Bitmap b = getThumb(getPixivId(editTextURL.getText().toString()));
//					  if (b == null) return;
//					  getActivity().runOnUiThread(new Runnable() {
//
//							@Override
//							public void run() {
//								imageView.setImageBitmap(b);
//							  }
//						  });
//					}
//				}).start();
//		  }
//	  };

    private void createDownloadTask(final String url) {
        database.addFailedId(url);
        new Thread(new Runnable() {

            @Override
            public void run() {
                final PojoPainting pojoPainting = getPicInfo(getPixivId(url));
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        fabStartDownload.hideProgress();
                    }
                });
                if (pojoPainting == null) {
                    showToast("未获取到有效的图片信息");
                    return;
                }
                getActivity().runOnUiThread(
                        new Runnable() {

                            @Override
                            public void run() {
                                if (pojoPainting.isAnimPicture) {
                                    if (pojoPainting.pojoAnimPainting.error.equals("true")) {
                                        showToast("动态图信息读取错误");
                                        return;
                                    }
                                    PixivProgressBar pixivProgressBar = new PixivProgressBar(getActivity());
                                    pixivProgressBar.bindingPixivDownload(downloadedList, pojoPainting,
                                            SharedPreferenceHelper.isDownloadBigGif() ?
                                                    pojoPainting.pojoAnimPainting.body.originalSrc :
                                                    pojoPainting.pojoAnimPainting.body.src);
                                    taskLinearLayout.addView(pixivProgressBar);
                                } else {
                                    if (!pojoPainting.staticPicJavaBean.error.equals("true")) {
                                        for (int i = 0; i < pojoPainting.staticPicJavaBean.body.size(); ++i) {
                                            PixivProgressBar pixivProgressBar = new PixivProgressBar(getActivity());
                                            pixivProgressBar.bindingPixivDownload(downloadedList, pojoPainting,
                                                    SharedPreferenceHelper.isDownloadBigPic() ?
                                                            pojoPainting.staticPicJavaBean.body.get(i).urls.original :
                                                            pojoPainting.staticPicJavaBean.body.get(i).urls.regular);
                                            taskLinearLayout.addView(pixivProgressBar);
                                        }
                                    } else {
                                        showToast("图片信息读取错误");
                                    }
                                }
                            }
                        }
                );
            }
        }).start();
    }

    private void createDownloadAllPictureTask(final String text) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LinkedTreeMap linkedTreeMap = (LinkedTreeMap) getAllPaint(getPixivId(text)).body.illusts;
                            for (Object o : linkedTreeMap.keySet()) {
                                String key = (String) o;
                                //    String value = (String) linkedTreeMap.get(key);
                                createDownloadTask(key);
                                Thread.sleep(2000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            showToast(e.toString());
//					  if (getAllPaint(text).body.illusts instanceof ArrayList) {
//						  for (Object o : (ArrayList) (getAllPaint(text).body.illusts)) {
//
//							}
//						}
                        }
                    }
                }
        ).start();
    }

    private Bitmap getThumb(String picId) {
        String main = readStringFromNetwork("https://www.pixiv.net/member_illust.php?mode=medium&illust_id=" + picId);
        if (main == null) return null;
        String flag = "\"small\":\"";
        int index1 = main.indexOf(flag) + flag.length();
        int index2 = main.indexOf("\"", index1);
        String picUrl = main.substring(index1, index2);
        try {
            return getBitmapFromNetwork(picUrl);
        } catch (IOException e) {
            e.printStackTrace();
            //       showToast(e.toString());
            return null;
        }
    }

    public void getToken() {
        String main = readStringFromNetwork("https://www.pixiv.net/member_illust.php?mode=medium&illust_id=74780259");
        String flag = "token: \"";
        int index1 = main.indexOf(flag) + flag.length();
        int index2 = main.indexOf("\"", index1);
        token = main.substring(index1, index2);
    }

    public void addFa(String pixivID) {

        HttpURLConnection conn = null;
        try {
            showToast("start");
            String Strurl = "https://www.pixiv.net/ajax/illusts/bookmarks/add";
            URL url = new URL(Strurl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //   conn.setRequestProperty("ser-Agent", "Fiddler");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(5 * 1000);
            conn.addRequestProperty("Referer", "https://www.pixiv.net/member_illust.php?mode=medium&illust_id=" + pixivID);
            //	conn.setRequestProperty(":authority", "www.pixiv.net");
            conn.setRequestProperty(":authority", "www.pixiv.net");
            conn.setRequestProperty(":method", "POST");
            conn.setRequestProperty(":path", "/ajax/illusts/bookmarks/add");
            conn.setRequestProperty(":scheme", "https");
            //conn.setRequestProperty(":path", "/ajax/illusts/bookmarks/add");
            //	conn.setRequestProperty(":scheme", "https");
            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("accept-encoding", "gzip, deflate, br");
            conn.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
            conn.setRequestProperty("content-type", "application/json; charset=utf-8");
            conn.setRequestProperty("origin", "https://www.pixiv.net");
            conn.setRequestProperty("x-csrf-token", token);
            //String c=URLEncoder.encode("{\"illust_id\":" + pixivID + ",\"restrict\":0,\"comment\":\"\",\"tags\":[]}".toString(), "UTF-8");
            String c = "{\"illust_id\":\"" + pixivID + "\",\"restrict\":0,\"comment\":\"\",\"tags\":[]}";
            conn.setRequestProperty("Content-Length", c.length() + "");
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(c.getBytes());
            outputStream.flush();
            outputStream.close();
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                //     Log.i("PostGetUtil","post请求成功");
                InputStream in = conn.getInputStream();
                //          String backcontent=IOUtils.readString(in);

                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader bufferReader = new BufferedReader(isr);
                String inputLine = "";
                StringBuilder resultData = new StringBuilder();
                while ((inputLine = bufferReader.readLine()) != null) {
                    resultData.append(inputLine).append("\n");
                }

                showToast(resultData.toString());
                //      backcontent= URLDecoder.decode(backcontent,"UTF-8");
                //     Log.i("PostGetUtil",backcontent);
                in.close();
            } else {
                showToast("post请求失败:" + conn.getResponseCode());
            }

        } catch (Exception e) {
            showToast(e.toString());
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }


		/*
         Map<String, String> map = new HashMap<>();
		 map.put(":authority", "www.pixiv.net");
		 map.put(":method", "POST");
		 map.put(":path", "/ajax/illusts/bookmarks/add");
		 map.put(":scheme", "https");
		 map.put("accept", "application/json");
		 map.put("accept-encoding", "gzip, deflate, br");
		 map.put("accept-language", "zh-CN,zh;q=0.9");
		 map.put("content-type", "application/json; charset=utf-8");
		 map.put("origin", "https://www.pixiv.net");
		 map.put("x-csrf-token", token);
		 Connection connection = Jsoup.connect("https://www.pixiv.net/ajax/illusts/bookmarks/add");
		 connection.cookies(cookieToMap(SharedPreferenceHelper.getValue(Data.preferenceKeys.cookieValue)));
		 connection.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
		 connection.referrer("https://www.pixiv.net/member_illust.php?mode=medium&illust_id=74810169");
		 connection.data("illust_id","74810169");
		 connection.data("restrict","0");
		 connection.data("comment","");
		 connection.data("tags","");


		 //     connection.data("{\"illust_id\":\"74810169\",\"restrict\":0,\"comment\":\"\",\"tags\":[]}");
		 connection.headers(map);
		 connection.ignoreContentType(true).method(Connection.Method.POST);
		 try {
		 Connection.Response response = connection.execute();
		 showToast(response.body());
		 } catch (IOException e) {
		 showToast(e.toString());
		 e.printStackTrace();
		 }*/
    }

	/*  public Bitmap getPixivHead() {
     String main = readStringFromNetwork("https://www.pixiv.net/member_illust.php?mode=medium&illust_id=74780259");
	 String flag = "profileImg: \"";
	 int index1 = main.indexOf(flag) + flag.length();
	 int index2 = main.indexOf("\"", index1);
	 String picUrl = main.substring(index1, index2);
	 LogTool.i("" + index1 + " " + index2 + " " + picUrl.replace("\\", ""));
	 try {
	 return getBitmapFromNetwork(picUrl.replace("\\", ""));
	 } catch (IOException e) {
	 e.printStackTrace();
	 //        showToast(e.toString());
	 return null;
	 }
	 }*/

    private Bitmap getBitmapFromNetwork(String picUrl) throws IOException {
        URL url = new URL(picUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.addRequestProperty("Referer", "https://www.pixiv.net/member_illust.php?mode=medium&illust_id=74780259");
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return BitmapFactory.decodeStream(conn.getInputStream());
        }
        return null;
    }

    private PojoPainting getPicInfo(String picId) {
        String main = readStringFromNetwork("https://www.pixiv.net/member_illust.php?mode=medium&illust_id=" + picId);
        if (main == null) return null;
        String flag = "\"illustType\":";
        int index1 = main.indexOf(flag) + flag.length();
        int type = Integer.parseInt(main.substring(index1, index1 + 1));
        PojoPainting pijb = new PojoPainting();
        pijb.id = picId;
        try {
            switch (type) {
                case 0:
                case 1:
                    pijb.isAnimPicture = false;
                    String picJsonAddress1 = "https://www.pixiv.net/ajax/illust/" + picId + "/pages";
                    pijb.staticPicJavaBean = new Gson().fromJson(readStringFromNetwork(picJsonAddress1), PojoStaticPainting.class);
                    break;
                case 2:
                    pijb.isAnimPicture = true;
                    String picJsonAddress2 = "https://www.pixiv.net/ajax/illust/" + picId + "/ugoira_meta";
                    pijb.pojoAnimPainting = new Gson().fromJson(readStringFromNetwork(picJsonAddress2), PojoAnimPainting.class);
                    break;
            }
        } catch (Exception e) {
            showToast(getActivity().getString(R.string.maybe_need_login));
            showToast(e.toString());
            getActivity().startActivity(new Intent(getActivity(), LoginPixivActivity.class));
        }
        return pijb;
    }

    public PojoAllPainting getAllPaint(String uid) {
        String picJsonAddress = "https://www.pixiv.net/ajax/user/" + uid + "/profile/all";
        try {
            return new Gson().fromJson(readStringFromNetwork(picJsonAddress), PojoAllPainting.class);
        } catch (Exception e) {
            return new PojoAllPainting();
        }
    }

    public String readStringFromNetwork(String url) {
        Connection.Response response = null;
        try {
            Connection connection = Jsoup.connect(url);
            connection.cookies(cookieToMap(SharedPreferenceHelper.getPixivCookie()));
            connection.referrer("https://www.pixiv.net/member_illust.php?mode=medium&illust_id=" + getPixivId(url));
            connection.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
            connection.ignoreContentType(true).method(Connection.Method.GET);
            response = connection.execute();
            //		showToast(String.valueOf(response.statusCode()));
            //		Thread.sleep(2900);
            //       LogTool.i(response.body());
        } catch (Exception e) {
            showToast(e.toString());
            return null;
        }
        return response.body();
    }

    public Map<String, String> cookieToMap(String value) {
        if (value == null) {
            showToast("请先登录");
            getActivity().startActivity(new Intent(getActivity(), LoginPixivActivity.class));
            return null;
        }
        Map<String, String> map = new HashMap<>();
        String values[] = value.split("; ");
        for (String val : values) {
            String vals[] = val.split("=");
            if (vals.length == 2) {
                map.put(vals[0], vals[1]);
            } else if (vals.length == 1) {
                map.put(vals[0], "");
            }
        }
        return map;
    }

    public String getPixivId(String str) {
        int pageIndex = str.indexOf("&page");
        if (pageIndex > 1) {
            str = str.substring(0, pageIndex);
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public enum Type {
        pid,
        uid
    }
}
