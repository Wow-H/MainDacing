package com.robot.asus.MainDancing;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.asus.robotframework.API.ExpressionConfig;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;

import java.util.Locale;

public class StartActivity extends RobotActivity {
    private static int iCurrentCommandSerial;
    private static boolean isSpeaked;

    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);

            if (serial == iCurrentCommandSerial && state == RobotCmdState.SUCCEED) {
                robotAPI.robot.setExpression(RobotFace.HIDEFACE);
                Log.d("onStageCheck", "Succeed" + serial + "");

                isSpeaked = true;
            }
        }
    };

    public static RobotCallback.Listen robotListenCallback = new RobotCallback.Listen() {
        @Override
        public void onFinishRegister() {

        }

        @Override
        public void onVoiceDetect(JSONObject jsonObject) {

        }

        @Override
        public void onSpeakComplete(String s, String s1) {

        }

        @Override
        public void onEventUserUtterance(JSONObject jsonObject) {

        }

        @Override
        public void onResult(JSONObject jsonObject) {

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };

    public StartActivity() {
        super(robotCallback, robotListenCallback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button05 = (Button) findViewById(R.id.genderTextView);


        button05.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub

                Intent it = new Intent();
                it.setClass(StartActivity.this, VideoActivity.class);
                startActivity(it);

            }

        });
        Button button4 = (Button) findViewById(R.id.dancewithme);

        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                if (Locale.getDefault().getLanguage().equals("en")) {
                    robotAPI.robot.setExpression(RobotFace.PLEASED, getResources().getString(R.string.MA_Hello), new ExpressionConfig().speed(85));
                    iCurrentCommandSerial = robotAPI.robot.setExpression(RobotFace.ACTIVE, getResources().getString(R.string.MA_intro), new ExpressionConfig().speed(85));
                } else {
                    robotAPI.robot.setExpression(RobotFace.PLEASED, getResources().getString(R.string.MA_Hello));
                    iCurrentCommandSerial = robotAPI.robot.setExpression(RobotFace.ACTIVE, getResources().getString(R.string.MA_intro));
                }

                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(isSpeaked) {
                            startDancingWithMe();
                            isSpeaked = false;
                        } else {
                            handler.postDelayed(this, 500);
                        }
                    }
                });

            }

        });

        Button button = (Button) findViewById(R.id.zenbosdance);

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub

                Intent it = new Intent();
                it.setComponent(new ComponentName("com.robot.asus.ZenboDancing","com.robot.asus.ZenboDancing.MainActivity"));
                startActivity(it);



            }

        });
    }

    private void startDancingWithMe() {
        Intent it = new Intent();
        it.setComponent(new ComponentName("com.robot.asus.DancingWithMe","com.robot.asus.DancingWithMe.ShowYeah"));
        startActivity(it);
    }


}
