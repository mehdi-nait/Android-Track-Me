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

import org.w3c.dom.Text;

public class Articles extends Fragment {




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity main = (MainActivity)getActivity();
        String s = main.query;
        View inf = inflater.inflate(R.layout.articles, container, false);
        TextView tv = (TextView) inf.findViewById(R.id.articles_text);
        tv.setText(s);
        return inf;
    }
}
