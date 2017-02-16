package restauran.lebasillic.menurestaurant.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by icanul on 2/14/17.
 */

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(Context context) {
        super(context);
        init();
    }


    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setPageTransformer(true, new VerticalPageTransformer2());

        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        /*para el efecto alpha en la transicion */
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;
        /*para el efecto alpha en la transicion */



        @Override
        public void transformPage(View view, float position) {
             /*para el efecto alpha en la transicion */
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
             /*para el efecto alpha en la transicion */

            if (position < -1) {

                view.setAlpha(0);
            } else if (position <= 1) {
                /*para el efecto alpha en la transicion
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;*/
                /*para el efecto alpha en la transicion */


                view.setTranslationX(view.getWidth() * -position);

                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);
                view.setAlpha(1);

                 /*para el efecto alpha en la transicion
                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));*/
                 /*para el efecto alpha en la transicion */
            } else {
                view.setAlpha(0);
            }
        }
    }

    private class VerticalPageTransformer2 implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);

                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapXY(ev));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    private MotionEvent swapXY(MotionEvent ev) {

        //Get display dimensions
        float displayWidth=this.getWidth();
        float displayHeight=this.getHeight();

        //Get current touch position
        float posX=ev.getX();
        float posY=ev.getY();

        //Transform (X,Y) into (Y,X) taking display dimensions into account
        float newPosX=(posY/displayHeight)*displayWidth;
        float newPosY=(1-posX/displayWidth)*displayHeight;

        //swap the x and y coords of the touch event
        ev.setLocation(newPosX, newPosY);

        return ev;
    }

}