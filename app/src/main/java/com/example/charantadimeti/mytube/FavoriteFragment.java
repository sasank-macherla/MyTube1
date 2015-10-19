package com.example.charantadimeti.mytube;

//import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
/**
 * Created by sasankmacherla on 10/17/15.
 */
public class FavoriteFragment extends Fragment{

    public static final String NAME = "FAVORITE";


//    public FavoriteFragment() {
//        // Required empty public constructor
//    }

    public static FavoriteFragment newInstance(int tab) {
        Bundle args = new Bundle();
        args.putInt(NAME, tab);
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        favoriteFragment.setArguments(args);
        return favoriteFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favorite, container, false);
        return view;
    }
}
