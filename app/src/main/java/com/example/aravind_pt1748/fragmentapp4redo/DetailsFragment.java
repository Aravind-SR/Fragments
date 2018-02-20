package com.example.aravind_pt1748.fragmentapp4redo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private int index;

    public DetailsFragment(){
    }

    public static DetailsFragment newInstance(int index){
        DetailsFragment f = new DetailsFragment();
        Bundle arg = new Bundle();
        arg.putInt("Index",index);
        f.setArguments(arg);
        return f;
    }


    public int getShownIndex(){
        return index;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Index",index);
    }

    @Override
    public void onCreate(Bundle myBundle) {
        super.onCreate(myBundle);
        Log.v("ARAVIND_INFO", "Calling onCreate() in DetailsFragment with bundle having : ");
        if (myBundle != null) {
            for (String key : myBundle.keySet()) {
                Log.v("ARAVIND_INFO", " " + key);
            }
        }
        else {
            Log.v("ARAVIND_INFO", "null");
        }

        index = getArguments().getInt("Index");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.details_layout,container,false);
        TextView text1 = (TextView) v.findViewById(R.id.textView);
        text1.setText(Shakespeare.DIALOGUE[index] );
        return v;
    }

}
