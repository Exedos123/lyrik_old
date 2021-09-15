package com.lyrik;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.lyrik.DBqueries.firebaseFirestore;


public class  ftab1 extends Fragment {

    public ftab1() {
        // Required empty public constructor
    }

    public TextView titleView, teluguL;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ftab1, container, false);


        titleView = (TextView) view.findViewById(R.id.telugu_Title);

        teluguL = (TextView) view.findViewById(R.id.telugu_lyric);

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("Songs").document(getActivity().getIntent().getStringExtra("Song_Id"))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    if ((documentSnapshot.getString("Lyrik_T")!=null) && (documentSnapshot.getString("Title"))!=null){
                        titleView.setText(documentSnapshot.get("Title").toString());


                        teluguL.setText(documentSnapshot.get("Lyrik_T").toString());
                    }else{
                        titleView.setText("Lyric Not Updated ");


                        teluguL.setText("");
                    }

                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }
}
