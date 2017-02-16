package restauran.lebasillic.menurestaurant.objects;

/**
 * Created by icanul on 2/16/17.
 */

public class Item {
    private String titulo;
    private String subtitulo;
    private  String price;
    private  Integer image = 0;

    public Item(String tit, String sub,String price){
        titulo = tit;
        subtitulo = sub;
        this.price = price;
    }
    public Item(String tit, String sub,String price,int img){
        titulo = tit;
        subtitulo = sub;
        this.price = price;
        image = img;
    }
    public String getTitulo(){
        return titulo;
    }

    public String getSubtitulo(){
        return subtitulo;
    }
    public String getPrice(){
        return price;
    }
    public Integer getImage(){
        return image;
    }
}
