package stutter.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class StutterServerApplication extends Application {

    public static void main(String[] args) throws Exception {
        Server mailServer = new Server(Protocol.HTTP, 8182);
        mailServer.setNext(new StutterServerApplication());
        mailServer.start();
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("http://localhost:8182/accounts/{accountId}/mails/{mailId}", StutterServerResource.class);
        return router;
    }
}
