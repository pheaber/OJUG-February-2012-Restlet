package stutter.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class StutterServerApplication extends Application {

    // this isn't needed when deploying within proper environment (Tomcat, etc), I'm just lazy
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

        // for the User list or to add a new User, use the UserList service
        router.attach(BASE + "/users", UserListServerResource.class);
        // for a specific User (to view, update, or delete), use the User service
        router.attach(BASE + "/users/{username}", UserServerResource.class);

        // for the Mumble list or to add a new Mumble, use the MumbleList service
        router.attach(BASE + "/mumbles", MumbleListServerResource.class);
        // for a specific Mumble (to view, or delete), use the Mumble service
        router.attach(BASE + "/mumbles/{mumbleId}", MumbleServerResource.class);

        return router;
    }

}
