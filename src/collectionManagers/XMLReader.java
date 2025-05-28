package collectionManagers;

import models.Coordinates;
import models.Flat;
import models.Furnish;
import models.House;
import models.Transport;
import models.View;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLReader {
    private final String filePath;
    private final SimpleDateFormat dateFormat;

    public XMLReader(String filePath) {
        this.filePath = filePath;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    public List<Flat> read() throws FileNotFoundException, XMLStreamException {
        List<Flat> flats = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));

        Flat currentFlat = null;
        String currentElement = null;
        String currentName = null;
        Long currentX = null;
        Long currentY = null;
        Float currentArea = null;
        Long currentNumberOfRooms = null;
        Furnish currentFurnish = null;
        View currentView = null;
        Transport currentTransport = null;
        String houseName = null;
        Integer houseYear = null;
        Long houseNumberOfFlatsOnFloor = null;
        Integer houseNumberOfLifts = null;
        Date currentCreationDate = null;

        while (reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    currentElement = reader.getLocalName();
                    break;

                case XMLStreamConstants.CHARACTERS:
                    if (currentElement != null) {
                        String text = reader.getText().trim();
                        if (!text.isEmpty()) {
                            switch (currentElement) {
                                case "id":
                                    if (currentFlat == null) {
                                        // We'll create the Flat object when we have all required fields
                                        currentFlat = new Flat("", new Coordinates(0, 0L), 0, 0, null, null, null, null);
                                        currentFlat.setId(Long.parseLong(text));
                                    } else {
                                        currentFlat.setId(Long.parseLong(text));
                                    }
                                    break;
                                case "name":
                                    currentName = text;
                                    break;
                                case "x":
                                    currentX = Long.parseLong(text);
                                    break;
                                case "y":
                                    currentY = Long.parseLong(text);
                                    break;
                                case "area":
                                    currentArea = Float.parseFloat(text);
                                    break;
                                case "numberOfRooms":
                                    currentNumberOfRooms = Long.parseLong(text);
                                    break;
                                case "furnish":
                                    currentFurnish = Furnish.valueOf(text);
                                    break;
                                case "view":
                                    currentView = View.valueOf(text);
                                    break;
                                case "transport":
                                    currentTransport = Transport.valueOf(text);
                                    break;
                                case "year":
                                    houseYear = Integer.parseInt(text);
                                    break;
                                case "numberOfFlatsOnFloor":
                                    houseNumberOfFlatsOnFloor = Long.parseLong(text);
                                    break;
                                case "numberOfLifts":
                                    houseNumberOfLifts = Integer.parseInt(text);
                                    break;
                                case "creationDate":
                                    try {
                                        currentCreationDate = dateFormat.parse(text);
                                    } catch (ParseException e) {
                                        throw new XMLStreamException("Invalid date format: " + text, e);
                                    }
                                    break;
                            }
                        }
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElement = reader.getLocalName();
                    switch (endElement) {
                        case "coordinates":
                            if (currentX != null && currentY != null) {
                                Coordinates coordinates = new Coordinates(currentX, currentY);
                                currentFlat.setCoordinates(coordinates);
                                currentX = null;
                                currentY = null;
                            }
                            break;
                        case "house":
                            if (houseName != null && houseYear != null && houseNumberOfFlatsOnFloor != null && houseNumberOfLifts != null) {
                                House house = new House(houseName, houseYear, houseNumberOfFlatsOnFloor, houseNumberOfLifts);
                                currentFlat.setHouse(house);
                                houseName = null;
                                houseYear = null;
                                houseNumberOfFlatsOnFloor = null;
                                houseNumberOfLifts = null;
                            }
                            break;
                        case "flat":
                            if (currentFlat != null) {
                                if (currentName != null) {
                                    currentFlat.setName(currentName);
                                }
                                if (currentArea != null) {
                                    currentFlat.setArea(currentArea);
                                }
                                if (currentNumberOfRooms != null) {
                                    currentFlat.setNumberOfRooms(currentNumberOfRooms);
                                }
                                if (currentFurnish != null) {
                                    currentFlat.setFurnish(currentFurnish);
                                }
                                if (currentView != null) {
                                    currentFlat.setView(currentView);
                                }
                                if (currentTransport != null) {
                                    currentFlat.setTransport(currentTransport);
                                }
                                if (currentCreationDate != null) {
                                    currentFlat.setCreationDate(currentCreationDate);
                                }
                                flats.add(currentFlat);
                                currentFlat = null;
                                currentName = null;
                                currentArea = null;
                                currentNumberOfRooms = null;
                                currentFurnish = null;
                                currentView = null;
                                currentTransport = null;
                                currentCreationDate = null;
                            }
                            break;
                    }
                    break;
            }
        }
        reader.close();
        return flats;
    }
} 