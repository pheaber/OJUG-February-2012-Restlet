package stutter.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class StutterServerApplication extends Application {

    // TODO: can this go away?
    private static final String BASE = "http://localhost:8182";

    public static void main(String[] args) throws Exception {
        Server mailServer = new Server(Protocol.HTTP, 8182);
        mailServer.setNext(new StutterServerApplication());
        mailServer.start();
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        router.attach(BASE + "/", RootServerResource.class);

        router.attach(BASE + "/users", UserListServerResource.class);
        router.attach(BASE + "/users/{username}", UserServerResource.class);

        router.attach(BASE + "/mumbles", MumbleListServerResource.class);
        router.attach(BASE + "/mumbles/{mumbleId}", MumbleServerResource.class);

        return router;
    }

}
