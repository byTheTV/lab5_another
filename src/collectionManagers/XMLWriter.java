package collectionManagers;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import models.Coordinates;
import models.Flat;
import models.House;

public class XMLWriter {
    private final SimpleDateFormat dateFormat;

    public XMLWriter() {
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    public void write(List<Flat> flats, String filePath) throws Exception {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(filePath));

        writer.writeStartDocument();
        writer.writeStartElement("flats");

        for (Flat flat : flats) {
            writeFlat(writer, flat);
        }

        writer.writeEndElement();
        writer.writeEndDocument();
        writer.close();
    }

    private void writeFlat(XMLStreamWriter writer, Flat flat) throws XMLStreamException {
        writer.writeStartElement("flat");

        writer.writeStartElement("id");
        writer.writeCharacters(String.valueOf(flat.getId()));
        writer.writeEndElement();

        writer.writeStartElement("name");
        writer.writeCharacters(flat.getName());
        writer.writeEndElement();

        writeCoordinates(writer, flat.getCoordinates());

        writer.writeStartElement("area");
        writer.writeCharacters(String.valueOf(flat.getArea()));
        writer.writeEndElement();

        writer.writeStartElement("numberOfRooms");
        writer.writeCharacters(String.valueOf(flat.getNumberOfRooms()));
        writer.writeEndElement();

        writer.writeStartElement("furnish");
        writer.writeCharacters(String.valueOf(flat.getFurnish()));
        writer.writeEndElement();

        writer.writeStartElement("view");
        writer.writeCharacters(String.valueOf(flat.getView()));
        writer.writeEndElement();

        writer.writeStartElement("transport");
        writer.writeCharacters(String.valueOf(flat.getTransport()));
        writer.writeEndElement();

        writeHouse(writer, flat.getHouse());

        writer.writeStartElement("creationDate");
        writer.writeCharacters(dateFormat.format(flat.getCreationDate()));
        writer.writeEndElement();

        writer.writeEndElement();
    }

    private void writeCoordinates(XMLStreamWriter writer, Coordinates coordinates) throws XMLStreamException {
        writer.writeStartElement("coordinates");

        writer.writeStartElement("x");
        writer.writeCharacters(String.valueOf(coordinates.getX()));
        writer.writeEndElement();

        writer.writeStartElement("y");
        writer.writeCharacters(String.valueOf(coordinates.getY()));
        writer.writeEndElement();

        writer.writeEndElement();
    }

    private void writeHouse(XMLStreamWriter writer, House house) throws XMLStreamException {
        writer.writeStartElement("house");

        writer.writeStartElement("year");
        writer.writeCharacters(String.valueOf(house.getYear()));
        writer.writeEndElement();

        writer.writeStartElement("numberOfFlatsOnFloor");
        writer.writeCharacters(String.valueOf(house.getNumberOfFlatsOnFloor()));
        writer.writeEndElement();

        writer.writeStartElement("numberOfLifts");
        writer.writeCharacters(String.valueOf(house.getNumberOfLifts()));
        writer.writeEndElement();

        writer.writeEndElement();
    }
} 