package restauran.lebasillic.menurestaurant.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import restauran.lebasillic.menurestaurant.R;
import restauran.lebasillic.menurestaurant.adapters.AdaptadorItems;
import restauran.lebasillic.menurestaurant.objects.Item;


/**
 * Created by icanul on 2/14/17.
 */

public class ScreenSlidePageFragment extends Fragment {


    private String TAG = "ScreenSlidePageFragment";
    FirebaseDatabase database;
    DatabaseReference myRef;
    int myInt;
    String titulo = "";
    TextView texto;
    private  ArrayList<Item> datos;
    private RecyclerView recView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        datos =new ArrayList<Item>();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myInt = bundle.getInt("item",0);
            Log.e(TAG, String.valueOf(myInt));
        }
        ViewGroup rootView = (ViewGroup) inflater.inflate( R.layout.fragment_screen_slide_page, container, false);

        texto= (TextView) rootView.findViewById(R.id.text_categoria);
        texto.setText(String.valueOf(myInt));
        recView = (RecyclerView) rootView.findViewById(R.id.items_recView);
        recView.setHasFixedSize(true);
        recView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                // true: consume touch event
                // false: dispatch touch event
                return true;
            }
        });
        TextView front = (TextView) rootView.findViewById(R.id.textView3);






        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("cat");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int cat =0;
                datos.clear();
                for (DataSnapshot item: dataSnapshot.getChildren()) {
                    if(cat == myInt){
                        titulo = String.valueOf(item.child("name").getValue());
                        texto.setText(String.valueOf(titulo));
                        for (DataSnapshot platillo: item.child("items").getChildren()) {
                            System.out.println(platillo.child("title").getValue());
                            datos.add(new Item(String.valueOf(platillo.child("title").getValue()),String.valueOf(platillo.child("subtitle").getValue()), String.valueOf(platillo.child("price").getValue())));
                        }
                    }
                    cat++;
                }
                AdaptadorItems adaptador = new AdaptadorItems(datos,getContext());
                recView.setAdapter(adaptador);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        recView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return rootView;
    }
}


