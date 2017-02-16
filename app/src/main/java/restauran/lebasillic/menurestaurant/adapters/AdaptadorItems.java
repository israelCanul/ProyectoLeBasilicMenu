package restauran.lebasillic.menurestaurant.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import restauran.lebasillic.menurestaurant.R;
import restauran.lebasillic.menurestaurant.objects.Item;

/**
 * Created by icanul on 2/16/17.
 */

public class AdaptadorItems extends RecyclerView.Adapter<AdaptadorItems.ItemsViewHolder> {

    public ArrayList<Item> datos;
    public Context context;

    public AdaptadorItems(ArrayList<Item> datos,Context context){
        this.datos = datos;
        this.context = context;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_item,parent,false);
        ItemsViewHolder ivh = new ItemsViewHolder(itemView,context);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        Item item = datos.get(position);
        holder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ItemsViewHolder
            extends RecyclerView.ViewHolder {
        private Context context;
        private TextView txtTitulo;
        private TextView txtSubtitulo;
        private TextView price;
        private ImageView image;

        public ItemsViewHolder(View itemView,Context context) {
            super(itemView);
            txtTitulo = (TextView)itemView.findViewById(R.id.item_titulo);
            txtSubtitulo = (TextView)itemView.findViewById(R.id.item_subtitulo);
            price = (TextView)itemView.findViewById(R.id.item_price);
            this.context = context;
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/times_new_roman.ttf");
            Typeface custom_font2 = Typeface.createFromAsset(context.getAssets(), "fonts/times_new_roman_bold.ttf");
            txtTitulo.setTypeface(custom_font);
            txtSubtitulo.setTypeface(custom_font2);
            price.setTypeface(custom_font2);
        }
        public ItemsViewHolder(View itemView,boolean img,Context contexto) {
            super(itemView);
            context = contexto;
            txtTitulo = (TextView)itemView.findViewById(R.id.item_titulo2);
            txtSubtitulo = (TextView)itemView.findViewById(R.id.item_subtitulo2);
            price = (TextView)itemView.findViewById(R.id.item_price2);
        }
        public void bindTitular(Item t) {
            //Drawable drawable = context.getDrawable(t.getImage());
            txtTitulo.setText(t.getTitulo());
            txtSubtitulo.setText(t.getSubtitulo());
            price.setText(t.getPrice());
            //image.setImageDrawable(new DrawableWrapper(drawable));
        }
    }


}


