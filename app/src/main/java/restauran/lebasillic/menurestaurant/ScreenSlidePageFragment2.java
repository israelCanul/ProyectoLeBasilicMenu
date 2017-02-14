package restauran.lebasillic.menurestaurant;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by icanul on 2/14/17.
 */

public class ScreenSlidePageFragment2 extends Fragment {


    private String TAG = "ScreenSlidePageFragment2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int myInt= 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myInt = bundle.getInt("color",0);

        }

        ViewGroup rootView = (ViewGroup) inflater.inflate( R.layout.fragment_screen_slide_page2, container, false);
        rootView.setBackgroundColor(myInt);

        return rootView;
    }
}
