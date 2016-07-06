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

package in.rovrfour.controller.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Create new TCP connection and hold the state information.
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class TcpConnection {

    /**
     * TCP connection socket.
     */
    private Socket socket;

    /**
     * Output buffer of the newly created socket.
     */
    private DataOutputStream outputBuffer;

    /**
     * Connection state flag.
     */
    private boolean isTcpActive = false;

    /**
     * Class constructor.
     * Creates a new socket and connects to the host.
     *
     * @param host  host ip address
     * @param port  port number
     *
     * @throws RuntimeException
     */
    public TcpConnection( String host, int port ) throws RuntimeException {

        try {
            InetAddress address = InetAddress.getByName( host );
            socket = new Socket( address, port);

            socket.setKeepAlive( true );

            outputBuffer = new DataOutputStream( socket.getOutputStream() );

            isTcpActive = true;

        } catch ( UnknownHostException ex ) {
            throw new RuntimeException( "Unknown host " + host );

        } catch ( IllegalArgumentException ex ) {
            throw new RuntimeException( ex.getMessage() );

        } catch (SocketException e) {
            throw new RuntimeException( "Could not connect to " + host );

        } catch ( IOException e ) {
            throw new RuntimeException( "Could not get socket output stream" );

        } catch ( Exception ex ) {
            throw new RuntimeException( "Could not connect due to " + ex.getClass().getSimpleName() );
        }
    }

    /**
     * Sends a message through the socket using its output buffer.
     *
     * @param content   string to be sent
     * @throws RuntimeException
     */
    public void sendMessage( String content ) throws RuntimeException {
        if ( !isActive() )
            throw new RuntimeException( "Connection not active" );

        try {
            outputBuffer.write(content.getBytes());

        } catch ( Exception ex ) {
            throw new RuntimeException( ex.getMessage() );
        }
    }

    /**
     * Returns the connection state.
     *
     * @return  true if connection active, else false
     */
    public boolean isActive () {
        return isTcpActive;
    }

    /**
     * Closes the output buffer and socket
     * and sets the connection state flag to false .
     */
    public void close() {
        if ( !isActive() ) return;

        try {
            outputBuffer.close();
            socket.close();

            isTcpActive = false;

        } catch ( IOException ex ) {

            isTcpActive = false;
        }
    }
}
