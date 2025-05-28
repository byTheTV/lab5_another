package collectionManagers;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import models.Coordinates;
import models.Flat;
import models.Furnish;
import models.House;
import models.Transport;
import models.View;

public class XMLReader {
    private final SimpleDateFormat dateFormat;

    public XMLReader() {
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    public List<Flat> read(String filePath) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));
        List<Flat> flats = new ArrayList<>();

        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("flat")) {
                flats.add(parseFlat(reader));
            }
        }

        reader.close();
        return flats;
    }

    private Flat parseFlat(XMLStreamReader reader) throws XMLStreamException, ParseException {
        Flat flat = new Flat();
        Coordinates coordinates = null;
        House house = null;

        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("flat")) {
                break;
            }
            if (event == XMLStreamConstants.START_ELEMENT) {
                String elementName = reader.getLocalName();
                reader.next();
                String value = reader.getText().trim();

                switch (elementName) {
                    case "id":
                        flat.setId(Long.parseLong(value));
                        break;
                    case "name":
                        flat.setName(value);
                        break;
                    case "coordinates":
                        coordinates = parseCoordinates(reader);
                        flat.setCoordinates(coordinates);
                        break;
                    case "area":
                        flat.setArea(Float.parseFloat(value));
                        break;
                    case "numberOfRooms":
                        flat.setNumberOfRooms(Long.parseLong(value));
                        break;
                    case "furnish":
                        flat.setFurnish(Furnish.valueOf(value));
                        break;
                    case "view":
                        flat.setView(View.valueOf(value));
                        break;
                    case "transport":
                        flat.setTransport(Transport.valueOf(value));
                        break;
                    case "house":
                        house = parseHouse(reader);
                        flat.setHouse(house);
                        break;
                    case "creationDate":
                        flat.setCreationDate(dateFormat.parse(value));
                        break;
                }
            }
        }

        return flat;
    }

    private Coordinates parseCoordinates(XMLStreamReader reader) throws XMLStreamException {
        long x = 0;
        long y = 0;
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("coordinates")) {
                break;
            }
            if (event == XMLStreamConstants.START_ELEMENT) {
                String elementName = reader.getLocalName();
                reader.next();
                String value = reader.getText().trim();

                switch (elementName) {
                    case "x":
                        x = Long.parseLong(value);
                        break;
                    case "y":
                        y = Long.parseLong(value);
                        break;
                }
            }
        }
        return new Coordinates(x, y);
    }

    private House parseHouse(XMLStreamReader reader) throws XMLStreamException {
        String name = "";
        Integer year = null;
        long numberOfFlatsOnFloor = 0;
        Integer numberOfLifts = null;
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("house")) {
                break;
            }
            if (event == XMLStreamConstants.START_ELEMENT) {
                String elementName = reader.getLocalName();
                reader.next();
                String value = reader.getText().trim();

                switch (elementName) {
                    case "name":
                        name = value;
                        break;
                    case "year":
                        year = Integer.parseInt(value);
                        break;
                    case "numberOfFlatsOnFloor":
                        numberOfFlatsOnFloor = Long.parseLong(value);
                        break;
                    case "numberOfLifts":
                        numberOfLifts = Integer.parseInt(value);
                        break;
                }
            }
        }
        return new House(name, year, numberOfFlatsOnFloor, numberOfLifts);
    }
} 