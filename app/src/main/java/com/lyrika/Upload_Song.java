package com.lyrika;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Upload_Song extends AppCompatActivity {

    public EditText songTitle;
    public EditText songLyric;
    public Button but1;
    final String TAG="L";

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_song);
        songTitle = (EditText) findViewById(R.id.titleSong);
        songLyric = (EditText) findViewById(R.id.lyricSong);
        but1 = (Button) findViewById(R.id.uplSong);




        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TiSong = songTitle.getText().toString();
                String LySong = songLyric.getText().toString();
                uploadSong(TiSong,LySong);



            }
        });

}





protected void uploadSong(String tS,String tL){
        // Create a new user with a first, middle, and last name
        Map<String, Object> user = new HashMap<>();
        String tS1 = tS;
        String tL1= tL;
        user.put("Title", tS1);
        user.put("lyrics", tL1);


// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Intent intent= new Intent(Upload_Song.this,MainActivity.class);

                        startActivities(new Intent[]{intent});
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }



}

