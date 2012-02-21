package stutter.server;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class RootServerResource extends ServerResource {

    @Get
    public String welcome() {
        return "hello, this should probably have real stuff in it";
    }

}
