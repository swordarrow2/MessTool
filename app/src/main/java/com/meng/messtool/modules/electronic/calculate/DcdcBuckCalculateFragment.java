package com.meng.messtool.modules.electronic.calculate;

import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.messtool.customview.MaterialDesign.*;

import java.util.*;

public class DcdcBuckCalculateFragment extends BaseFragment implements View.OnClickListener {

    /*
     *@author 清梦
     *@date 2024-06-26 02:31:23
     */
    public static final String TAG = "DcdcCalculateFragment";

    private TabHost tabHost;
    private TextView result;
    private Button btn;
    private MDEditText tab1vi, tab1vo, tab1vd, tab1io, tab1fr,
            tab2vi, tab2vo, tab2vd, tab2io, tab2fr, tab2l,
            tab3vi, tab3vo, tab3vd, tab3dv, tab3io, tab3fr, tab3l,
            tab4vi, tab4vo, tab4vd, tab4io, tab4fr, tab4l, tab4dv;

    @Override
    public String getTitle() {
        return "DCDC Buck元件选型";
    }

    @Override
    public String getVersionName() {
        return "V1.1.0";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.electronic_dcdc_buck_calculate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabHost = (TabHost) view.findViewById(android.R.id.tabhost);
        tabHost.setup();
        result = (TextView) view.findViewById(R.id.buck_calculateTextViewResult);

        initViews(view);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("电感感值", null).setContent(R.id.buck_inductor_select));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("电感电流", null).setContent(R.id.buck_inductor_current));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("输入滤波电容", null).setContent(R.id.buck_input_capcitor));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("输出滤波电容", null).setContent(R.id.buck_output_capcitor));
        btn.setOnClickListener(this);
    }

    private void calculateL(double vi, double vo, double vd, double io, double f) throws NumberFormatException {
        double[] result = new double[]{
                ((vo + vd) / (f * 0.2 * io)) * ((vi - vo) / (vi + vd)),
                ((vo + vd) / (f * 0.4 * io)) * ((vi - vo) / (vi + vd))
        };
        setResult(String.format(Locale.CHINA, "合适的感值:%.2fuH~%.2fuH", result[1] * 1000000, result[0] * 1000000));
    }

    private void calculateCurrent(double vi, double vo, double vd, double io, double f, double l) throws NumberFormatException {
        double delta = (((vo + vd) / (f * l)) * ((vi - vo) / (vi + vd)));
        double max = (io + delta / 2);
        setResult(String.format(Locale.CHINA, "电感电流纹波:%.2fA\n电感峰值电流:%.2fA", delta, max));
    }

    private void calculateInputCapcitor(double vi, double vo, double vd, double deltaV, double io, double f, double l) throws NumberFormatException {
        double Ci = ((io / (deltaV * vi * f)) *
                (vo + vd * ((vi - vo) / (vi + vd))) *
                ((vi - vo) / (vi + vd)));
        double esr = deltaV / (io + ((vo + vd) / (2 * f * l)) *
                ((vi - vo) / (vi + vd)));
        setResult(String.format(Locale.CHINA, "使用陶瓷电容:容量不小于:%.2fuF\n使用电解电容:ESR不大于%.2fmΩ", Ci * 1000000, esr * 1000));
    }

    private void calculateOutputCapcitor(double vi, double vo, double vd, double io, double deltaV, double f, double l) throws NumberFormatException {
        double Ci = (((vo + vd) / (8 * f * f * l * deltaV)) * ((vi - vo) / (vi + vd)));
        double esr = ((deltaV * f * l * (vi + vd)) / ((vo + vd) * (vi - vo)));
        setResult(String.format(Locale.CHINA, "使用陶瓷电容:容量不小于:%fuF\n使用电解电容:ESR不大于%fmΩ", Ci * 1000000, esr * 1000));
    }

    private void setResult(CharSequence s) {
        result.setText(s);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (tabHost.getCurrentTab()) {
                case 0:
                    calculateL(tab1vi.getDouble(), tab1vo.getDouble(), tab1vd.getDouble(), tab1io.getDouble(), tab1fr.getDouble() * 1000);
                    break;
                case 1:
                    calculateCurrent(tab2vi.getDouble(), tab2vo.getDouble(), tab2vd.getDouble(), tab2io.getDouble(), tab2fr.getDouble() * 1000, tab2l.getDouble() / 1000000);
                    break;
                case 2:
                    calculateInputCapcitor(tab3vi.getDouble(), tab3vo.getDouble(), tab3vd.getDouble(), tab3dv.getDouble() / 1000, tab3io.getDouble(), tab3fr.getDouble() * 1000, tab3l.getDouble() / 1000000);
                    break;
                case 3:
                    calculateOutputCapcitor(tab4vi.getDouble(), tab4vo.getDouble(), tab4vd.getDouble(), tab4io.getDouble(), tab4dv.getDouble() / 1000, tab4fr.getDouble() * 1000, tab4l.getDouble() / 1000000);
                    break;
            }
        } catch (NumberFormatException e) {
            showToast("请输入正确的数字");
        }
    }

    private void initViews(View view) {
        tab1vi = (MDEditText) view.findViewById(R.id.buck_inductor_select_vi);
        tab1vo = (MDEditText) view.findViewById(R.id.buck_inductor_select_vo);
        tab1vd = (MDEditText) view.findViewById(R.id.buck_inductor_select_vd);
        tab1io = (MDEditText) view.findViewById(R.id.buck_inductor_select_oi);
        tab1fr = (MDEditText) view.findViewById(R.id.buck_inductor_select_freq);

        tab2vi = (MDEditText) view.findViewById(R.id.buck_inductor_current_vi);
        tab2vo = (MDEditText) view.findViewById(R.id.buck_inductor_current_vo);
        tab2vd = (MDEditText) view.findViewById(R.id.buck_inductor_current_vd);
        tab2io = (MDEditText) view.findViewById(R.id.buck_inductor_current_oi);
        tab2fr = (MDEditText) view.findViewById(R.id.buck_inductor_current_freq);
        tab2l = (MDEditText) view.findViewById(R.id.buck_inductor_current_l);

        tab3vi = (MDEditText) view.findViewById(R.id.buck_input_capcitor_vi);
        tab3vo = (MDEditText) view.findViewById(R.id.buck_input_capcitor_vo);
        tab3vd = (MDEditText) view.findViewById(R.id.buck_input_capcitor_vd);
        tab3dv = (MDEditText) view.findViewById(R.id.buck_input_capcitor_deltaV);
        tab3io = (MDEditText) view.findViewById(R.id.buck_input_capcitor_io);
        tab3fr = (MDEditText) view.findViewById(R.id.buck_input_capcitor_freq);
        tab3l = (MDEditText) view.findViewById(R.id.buck_input_capcitor_l);

        tab4vi = (MDEditText) view.findViewById(R.id.buck_output_capcitor_vi);
        tab4vo = (MDEditText) view.findViewById(R.id.buck_output_capcitor_vo);
        tab4vd = (MDEditText) view.findViewById(R.id.buck_output_capcitor_vd);
        tab4dv = (MDEditText) view.findViewById(R.id.buck_output_capcitor_deltaV);
        tab4io = (MDEditText) view.findViewById(R.id.buck_output_capcitor_io);
        tab4fr = (MDEditText) view.findViewById(R.id.buck_output_capcitor_freq);
        tab4l = (MDEditText) view.findViewById(R.id.buck_output_capcitor_l);

        btn = (Button) view.findViewById(R.id.fpv_config_content_framelayout);
    }
}
