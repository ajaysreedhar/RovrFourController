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

import android.view.View;
import android.view.View.OnClickListener;

import in.rovrfour.controller.network.TcpConnection;

/**
 * LongPressListener
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class LongPressListener implements OnClickListener {

    /**
     * Tcp connection state
     */
    public TcpConnection connection;

    /**
     * Message to be sent
     */
    public String outputMessage;

    /**
     * Class constructor
     *
     * @param connection        tcp connection
     * @param outputMessage     message to be sent
     */
    public LongPressListener( TcpConnection connection, String outputMessage ) {
        this.connection = connection;
        this.outputMessage = outputMessage;
    }

    /**
     * Send the message
     *
     * @param v current view
     */
    @Override
    public void onClick(View v) {
        try {
            connection.sendMessage( v.getTag().toString() );

        } catch ( Exception ex ) {
            return;
        }
    }
}
