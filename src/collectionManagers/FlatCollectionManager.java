package collectionManagers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import models.Flat;
import models.House;

public class FlatCollectionManager {
    private TreeMap<Long, Flat> flatCollection;
    private Date initializationDate;
    private final XMLReader xmlReader;
    private final XMLWriter xmlWriter;
    private long nextId = 1;

    public FlatCollectionManager() {
        this.flatCollection = new TreeMap<>();
        this.initializationDate = new Date();
        this.xmlReader = new XMLReader();
        this.xmlWriter = new XMLWriter();
    }

    public void addFlat(Flat flat) {
        flat.setId(generateNextId());
        flatCollection.put(flat.getId(), flat);
    }

    public void showCollection() {
        if (flatCollection.isEmpty()) {
            System.out.println("Collection is empty.");
        } else {
            flatCollection.values().forEach(System.out::println);
        }
    }

    public String getInfo() {
        return "Collection Type: " + flatCollection.getClass().getName() +
               "\nInitialization Date: " + initializationDate +
               "\nNumber of Elements: " + flatCollection.size();
    }

    private long generateNextId() {
        // Ensure uniqueness even if elements are removed
        long currentMaxId = flatCollection.values().stream()
                                        .map(Flat::getId)
                                        .filter(id -> id != null)
                                        .max(Long::compareTo)
                                        .orElse(0L);
        nextId = Math.max(nextId, currentMaxId + 1);
        return nextId++;
    }

    public boolean updateFlat(Long id, Flat updatedFlat) {
        Optional<Flat> existingFlat = Optional.ofNullable(flatCollection.get(id));
        if (existingFlat.isPresent()) {
            Flat flatToUpdate = existingFlat.get();
            // Сохраняем оригинальные id и дату создания
            Long originalId = flatToUpdate.getId();
            Date originalCreationDate = flatToUpdate.getCreationDate();
            
            // Копируем все поля из обновленной квартиры
            flatToUpdate.setName(updatedFlat.getName());
            flatToUpdate.setCoordinates(updatedFlat.getCoordinates());
            flatToUpdate.setArea(updatedFlat.getArea());
            flatToUpdate.setNumberOfRooms(updatedFlat.getNumberOfRooms());
            flatToUpdate.setFurnish(updatedFlat.getFurnish());
            flatToUpdate.setView(updatedFlat.getView());
            flatToUpdate.setTransport(updatedFlat.getTransport());
            flatToUpdate.setHouse(updatedFlat.getHouse());
            
            // Восстанавливаем оригинальные id и дату создания
            flatToUpdate.setId(originalId);
            flatToUpdate.setCreationDate(originalCreationDate);
            
            System.out.println("Элемент с id " + id + " успешно обновлен");
            return true;
        } else {
            System.out.println("Элемент с id " + id + " не найден");
            return false;
        }
    }

    public boolean removeFlatById(Long id) {
        return flatCollection.remove(id) != null;
    }

    public void clearCollection() {
        flatCollection.clear();
        System.out.println("Collection cleared.");
    }

    /**
     * Загружает коллекцию из XML файла
     * @param filePath путь к XML файлу
     * @throws Exception если произошла ошибка при чтении файла
     */
    public void loadCollection(String filePath) throws Exception {
        List<Flat> flats = xmlReader.read(filePath);
        flatCollection.clear();
        for (Flat flat : flats) {
            flatCollection.put(flat.getId(), flat);
        }
        System.out.println("Загружено " + flatCollection.size() + " элементов");
    }

    /**
     * Сохраняет коллекцию в XML файл
     * @param filePath путь к XML файлу
     * @throws Exception если произошла ошибка при записи файла
     */
    public void saveCollection(String filePath) throws Exception {
        xmlWriter.write(new ArrayList<>(flatCollection.values()), filePath);
    }

    public void executeScript(String filePath) {
        // Script execution logic needs to be implemented
        System.out.println("Execute script command not fully implemented.");
    }

    public boolean removeFlatAtIndex(int index) {
        if (index < 0 || index >= flatCollection.size()) {
            System.out.println("Invalid index.");
            return false;
        }
        Iterator<Flat> iterator = flatCollection.values().iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        iterator.remove();
        return true;
    }

    public void addIfMin(Flat flat) {
        // Comparison logic for Flat needs to be defined and implemented
        Optional<Flat> minFlat = flatCollection.values().stream()
                                              .min(Comparator.comparing(Flat::getId)); // Example comparison by ID

        if (minFlat.isEmpty() || compareFlats(flat, minFlat.get()) < 0) {
             flat.setId(generateNextId());
             flatCollection.put(flat.getId(), flat);
             System.out.println("Flat added as it is less than the minimum.");
        } else {
             System.out.println("Flat not added as it is not less than the minimum.");
        }
    }

    public void removeLower(Flat flat) {
         // Comparison logic for Flat needs to be defined and implemented
        boolean removed = flatCollection.values().removeIf(f -> compareFlats(f, flat) < 0);
        if (removed) {
            System.out.println("Removed elements lower than the given flat.");
        } else {
            System.out.println("No elements lower than the given flat found.");
        }
    }

    public Flat findMinById() {
        return flatCollection.values().stream()
                             .filter(flat -> flat.getId() != null)
                             .min(Comparator.comparing(Flat::getId))
                             .orElse(null);
    }

    public long countLessThanHouse(House house) {
        // Comparison logic for House needs to be defined and implemented
        return flatCollection.values().stream()
                             .filter(flat -> flat.getHouse() != null)
                             .filter(flat -> compareHouses(flat.getHouse(), house) < 0) // Example comparison
                             .count();
    }

    public Set<House> printUniqueHouses() {
        Set<House> uniqueHouses = new HashSet<>();
        flatCollection.values().stream()
                      .map(Flat::getHouse)
                      .filter(h -> h != null)
                      .forEach(uniqueHouses::add);
        uniqueHouses.forEach(System.out::println);
        return uniqueHouses;
    }

    // Placeholder comparison methods - IMPLEMENT BASED ON DESIRED CRITERIA
    private int compareFlats(Flat f1, Flat f2) {
        // Define how to compare two Flat objects (e.g., by area, number of rooms, etc.)
        // Example: comparing by area
        return Float.compare(f1.getArea(), f2.getArea());
    }

     private int compareHouses(House h1, House h2) {
        // Define how to compare two House objects (e.g., by year, number of flats, etc.)
        // Example: comparing by year
        if (h1.getYear() == null && h2.getYear() == null) return 0;
        if (h1.getYear() == null) return -1;
        if (h2.getYear() == null) return 1;
        return Integer.compare(h1.getYear(), h2.getYear());
    }
} 