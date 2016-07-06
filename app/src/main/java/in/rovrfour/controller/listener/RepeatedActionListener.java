/**
 * MIT License
 *
 * Copyright (c) 2016 Ajay Sreedhar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package in.rovrfour.controller.listener;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;

/**
 * Listen long press events
 *
 * @since 0.1.0
 * @version 0.1.0
 */
public class RepeatedActionListener implements OnTouchListener {

    /**
     * Event handler.
     */
    private Handler handler = new Handler();

    /**
     * Initial delay.
     */
    private int initInterval;

    /**
     * Normal delay.
     */
    private int normalInterval;

    /**
     * Widget.
     */
    private View downView;

    /**
     * Click listener.
     */
    private final OnClickListener clickListener;

    /**
     * Repeats action with a delay of normalInterval.
     */
    private Runnable handlerRunnable = new Runnable() {

        @Override
        public void run() {
            handler.postDelayed( this, normalInterval );
            clickListener.onClick( downView );
        }

    };

    /**
     * Repeats the action on long press.
     *
     * @param view      widget
     * @param event     event fired
     *
     * @return          action state
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                handler.removeCallbacks(handlerRunnable);
                handler.postDelayed( handlerRunnable, initInterval );
                downView = view;
                downView.setPressed(true);
                clickListener.onClick( view );
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                handler.removeCallbacks(handlerRunnable);
                downView.setPressed( false );
                return true;
        }

        return false;
    }

    /**
     * Sets the initial delay, normal delay and listener.
     *
     * @param initInterval      initial interval
     * @param normalInterval    normal interval
     * @param listener          click event listener
     */
    public RepeatedActionListener( int initInterval, int normalInterval, OnClickListener listener ) {

        if ( listener == null )
            throw new IllegalArgumentException( "Null runnable received" );

        if ( initInterval < 0 || normalInterval < 0 )
            throw new IllegalArgumentException( "Invalid intervals" );

        this.initInterval = initInterval;
        this.normalInterval = normalInterval;
        this.clickListener = listener;
    }
}
