package models;

import java.util.Date;

public class Flat {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float area; //Максимальное значение поля: 945, Значение поля должно быть больше 0
    private long numberOfRooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле может быть null
    private View view; //Поле не может быть null
    private Transport transport; //Поле не может быть null
    private House house; //Поле не может быть null

    public Flat() {
        this.creationDate = new Date();
    }

    public Flat(String name, Coordinates coordinates, float area, long numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.id = null; // Will be generated automatically
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = new Date(); // Generated automatically
        setArea(area);
        setNumberOfRooms(numberOfRooms);
        this.furnish = furnish;
        setView(view);
        setTransport(transport);
        setHouse(house);
    }

    // Constructor for creating flat with id (e.g., when loading from file)
    public Flat(Long id, String name, Coordinates coordinates, Date creationDate, float area, long numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.id = id;
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = creationDate;
        setArea(area);
        setNumberOfRooms(numberOfRooms);
        this.furnish = furnish;
        setView(view);
        setTransport(transport);
        setHouse(house);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("Creation date cannot be null");
        }
        this.creationDate = creationDate;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        if (area <= 0 || area > 945) {
            throw new IllegalArgumentException("Area must be greater than 0 and less than or equal to 945");
        }
        this.area = area;
    }

    public long getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(long numberOfRooms) {
        if (numberOfRooms <= 0) {
            throw new IllegalArgumentException("Number of rooms must be greater than 0");
        }
        this.numberOfRooms = numberOfRooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View cannot be null");
        }
        this.view = view;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        if (transport == null) {
            throw new IllegalArgumentException("Transport cannot be null");
        }
        this.transport = transport;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        if (house == null) {
            throw new IllegalArgumentException("House cannot be null");
        }
        this.house = house;
    }

    @Override
    public String toString() {
        return "Flat{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", coordinates=" + coordinates +
               ", creationDate=" + creationDate +
               ", area=" + area +
               ", numberOfRooms=" + numberOfRooms +
               ", furnish=" + furnish +
               ", view=" + view +
               ", transport=" + transport +
               ", house=" + house +
               '}';
    }
} 