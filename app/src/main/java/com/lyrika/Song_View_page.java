package com.lyrika;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static com.lyrika.DBqueries.firebaseFirestore;

public class Song_View_page extends AppCompatActivity {

    public TextView titleView;
    private ImageView addProfileIcon, homeBtn, uploadBtn, accountBtn;
    public TextView lyrikView;
    final String TAG = "L";
    private Toolbar toolbar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_view_page);
        titleView = (TextView) findViewById(R.id.SongTitle);
        lyrikView = (TextView) findViewById(R.id.SongLyric);
       // String Tempholder = getIntent().getStringExtra("ListClick");
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.myToolBar);



        // Adding App Bar Code
        homeBtn = findViewById(R.id.Home_btn);
        uploadBtn = findViewById(R.id.add_song_btn_app_bar);
        accountBtn = findViewById(R.id.account_img);


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homeintent = new Intent(Song_View_page.this, MainActivity.class);
                startActivity(Homeintent);


                //  frameLayout = findViewById(R.id.main_frame_layout);


            }
        });

        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent Homeintent = new Intent(Song_View_page.this, MyAccountPage.class);
                startActivity(Homeintent);

            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Uploadintent = new Intent(Song_View_page.this, Upload_Song.class);
                startActivity(Uploadintent);


            }
        });

        //Appbar Code
        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("Songs").document(getIntent().getStringExtra("Song_Id"))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                 DocumentSnapshot documentSnapshot = task.getResult();

                    titleView.setText(documentSnapshot.get("Title").toString());

                    lyrikView.setText(documentSnapshot.get("Lyrik_T").toString());
                    Toast.makeText(Song_View_page.this, "Successfully connected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Song_View_page.this, "Please Somthing Went wrong", Toast.LENGTH_SHORT).show();


                }
            }
        });


        //   titleView.setText("This is "+Tempholder);
//addDb();
//addAnotherDb();
       // display(Tempholder);
    }


 /*   protected void addDb() {

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("FN", "Ada");
        user.put("LN", "Lovelace");
        user.put("BD", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {


                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });


    }

    protected void addAnotherDb() {
        // Create a new user with a first, middle, and last name
        Map<String, Object> user = new HashMap<>();
        user.put("Title", "Song 3");
        user.put("Lyrics", "అగ్ని మండించు – నాలో అగ్ని మండించు (2)\n" +
                "పరిశుద్ధాత్ముడా – నాలో అగ్ని మండించు (2)\n" +
                "\n" +
                "అగ్ని మండుచుండెనే – పొద కాలిపోలేదుగా (2)\n" +
                "ఆ అగ్ని లో నుండే – నీవు మోషేను దర్శించినావే (2)       ||అగ్ని||\n" +
                "\n" +
                "అగ్ని కాల్చి వేసెనే – సిద్ధం చేసిన అర్పణను (2)\n" +
                "ఆ అగ్ని ద్వారానే – నీవు గిద్యోన్ని దైర్యపరచితివే (2)       ||అగ్ని||\n" +
                "\n" +
                "అగ్ని కాన రానందునా – వారు సిగ్గు పడిపోయిరే (2)\n" +
                "నీ అగ్ని దిగిరాగా – నీవు ఏలియాను ఘన పరచినావే (2)       ||అగ్ని||\n" +
                "\n" +
                "ప్రాణ ఆత్మ శరీరము – నీకే అర్పించు చున్నానయ్యా (2)\n" +
                "నీ ఆత్మ వరములతో – నను అలంకరించుమయా (2)       ||అగ్ని||");


// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }


    private void display(String tmpholder) {

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().containsValue(tmpholder)) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    //  titleView.append("\n\nTitle "+document.getData().get("lyrics")+"\n");
                                    titleView.setText("" + document.getData().get("Title") + "\n");

                                    String songLyrics = (String) document.getData().get("lyrics");
                                    //String abc= songLyrics.replacingOccurrences(of: "\n", with: "\n");
                                    //    String abc=  songLyrics.replaceAll( "<lyrics>", "\n" );
                                    lyrikView.setText("\n\n " + document.getData().get("lyrics") + "\n");
                                    // lyrikView.setText("\n\n "+abc+"\n");

                                }
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

  */


}
