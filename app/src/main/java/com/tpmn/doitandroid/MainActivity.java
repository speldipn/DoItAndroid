package com.tpmn.doitandroid;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";
    private static final int REQUEST_CODE = 100;

    Animation translateIn;
    Animation translateOut;

    ViewGroup container;

    Button startButton;
    Button stopButton;
    TextView infoTextView;

    AnimationThread animationThread;
    boolean isRun = false;

    CardItem card;
    CardItem card2;

    List<Photo> list;

    private String[] permssions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        permissions();
    }

    private void permissions() {
        requestPermissions(permssions, REQUEST_CODE);
    }


    private void setup() {
        container = findViewById(R.id.container);
        infoTextView = findViewById(R.id.infoTextView);

        translateIn = AnimationUtils.loadAnimation(this, R.anim.translate_in);
        translateOut = AnimationUtils.loadAnimation(this, R.anim.translate_out);

        list = getGalleryList();

        card = new CardItem(this);
        container.addView(card);

        card2 = new CardItem(this);
        container.addView(card2);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener((v) -> {
            animationThread = new AnimationThread();
            animationThread.start();
        });

        stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener((v) -> {
            isRun = false;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isRun = false;
    }


    private Photo getPhoto(int position) {
        return list.get(position % list.size());
    }

    class AnimationThread extends Thread {
        int photoIndex = 0;
        int index = 0;

        @Override
        public void run() {
            isRun = true;
            while (isRun) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    infoTextView.setText(String.format(getString(R.string.slding_info_format), (photoIndex % list.size())/2, list.size()/2));

                    if (index == 0) {
                        Photo photo0 = getPhoto(photoIndex++);
                        card.setPhoto(photo0.thumbnailPath, photo0.date);
                        Photo photo1 = getPhoto(photoIndex++);
                        card.setPhoto2(photo1.thumbnailPath, photo1.date);

                        card.startAnimation(translateIn);
                        card2.startAnimation(translateOut);
                    } else {
                        Photo photo2 = getPhoto(photoIndex++);
                        card2.setPhoto(photo2.thumbnailPath, photo2.date);
                        Photo photo3 = getPhoto(photoIndex++);
                        card2.setPhoto2(photo3.thumbnailPath, photo3.date);

                        card.startAnimation(translateOut);
                        card2.startAnimation(translateIn);
                    }
                });

                index += 1;
                index %= 2;

                try { Thread.sleep(2000); } catch (Exception ignored) { }
            }
        }
    }

    public List<Photo> getGalleryList() {
        ContentResolver resolver = getContentResolver();
        List<Photo> list = new ArrayList<>();

        String projections[] = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Thumbnails.DATA
        };

        String selection = MediaStore.Images.Media.BUCKET_DISPLAY_NAME + "='Screenshots'";

        Uri photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = resolver.query(
                photoUri,
                projections,
                selection,
                null,
                MediaStore.Images.Media.DATE_TAKEN + " DESC"
        );
        while (cursor.moveToNext()) {
            Photo photo = new Photo(
                    cursor.getString(cursor.getColumnIndex(projections[0])),
                    cursor.getString(cursor.getColumnIndex(projections[1])),
                    cursor.getString(cursor.getColumnIndex(projections[2])),
                    cursor.getString(cursor.getColumnIndex(projections[3])),
                    cursor.getString(cursor.getColumnIndex(projections[4]))
            );
            list.add(photo);
        }
        cursor.close();
        return list;
    }

    class Photo {
        public Photo(String id, String bucketName, String displayName, String date, String thumbnailPath) {
            this.id = id;
            this.bucketName = bucketName;
            this.displayName = displayName;
            this.date = date;
            this.thumbnailPath = thumbnailPath;
        }

        public String id;
        public String bucketName;
        public String displayName;
        public String date;
        public String thumbnailPath;

        @Override
        public String toString() {
            return "Photo{" +
                    "id='" + id + '\'' +
                    ", bucketName='" + bucketName + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", date='" + date + '\'' +
                    ", thumbnailPath='" + thumbnailPath + '\'' +
                    '}';
        }
    }
}