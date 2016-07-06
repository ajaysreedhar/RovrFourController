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

package in.rovrfour.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.rovrfour.controller.R;
import in.rovrfour.controller.model.TcpParameters;
import in.rovrfour.controller.listener.LongPressListener;
import in.rovrfour.controller.listener.RepeatedActionListener;
import in.rovrfour.controller.network.TcpConnection;
import in.rovrfour.controller.task.TcpConnectionTask;

/**
 * Activity to control movements of the rover.
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class ActionsActivity extends AppCompatActivity {

    /**
     * Tcp connection object.
     */
    TcpConnection connection;

    /**
     * Creates TCP connection with the rover,
     * sets the content view and binds event listeners.
     *
     * Exits with an error message toast if TCP connection fails.
     *
     * @param savedInstanceState    saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TcpParameters tcpParameters = (TcpParameters) getIntent().getSerializableExtra( "tcpParameters" );

        try {
            TcpConnectionTask tcpConnectionTask = new TcpConnectionTask();
            connection = tcpConnectionTask.execute( tcpParameters ).get();

            if ( connection == null )
                throw new Exception( tcpConnectionTask.getMessage() );

            setContentView( R.layout.activity_actions );

            RepeatedActionListener listener = new RepeatedActionListener( 500, 100, new LongPressListener( connection, "mT" ) );

            findViewById( R.id.buttonForward ).setOnTouchListener(listener);
            findViewById( R.id.buttonLeft ).setOnTouchListener(listener);
            findViewById( R.id.buttonRight ).setOnTouchListener(listener);
            findViewById( R.id.buttonBackward ).setOnTouchListener(listener);

            ((TextView) findViewById( R.id.textViewIpInfo )).setText( "Connected to " + tcpParameters.getHost() );

        } catch ( Exception ex ) {
            Toast.makeText( this, ex.getMessage(), Toast.LENGTH_SHORT ).show();
            finish();
        }
    }

    /**
     * Closes the connections
     * and disconnects from the rover.
     *
     * @param view  widget
     */
    public void disconnect(View view) {
        if ( connection == null )
            return;

        connection.close();
        this.finish();
    }

    /**
     * Closes the connections and destroys the view.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if ( connection != null ) {
            connection.close();
        }
    }
}
