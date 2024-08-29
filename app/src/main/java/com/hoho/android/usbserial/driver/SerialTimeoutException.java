package com.hoho.android.usbserial.driver;

import java.io.*;

/**
 * Signals that a timeout has occurred on serial write.
 * Similar to SocketTimeoutException.
 * <p>
 * {@see InterruptedIOException#bytesTransferred} may contain bytes transferred
 */
public class SerialTimeoutException extends InterruptedIOException {
    public SerialTimeoutException(String s, int bytesTransferred) {
        super(s);
        this.bytesTransferred = bytesTransferred;
    }
}
