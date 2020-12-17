package com.ensim.scanmev1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Scan extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String reference = "Produit_01";
        getDataFromDb(reference);





    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.scan,container,false);

        return v;
    }

    public void getDataFromDb(String reference){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<String> data = new ArrayList<String>();


        db.collection(reference)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String s = "";
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Map<String, Object> map = document.getData();
                                List<String> l = new ArrayList<String>(map.keySet());
                                for( String key:l){
                                    s = s+key+" : "+map.get(key)+"\n";
                                }
                                s = s+"=========================\n";


                                //Toast.makeText(getContext(),document.getId() + " => " + document.getData(),Toast.LENGTH_LONG).show();

                            }
                            if(task.getResult().isEmpty()){
                                Toast.makeText(getContext(),"Produit Non Authentique",Toast.LENGTH_LONG).show();
                            }
                            else{

                                Toast.makeText(getContext(),"Produit Authentique, See articles for more info",Toast.LENGTH_LONG).show();

                            }


                        } else {

                            s = "Error !";
                            Toast.makeText(getContext(),"Produit Non Authentique",Toast.LENGTH_LONG).show();

                        }
                        MainActivity m = (MainActivity) getActivity();
                        m.setQuery(s);




                    }
                });



    }
}
