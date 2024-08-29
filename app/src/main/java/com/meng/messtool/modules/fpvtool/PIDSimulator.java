package com.meng.messtool.modules.fpvtool;

import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.SeekBar.*;
import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.app.*;
import java.util.concurrent.*;

public class PIDSimulator extends BaseFragment {
    private PidStruct pidStruct = new PidStruct();
    /*
     *@author 清梦
     *@date 2024-07-11 16:21:26
     */
    public static final String TAG = "PIDSimulator";
    private SeekBar pbP, pbI, pbD, pbExpect, pbActual;
    private TextView out;

    @Override
    public String getTitle() {
        return "PID模拟器";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.function_electronic_pid_simulator, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        out = (TextView) view.findViewById(R.id.pid_simulatorTextView_out);
        pbP = (SeekBar) view.findViewById(R.id.pid_simulatorSeekBar_P);
        pbI = (SeekBar) view.findViewById(R.id.pid_simulatorSeekBar_I);
        pbD = (SeekBar) view.findViewById(R.id.pid_simulatorSeekBar_D);
        pbExpect = (SeekBar) view.findViewById(R.id.pid_simulatorSeekBar_expect);
        pbActual = (SeekBar) view.findViewById(R.id.pid_simulatorSeekBar_actual);
        pbP.setProgress(1);
        pbI.setProgress(1);
        pbD.setProgress(1);
        pbExpect.setProgress(65535);

        pbP.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar p1, int p2, boolean p3) {
                pidStruct.kp = p2;
            }

            @Override
            public void onStartTrackingTouch(SeekBar p1) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar p1) {
            }
        });
        pbI.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar p1, int p2, boolean p3) {
                pidStruct.ki = p2;
            }

            @Override
            public void onStartTrackingTouch(SeekBar p1) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar p1) {
            }
        });
        pbD.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar p1, int p2, boolean p3) {
                pidStruct.kd = p2;
            }

            @Override
            public void onStartTrackingTouch(SeekBar p1) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar p1) {
            }
        });
        pbExpect.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar p1, int p2, boolean p3) {
                pidStruct.expect = p2;
            }

            @Override
            public void onStartTrackingTouch(SeekBar p1) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar p1) {
            }
        });
        ThreadPool.executeAtFixedRate(new Runnable() {

            @Override
            public void run() {
                final float result = (int) pidStruct.cal();
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        out.setText("out:" + result);
                        pbActual.setProgress((int) result);
                    }
                });
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

//    float PID_Cal(float Speed)
//    {
//        pid.SetSpeed = Speed;
//        pid.Err = pid.SetSpeed - pid.ActualSpeed;//误差的计算，即比例控制
//        pid.Integral += pid.Err;//误差相加，即积分控制
//        pid.Voltage = pid.Kp * pid.Err + pid.Ki * pid.Integral + pid.Kd *
//            (pid.Err - pid.Err_last);//根据位置型PID控制的公式
//        pid.Err_last = pid.Err;
//        pid.ActualSpeed = pid.Voltage * 1.0; //转换
//        return pid.ActualSpeed;//PID控制后的实际输出值
//    }

    private class PidStruct {
        public float kp = 1;
        public float ki = 0.1f;
        public float kd = 1;

        public float expect;
        public float actual;
        public float error;
        public float lastError;
        public float errorSum;//integral;

        public float cal() {
            error = expect - actual;
            errorSum += error;
            actual = kp * error + ki * errorSum + kd * (error - lastError);
            lastError = error;
            return actual;
        }

    }
}
