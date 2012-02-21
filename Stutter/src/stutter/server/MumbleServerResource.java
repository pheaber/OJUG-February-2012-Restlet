package stutter.server;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import stutter.Mumble;
import stutter.common.MumbleManager;

public class MumbleServerResource extends ServerResource implements MumbleManager {

    private String mumbleId;

    /**
     * Retrieve the identifier based on the URI path variable "mumbleId" declared in the URI template attached to the
     * application router.
     */
    @Override
    protected void doInit() throws ResourceException {
        this.mumbleId = (String) getRequestAttributes().get("mumbleId");
    }

    @Get("xml")
    public Representation getMumbleXML() {
        Mumble mumble = FakeStorage.getInstance().getMumble(this.mumbleId);

        DomRepresentation mumbleXML;

        try {
            mumbleXML = new DomRepresentation();
            mumbleXML.setIndenting(true);

            // make XML of the Mumble like this:
            // <mumble username="user" id="aa214" time="yyyy-MM-ddTHH:mm:ss">message goes here</mumble>
            Document doc = mumbleXML.getDocument();
            Element mumbleRootElement = doc.createElement("mumble");
            doc.appendChild(mumbleRootElement);
            mumbleRootElement.setTextContent(mumble.getMessage());
            mumbleRootElement.setAttribute("username", mumble.getUsername());
            mumbleRootElement.setAttribute("id", mumble.getId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            mumbleRootElement.setAttribute("time", dateFormat.format(mumble.getTime()));

        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                    "Error building XML for Mumble: " + this.mumbleId, e);
        }

        return mumbleXML;
    }

    @Override
    public Mumble getMumble() {
        return FakeStorage.getInstance().getMumble(this.mumbleId);
    }

    @Override
    public void removeMumble() {
        FakeStorage.getInstance().removeMumble(this.mumbleId);
    }

}