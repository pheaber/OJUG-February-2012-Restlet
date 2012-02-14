package hello;

import java.io.IOException;

import org.restlet.resource.ClientResource;

/**
 * Example from Restlet Introduction.
 * 
 * http://wiki.restlet.org/docs_2.0/13-restlet/21-restlet/318-restlet/320-restlet.html
 */
public class SimpleClient {
    public static void main(String [] args) throws IOException {
        // Outputting the content of a Web page
        new ClientResource("http://localhost:8182/").get().write(System.out);
    }
}