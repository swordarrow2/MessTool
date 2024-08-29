package com.meng.messtool.system.task;

import android.app.*;
import android.view.*;
import android.widget.*;
import com.meng.messtool.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.app.*;
import java.util.*;

public class BackgroundTaskAdapter extends BaseAdapter {

    /*
     *@author 清梦
     *@date 2024-04-16 22:28:25
     */
    public static final String TAG = "BackgroundTaskAdapter";

    private static BackgroundTaskAdapter instance;

    private Activity activity;
    private ArrayList<BackgroundTask> list = new ArrayList<>();

    public static BackgroundTaskAdapter getInstance() {
        if (instance == null) {
            instance = new BackgroundTaskAdapter();
        }
        return instance;
    }

    public void init(Activity activity) {
        this.activity = activity;
        Debuger.addLog(TAG, "init", activity.toString());
    }

    private BackgroundTaskAdapter() {
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public BackgroundTaskAdapter addTask(BackgroundTask task) {
        list.add(task);
        notifyDataSetChanged();
        ThreadPool.execute(task);
        Debuger.addLog(TAG, "addTask", task.getTitle());
        return this;
    }

    public void remove(BackgroundTask task) {
        list.remove(task);
        Debuger.addLog(TAG, "removeTask", task.getTitle());
        notifyDataSetChanged();
    }

    public int getCount() {
        return list.size();
    }

    public BackgroundTask getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return list.get(position).hashCode();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.system_background_task_list_item, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.main_list_item_textview_title);
            holder.tvStatus = (TextView) convertView.findViewById(R.id.main_list_item_textview_statu);
            holder.tvProgress = (TextView) convertView.findViewById(R.id.main_list_item_textview_statu_progress_text);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.main_list_item_progressbar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BackgroundTask mpb = list.get(position);
        holder.tvTitle.setText(mpb.getTitle());
        holder.tvStatus.setText(mpb.getStatus());
        holder.progressBar.setMax(mpb.getMaxProgress());
        int progress = mpb.getProgress();
        if (progress == -1) {
            holder.progressBar.setIndeterminate(true);
        } else {
            holder.progressBar.setIndeterminate(false);
            holder.progressBar.setProgress(progress);
        }
        holder.tvProgress.setText(mpb.getProgressText());
        return convertView;
    }

    private class ViewHolder {
        private TextView tvTitle;
        private TextView tvStatus;
        private TextView tvProgress;
        private ProgressBar progressBar;
    }

}
