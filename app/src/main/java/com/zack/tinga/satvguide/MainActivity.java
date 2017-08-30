package com.zack.tinga.satvguide;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.zack.tinga.satvguide.activities.MainMenu;
import com.zack.tinga.satvguide.classes.TVClass;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int GALLERY_CODE = 1;
    EditText editTextName, editTextTime;
    Button buttonDone, buttonNext;

    DatabaseReference mDatabaseReference;
    StorageReference mStorage;

    ImageButton imageButtonSelect;
    VideoView mVideoView;

    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("sabc3");
        mStorage = FirebaseStorage.getInstance().getReference();

        editTextName = (EditText) findViewById(R.id.textview_show_name);
        editTextTime = (EditText) findViewById(R.id.textview_show_time);
        buttonDone = (Button) findViewById(R.id.btnDone);
        buttonNext = (Button) findViewById(R.id.btnNext);
        imageButtonSelect = (ImageButton) findViewById(R.id.imageButtonSelect);
        mVideoView = (VideoView) findViewById(R.id.videoViewDis);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        selectImage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            try {
                String path = data.getData().toString();
                mVideoView.setVideoPath(path);
                mVideoView.requestFocus();
                mVideoView.start();
                int durtn = mVideoView.getDuration();
                System.out.println("Duration" + durtn);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void selectImage() {

        imageButtonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_CODE);
            }
        });
    }

    public void insertData() {
        final String name = editTextName.getText().toString();
        final String time = editTextTime.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(time)) {

            StorageReference filePath = mStorage.child("show_images").child(videoUri.getLastPathSegment());
            filePath.putFile(videoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri img = taskSnapshot.getDownloadUrl();
                    String id = mDatabaseReference.push().getKey();
                    TVClass tvClass = new TVClass(name, time, img.toString());
                    mDatabaseReference.child(id).setValue(tvClass);
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
