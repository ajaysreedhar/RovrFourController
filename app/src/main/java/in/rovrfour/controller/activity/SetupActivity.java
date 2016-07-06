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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.rovrfour.controller.R;
import in.rovrfour.controller.model.TcpParameters;

/**
 * Shows welcome message and prompts for user inputs.
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class SetupActivity extends AppCompatActivity {

    /**
     * Sets the content view.
     *
     * @param savedInstanceState    saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }

    /**
     * Builds TCP parameter object and starts controller activity.
     *
     * @param view  widget
     */
    public void createTcpConnection( View view ) {
        String ipAddress;

        try {
            TextView textFieldIp = (TextView)findViewById( R.id.textFieldIp );
            ipAddress = textFieldIp.getText().toString();

            if ( ipAddress == null || ipAddress.length() <= 0 ) throw new NullPointerException( "Please enter an IP address" );

        } catch ( NullPointerException ex ) {
            Toast.makeText( this, ex.getMessage(), Toast.LENGTH_SHORT ).show();
            return;
        }

        TcpParameters tcpParameters = new TcpParameters( ipAddress );

        Intent controllerIntent = new Intent( this, ActionsActivity.class );
        controllerIntent.putExtra( "tcpParameters", tcpParameters );
        startActivity( controllerIntent );
    }
}
