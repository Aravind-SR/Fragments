package com.example.aravind_pt1748.fragmentapp4redo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment {

    public static ArrayAdapter<String> adapter;
    private listenerInter myActivity;
    private int mCurrentPosition;

    public interface listenerInter{
        public void showDetails(int index);
    }

    public static TitlesFragment newInstance(int index){
        TitlesFragment tf = new TitlesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Index",index);
        tf.setArguments(bundle);
        return tf;
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Log.v("ARAVIND_INFO","In TitlesFragment, onAttach is called with context : "+context.toString());

        try {
            myActivity=(MainActivity)context;
        }
        catch(Exception e){
            Log.e("ARAVIND_INFO",context.toString()+" must implement interface");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedState){
        super.onActivityCreated(savedState);
        Log.v("ARAVIND_INFO","In TitlesFragment, onActivityCreated is called with bundle having : ");
        if(savedState!=null){
            for(String key : savedState.keySet()){
                Log.v("ARAVIND_INFO"," "+key);
            }
        }
        else{
            Log.v("ARAVIND_INFO","null");
        }
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, Shakespeare.TITLES);
        setListAdapter(adapter);
        ListView l = getListView();
        if(savedState!=null){
            Log.v("ARAVIND_INFO"," If savedstate!=null : Index :"+mCurrentPosition);
            mCurrentPosition = savedState.getInt("Index");
            l.setSelection(mCurrentPosition);
            myActivity.showDetails(mCurrentPosition);
        }
        l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        Log.v("ARAVIND_INFO1","Calling onSaveInstanceState() in titleFragment...."+mCurrentPosition);
        outState.putInt("Index",mCurrentPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        myActivity.showDetails(position);
        mCurrentPosition=position;
    }

    @Override
    public void onDetach() {
        Log.v("ARAVIND_INFO","Calling onDetach() in titlesFragment......");
        super.onDetach();
        myActivity=null;
    }
}
