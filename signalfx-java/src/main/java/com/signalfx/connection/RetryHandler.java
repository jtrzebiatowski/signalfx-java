package com.signalfx.connection;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;

/**
 * Compared to the {@link DefaultHttpRequestRetryHandler} we allow retry on {@link
 * javax.net.ssl.SSLException}, because it gets thrown when we try to send data points over a
 * connection that our server has already closed. It is still unknown how exactly our server closes
 * "stale" connections in such a way that http client is unable to detect this.
 */
class RetryHandler extends DefaultHttpRequestRetryHandler {

  public RetryHandler() {
    super(3, true, Arrays.asList(
        InterruptedIOException.class,
        UnknownHostException.class,
        ConnectException.class));
  }
}
