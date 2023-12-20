package com.example.pr29;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends Activity implements View.OnClickListener {

        final int CAMERA_REQUEST = 1;
        private Uri picUri;
        ImageButton btnWeb, btnMap, btnCall, btnPhoto, btnVideo;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                setContentView(R.layout.activity_main);
                setTitle("Съёмка и Кадрирование");
                btnWeb = findViewById(R.id.btnWeb);
                btnMap = findViewById(R.id.btnMap);
                btnCall = findViewById(R.id.btnCall);
                btnPhoto = findViewById(R.id.btnPhoto);
                btnVideo = findViewById(R.id.btnVideo);

                btnWeb.setOnClickListener(this);
                btnMap.setOnClickListener(this);
                btnCall.setOnClickListener(this);
                btnPhoto.setOnClickListener(this);
                btnVideo.setOnClickListener(this);
        }

        @SuppressLint("NonConstantResourceId")
        public void onClick(View v) {

                Intent intent;
                if (v.getId() == R.id.btnPhoto) {
                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(captureIntent, CAMERA_REQUEST);
                } else if (v.getId() == R.id.btnVideo) {
                        Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        startActivityForResult(captureIntent, CAMERA_REQUEST);
                } else if (v.getId() == R.id.btnWeb) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"));
                        startActivity(intent);
                } else if (v.getId() == R.id.btnMap) {
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("geo:55.754283,37.62002"));
                        startActivity(intent);
                } else if (v.getId() == R.id.btnCall) {
                        intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:89612227990"));
                        startActivity(intent);
                }
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {

                        if (requestCode == CAMERA_REQUEST) {

                                picUri = data.getData();
                        }
                        Bundle extras = data.getExtras();

                        Bitmap thePic = extras.getParcelable("data");

                        ImageView picView = findViewById(R.id.ivPhoto);
                        picView.setImageBitmap(thePic);

                        MediaStore.Images.Media.insertImage(getContentResolver(), thePic, "Название изображения", "Описание изображения");

                }
        }
}