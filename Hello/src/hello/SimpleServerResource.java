package hello;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Example from Restlet Introduction.
 * 
 * http://wiki.restlet.org/docs_2.0/13-restlet/21-restlet/318-restlet/319-restlet.html
 */
public class SimpleServerResource extends ServerResource {

    public static void main(String[] args) throws Exception {
        // Create the HTTP server and listen on port 8182
        new Server(Protocol.HTTP, 8182, SimpleServerResource.class).start();
    }

    @Override
    @Get
    public String toString() {
        return "hello, world";
    }

}