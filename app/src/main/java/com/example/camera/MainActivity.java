package com.example.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnCamera;
    private ImageView ivPhoto;
    private final int CAMERA_REQUEST = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    ivPhoto.setImageBitmap(bitmap);
                }
                break;
        }
    }

    private void initView() {
        ivPhoto = findViewById(R.id.iv_capture_photo);
        btnCamera = findViewById(R.id.btn_camera_invoke);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动本机摄像头
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //启动系统拍照应用,并将拍摄的照片返回并显示在ImageView组件中
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });
    }
}