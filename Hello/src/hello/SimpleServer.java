package hello;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;

/**
 * Example written by Brian Sletten.
 * 
 * http://www.javaworld.com/javaworld/jw-12-2008/jw-12-rest-series-2.html
 */
public class SimpleServer {
    public static void main(String[]args) throws Exception {
        Restlet restlet = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
                response.setEntity("Hello, Java RESTafarians!", MediaType.TEXT_PLAIN);
            }
        };

        // Avoid conflicts with other Java containers listening on 8080!
        new Server(Protocol.HTTP, 8182, restlet).start();
    }
}