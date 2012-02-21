package stutter.server;

import java.io.IOException;
import java.util.List;

import org.restlet.data.Reference;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import stutter.Mumble;
import stutter.common.MumbleListManager;

public class MumbleListServerResource extends ServerResource implements MumbleListManager {

    @Override
    public List<Mumble> listAll() {
        return FakeStorage.getInstance().listMumbles();
    }

    @Override
    public Mumble newMumble(Mumble newMumble) {
        Mumble madeMumble = FakeStorage.getInstance().newMumble(newMumble.getUsername(), newMumble.getMessage());
        Reference reference = new Reference(getReference());
        reference.addSegment(madeMumble.getId());
        madeMumble.setReference(reference.getTargetRef().toString());
        return madeMumble;
    }

    @Post("xml")
    public void newMumbleXML(Representation xmlMumble) {
        DomRepresentation mumbleRepresentation = new DomRepresentation(xmlMumble);

        Document doc;
        try {
            doc = mumbleRepresentation.getDocument();

            Element rootMumbleElement = doc.getDocumentElement();
            String message = rootMumbleElement.getTextContent();
            String username = rootMumbleElement.getAttribute("username");

            FakeStorage.getInstance().newMumble(username, message);

        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }

}