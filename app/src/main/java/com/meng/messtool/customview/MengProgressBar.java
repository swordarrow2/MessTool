package com.meng.messtool.customview;

import android.app.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;

public class MengProgressBar extends LinearLayout {
    public Activity context;
    private TextView tvTitle;
    private TextView tvStatus;
    private TextView tvProgress;
    private ProgressBar progressBar;

    public MengProgressBar(Activity context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.system_background_task_list_item, this);
        tvTitle = (TextView) findViewById(R.id.main_list_item_textview_title);
        tvStatus = (TextView) findViewById(R.id.main_list_item_textview_statu);
        tvProgress = (TextView) findViewById(R.id.main_list_item_textview_statu_progress_text);
        progressBar = (ProgressBar) findViewById(R.id.main_list_item_progressbar);
    }

    public void setProgress(int progress) {
        progressBar.setProgress(progress);
    }

    public int getProgress() {
        return progressBar.getProgress();
    }

    public void setStatusText(String statusText) {
        tvStatus.setText(statusText);
    }

    public String getStatusText() {
        return tvStatus.getText().toString();
    }

    public void setProgressText(String progressText) {
        tvProgress.setText(progressText);
    }

    public String getProgressText() {
        return tvProgress.getText().toString();
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public String getTitle() {
        return tvTitle.getText().toString();
    }

}
