package stutter;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;

import stutter.server.StutterServerApplication;

public class StutterServerComponent extends Component {

    public static void main(String[] args) throws Exception {
        new StutterServerComponent().start();
    }

    public StutterServerComponent() {
        // Adds a HTTP server connector
        Server server = getServers().add(Protocol.HTTP, 8111);
        server.getContext().getParameters().set("tracing", "true");
        getDefaultHost().attachDefault(new StutterServerApplication());
    }

}