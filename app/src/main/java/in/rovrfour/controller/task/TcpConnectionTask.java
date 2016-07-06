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

package in.rovrfour.controller.task;

import android.os.AsyncTask;

import in.rovrfour.controller.model.TcpParameters;
import in.rovrfour.controller.network.TcpConnection;

/**
 * Asynchronous task to create a TCP connection
 * with the rover.
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class TcpConnectionTask extends AsyncTask<TcpParameters, Integer, TcpConnection> {

    /**
     * Exception message.
     */
    private String message = "";

    /**
     * Creates a TCP connection with the rover.
     *
     * @param params    connection parameters
     * @return          newly created connection
     */
    @Override
    protected TcpConnection doInBackground( TcpParameters... params ) {
        TcpParameters tcpParameters = params[0];
        TcpConnection connection;

        try {
            connection = new TcpConnection( tcpParameters.getHost(), TcpParameters.PORT );

        } catch ( RuntimeException ex ) {
            message = ex.getMessage();
            return null;
        }

        return connection;
    }

    /**
     * Returns the exception message if any.
     *
     * @return  exception message
     */
    public String getMessage() {
        return message;
    }
}
