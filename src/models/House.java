package models;

public class House {
    private String name; // Поле может быть null
    private Integer year; // Максимальное значение поля: 975, Значение поля должно быть больше 0
    private long numberOfFlatsOnFloor; // Значение поля должно быть больше 0
    private Integer numberOfLifts; // Значение поля должно быть больше 0

    public House(String name, Integer year, long numberOfFlatsOnFloor, Integer numberOfLifts) {
        this.name = name;
        setYear(year);
        setNumberOfFlatsOnFloor(numberOfFlatsOnFloor);
        setNumberOfLifts(numberOfLifts);
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        if (year != null && (year <= 0 || year > 975)) {
            throw new IllegalArgumentException("Year must be greater than 0 and less than or equal to 975");
        }
        this.year = year;
    }

    public long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public void setNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) {
        if (numberOfFlatsOnFloor <= 0) {
            throw new IllegalArgumentException("Number of flats on floor must be greater than 0");
        }
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public Integer getNumberOfLifts() {
        return numberOfLifts;
    }

    public void setNumberOfLifts(Integer numberOfLifts) {
         if (numberOfLifts != null && numberOfLifts <= 0) {
            throw new IllegalArgumentException("Number of lifts must be greater than 0");
        }
        this.numberOfLifts = numberOfLifts;
    }

    @Override
    public String toString() {
        return "House{" +
               "name='" + name + '\'' +
               ", year=" + year +
               ", numberOfFlatsOnFloor=" + numberOfFlatsOnFloor +
               ", numberOfLifts=" + numberOfLifts +
               '}';
    }
} 