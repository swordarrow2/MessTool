package com.meng.messtool.modules.electronic.semiee;

import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.api.semiee.*;
import com.meng.messtool.*;
import com.meng.tools.*;

import java.io.*;
import java.nio.charset.*;

public class ChipInformationFragment extends BaseFragment {

    private TextView lv;
    private ScrollView sv;

    @Override
    public String getTitle() {
        return "芯片信息";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        lv = new TextView(getActivity());
        sv = new ScrollView(getActivity());
        sv.setFocusable(true);
        sv.setClickable(true);
        lv.setClickable(true);
        lv.setFocusable(true);
        sv.addView(lv);
        return sv;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final File fileJson = new File(getActivity().getExternalFilesDir("") + "/test_semiee_chip_information.json");
        if (!fileJson.exists()) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    FileTool.saveToFile(fileJson, GSON.toJson(SemieeApi.getChipInformation("8c16d867-cf5b-4442-8fc2-773f03fb02e8")).getBytes(StandardCharsets.UTF_8));
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                init(fileJson);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }).start();
        } else {
            try {
                init(fileJson);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void init(File fileJson) throws IOException {
        String json = FileTool.readString(fileJson);
        ChipInformation result = GSON.fromJson(json, ChipInformation.class);

        lv.setText(result.toString());
        // AndroidContent.copyToClipboard(result.toString());

    }
}

