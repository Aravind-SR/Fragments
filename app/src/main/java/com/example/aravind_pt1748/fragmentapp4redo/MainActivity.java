package com.example.aravind_pt1748.fragmentapp4redo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements TitlesFragment.listenerInter{

    private int mIndex;

    public boolean isMultiPane(){
        return getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitlesFragment tf;
        if(savedInstanceState==null) {
            tf = new TitlesFragment();
        }
        else{
            tf = TitlesFragment.newInstance(savedInstanceState.getInt("Index"));
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.titles_fragment,tf);
        ft.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Index",mIndex);
    }

    public void showDetails(int index){
        Log.v("ARAVIND_INFO","in MainActivity showDetails("+index+")");
        mIndex=index;
        DetailsFragment details;
        if(isMultiPane()){
            details = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if(details==null||details.getShownIndex()!=index){
                Log.v("ARAVIND_INFO","in MainActivity showDetails().....calling fragmentTransaction");
                details = DetailsFragment.newInstance(index);
                details.setEnterTransition(new Slide(Gravity.RIGHT));
                details.setEnterTransition(new Fade());
                details.setAllowEnterTransitionOverlap(false);
                details.setExitTransition(new Slide(Gravity.LEFT));
                getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.fragment_container,details)
                        .addToBackStack(null)
                        .commit();
            }
        }
        else{
            /*
            Intent intent = new Intent();
            intent.setClass(this, DetailsActivity.class);
            intent.putExtra("Index", index);
            startActivity(intent);
            */
            details = DetailsFragment.newInstance(index);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.titles_fragment,details);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView search = (SearchView)item.getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                for(int i=0;i<7;i++){
                    if(Shakespeare.TITLES[i].contains(query)){
                        showDetails(i);
                        break;
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

}
