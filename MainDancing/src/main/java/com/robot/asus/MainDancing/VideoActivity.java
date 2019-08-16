package com.robot.asus.MainDancing;

import android.Manifest;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;


public class VideoActivity extends RobotActivity implements View.OnClickListener {
    private VideoView video;

    private MediaController mediaController;
    private Button raw;
    private Button assets;
    private AssetManager assetManager;

    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);
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

    public VideoActivity() {
        super(robotCallback, robotListenCallback);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);
        initView();
        //requestPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();

        playVideoRaw();
    }

    private void requestPermission() {
        String[] needPermission = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        ActivityCompat.requestPermissions(this, needPermission, 1);
    }

    private void initView() {
        video = (VideoView) findViewById(R.id.video);

        //raw = (Button) findViewById(R.id.raw);
        //raw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //playVideoRaw();
    }

    /**
     * 播放raw的小视频
     */
    private void playVideoRaw() {
        //mediaController = new MediaController(this);
        //获取raw.mp4的uri地址

        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        //让video和mediaController建立关联
        //video.setMediaController(mediaController);
        //mediaController.setMediaPlayer(video);
        //让video获取焦点
        //video.requestFocus();
        //监听播放完成，
        /*video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //重新开始播放
                video.start();
            }
        });*/

        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                //mp.setVolume(0f, 0f);
            }
        });
    }
}



