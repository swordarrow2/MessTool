package com.meng.messtool.modules.media.gif;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.AlertDialog;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;
import com.meng.messtool.customview.MaterialDesign.*;
import com.meng.tools.app.*;

import java.io.*;
import java.util.*;

public class GIFCreator extends BaseFragment {

    public MDEditText mengEtFrameDelay;
    public ArrayList<GIFFrame> selectedImages = new ArrayList<>();
    public EditFrameAdapter editFrameAdapter;
    public FloatingButton fabAdd;
    public FloatingButton fabEncode;

    private int mPreviousVisibleItem;
    private boolean encoding = false;

    @Override
    public String getTitle() {
        return "GIF合成器";
    }

    @Override
    public String getVersionName() {
        return "V1.0.0";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.picture_encode_gif, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mengEtFrameDelay = (MDEditText) view.findViewById(R.id.gif_creator_delay);
        fabAdd = (FloatingButton) view.findViewById(R.id.fab_add);
        fabEncode = (FloatingButton) view.findViewById(R.id.fab_encode);
        ListView listView = (ListView) view.findViewById(R.id.gif_creator_list);
        fabAdd.setOnClickListener(listenerBtnClick);
        fabEncode.setOnClickListener(listenerBtnClick);
        editFrameAdapter = new EditFrameAdapter(getActivity(), selectedImages, true);
        listView.setAdapter(editFrameAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View v, int position, long id) {
                final EditText editTextName = new EditText(getActivity());
                final GIFFrame personInfo = (GIFFrame) parent.getItemAtPosition(position);
                editTextName.setText(String.valueOf(personInfo.delay));
                new AlertDialog.Builder(getActivity())
                        .setView(editTextName)
                        .setTitle("设置帧延时(ms)")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                personInfo.delay = Integer.parseInt(editTextName.getText().toString());
                                editFrameAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("确定删除吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                selectedImages.remove(position);
                                editFrameAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });
        /*   listView.setOnScrollListener(new AbsListView.OnScrollListener() {
		 @Override
		 public void onScrollStateChanged(AbsListView view, int scrollState) {
		 }

		 @Override
		 public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		 if (firstVisibleItem > mPreviousVisibleItem) {
		 fabAdd.hide(true);
		 fabEncode.hide(true);
		 } else if (firstVisibleItem < mPreviousVisibleItem) {
		 fabAdd.show(true);
		 fabEncode.show(true);
		 }
		 mPreviousVisibleItem = firstVisibleItem;
		 }
		 });*/
        fabAdd.hide(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabAdd.show(true);
            }
        }, 300);

        fabEncode.hide(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabEncode.show(true);
            }
        }, 600);
    }

    View.OnClickListener listenerBtnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_add:
                    Intent intent = new Intent(getActivity(), SelectFileActivity.class);
                    startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_FILE);
                    break;
                case R.id.fab_encode:
                    if (encoding) return;
                    encoding = true;
                    showToast("开始生成gif");
                    ThreadPool.execute(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                fabEncode.setMax(selectedImages.size());
                                File outputFile = FileTool.getAppFile(FileSavePath.GIF, FileTool.FileType.gif_89a);
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        fabEncode.setShowProgressBackground(true);
                                        fabEncode.setIndeterminate(false);
                                    }
                                });
                                AnimatedGifEncoder localAnimatedGifEncoder = new AnimatedGifEncoder();
                                localAnimatedGifEncoder.start(baos);//start
                                localAnimatedGifEncoder.setRepeat(0);//设置生成gif的开始播放时间。0为立即开始播放
                                int currentFile = 1;
                                for (GIFFrame gifFrame : selectedImages) {
                                    localAnimatedGifEncoder.setDelay(gifFrame.delay);
                                    localAnimatedGifEncoder.addFrame(BitmapFactory.decodeFile(gifFrame.filePath));
                                    setProgress(currentFile);
                                    ++currentFile;
                                }
                                localAnimatedGifEncoder.finish();
                                FileOutputStream fos = new FileOutputStream(outputFile);
                                baos.writeTo(fos);
                                baos.flush();
                                fos.flush();
                                baos.close();
                                fos.close();
                                getActivity().getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(outputFile)));
                                showToast("完成 : " + outputFile.getAbsolutePath());
                            } catch (Exception e) {
                                showToast(e.toString());
                            }
                            encoding = false;
                        }
                    });
                    break;
            }
        }
    };

    private void setProgress(final int progress) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (fabEncode.getMax() == progress) {
                    fabEncode.hideProgress();
                } else {
                    fabEncode.setProgress(progress, true);
                }
                //    if (progress == 100) {
                //    fabEncode.hideProgress();
                //	fabEncode.setIndeterminate(false);
                //      } else {
                //	  fabEncode.setIndeterminate(false);
                //           }
            }
        });
    }

    @Override
    public void onResume() {
        if (editFrameAdapter != null) {
            editFrameAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REQUEST_CODE_SELECT_FILE && resultCode == Activity.RESULT_OK) {
            showToast("add frame ok");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
