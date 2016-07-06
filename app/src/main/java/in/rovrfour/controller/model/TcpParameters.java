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

package in.rovrfour.controller.model;

import java.io.Serializable;

/**
 * Serializable class for holding TCP connection parameters.
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class TcpParameters implements Serializable {

    /**
     * Default port number 8468.
     */
    public static int PORT = 8468;

    /**
     * Host IP address.
     */
    private String host;

    /**
     * Sets the host ip address.
     *
     * @param host  host ip address
     */
    public TcpParameters(String host) {
        this.host = host;
    }

    /**
     * Returns the host ip address.
     *
     * @return  host ip address
     */
    public String getHost() {
        return host;
    }
}
