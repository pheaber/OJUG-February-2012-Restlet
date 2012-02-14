package hello;

import java.io.IOException;

import org.restlet.Client;
import org.restlet.data.Protocol;

/**
 * Based on example written by Brian Sletten.
 * 
 * http://www.javaworld.com/javaworld/jw-12-2008/jw-12-rest-series-2.html
 */
public class SimpleClient {
    public static void main(String [] args) throws IOException {
        String uri = (args.length > 0) ? args[0] : "http://localhost:8182" ;
        Client client = new Client(Protocol.HTTP);
        client.get(uri).getEntity().write(System.out);
    }
}