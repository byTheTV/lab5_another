package collectionManagers;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import models.Flat;
import models.House;

public class FlatCollectionManager {
    private Set<Flat> flatCollection = new LinkedHashSet<>();
    private Date initializationDate;
    private long nextId = 1;

    public FlatCollectionManager() {
        this.initializationDate = new Date();
    }

    public void addFlat(Flat flat) {
        flat.setId(generateNextId());
        flatCollection.add(flat);
    }

    public void showCollection() {
        if (flatCollection.isEmpty()) {
            System.out.println("Collection is empty.");
        } else {
            flatCollection.forEach(System.out::println);
        }
    }

    public String getInfo() {
        return "Collection Type: " + flatCollection.getClass().getName() +
               "\nInitialization Date: " + initializationDate +
               "\nNumber of Elements: " + flatCollection.size();
    }

    private long generateNextId() {
        // Ensure uniqueness even if elements are removed
        long currentMaxId = flatCollection.stream()
                                        .map(Flat::getId)
                                        .filter(id -> id != null)
                                        .max(Long::compareTo)
                                        .orElse(0L);
        nextId = Math.max(nextId, currentMaxId + 1);
        return nextId++;
    }

    public boolean updateFlat(Long id, Flat updatedFlat) {
        Optional<Flat> existingFlat = flatCollection.stream()
                                                    .filter(flat -> flat.getId() != null && flat.getId().equals(id))
                                                    .findFirst();
        if (existingFlat.isPresent()) {
            // In a real implementation, you would copy properties from updatedFlat to existingFlat
            // or replace the object entirely. Replacing requires removing and re-adding to the Set.
            Flat flatToUpdate = existingFlat.get();
            // Example (requires thoughtful implementation to maintain ID and creationDate):
            // flatToUpdate.setName(updatedFlat.getName());
            // ... copy other fields ...
            System.out.println("Flat with id " + id + " found. Update logic needs to be implemented.");
            return true;
        } else {
            System.out.println("Flat with id " + id + " not found.");
            return false;
        }
    }

    public boolean removeFlatById(Long id) {
        return flatCollection.removeIf(flat -> flat.getId() != null && flat.getId().equals(id));
    }

    public void clearCollection() {
        flatCollection.clear();
        System.out.println("Collection cleared.");
    }

    public void saveCollection(String filePath) {
        // Saving logic needs to be implemented (e.g., to JSON, XML, etc.)
        System.out.println("Save command not fully implemented.");
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
        Iterator<Flat> iterator = flatCollection.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        iterator.remove();
        return true;
    }

    public void addIfMin(Flat flat) {
        // Comparison logic for Flat needs to be defined and implemented
        Optional<Flat> minFlat = flatCollection.stream()
                                              .min(Comparator.comparing(Flat::getId)); // Example comparison by ID

        if (minFlat.isEmpty() || compareFlats(flat, minFlat.get()) < 0) {
             flat.setId(generateNextId());
             flatCollection.add(flat);
             System.out.println("Flat added as it is less than the minimum.");
        } else {
             System.out.println("Flat not added as it is not less than the minimum.");
        }
    }

    public void removeLower(Flat flat) {
         // Comparison logic for Flat needs to be defined and implemented
        boolean removed = flatCollection.removeIf(f -> compareFlats(f, flat) < 0);
        if (removed) {
            System.out.println("Removed elements lower than the given flat.");
        } else {
            System.out.println("No elements lower than the given flat found.");
        }
    }

    public Flat findMinById() {
        return flatCollection.stream()
                             .filter(flat -> flat.getId() != null)
                             .min(Comparator.comparing(Flat::getId))
                             .orElse(null);
    }

    public long countLessThanHouse(House house) {
        // Comparison logic for House needs to be defined and implemented
        return flatCollection.stream()
                             .filter(flat -> flat.getHouse() != null)
                             .filter(flat -> compareHouses(flat.getHouse(), house) < 0) // Example comparison
                             .count();
    }

    public Set<House> printUniqueHouses() {
        Set<House> uniqueHouses = new HashSet<>();
        flatCollection.stream()
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