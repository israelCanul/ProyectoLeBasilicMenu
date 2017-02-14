package restauran.lebasillic.menurestaurant.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import restauran.lebasillic.menurestaurant.R;


/**
 * Created by icanul on 2/14/17.
 */

public class ScreenSlidePageFragment extends Fragment {


    private String TAG = "ScreenSlidePageFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int myInt= 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myInt = bundle.getInt("color",0);
            Log.e(TAG, String.valueOf(myInt));
        }

        ViewGroup rootView = (ViewGroup) inflater.inflate( R.layout.fragment_screen_slide_page, container, false);
        rootView.setBackgroundColor(myInt);

        return rootView;
    }
}


