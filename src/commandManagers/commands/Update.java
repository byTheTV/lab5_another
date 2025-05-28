package commandManagers.commands;

import java.util.Scanner;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;
import commandManagers.CommandMode;
import commandManagers.InputReader;
import models.Flat;

/**
 * Команда update обновляет значение элемента коллекции по id.
 */
public class Update extends Command<FlatCollectionManager> {
    private final Scanner scanner;
    private final InputReader inputReader;

    public Update(FlatCollectionManager collectionManager, Scanner scanner) {
        super(collectionManager);
        this.scanner = scanner;
        this.inputReader = new InputReader(scanner, CommandMode.CLI_UserMode);
    }

    @Override
    public String getCommandName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции по id";
    }

    @Override
    public boolean checkArgument(String[] args) {
        if (args.length != 1) {
            return false;
        }
        try {
            Long.parseLong(args[0]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void execute(String[] args) {
        Long id = Long.parseLong(args[0]);
        Flat flat = new Flat();
        
        inputReader.setFieldWithRetry(flat, () -> flat.setName(inputReader.readName(null)), "name");
        inputReader.setFieldWithRetry(flat, () -> flat.setCoordinates(inputReader.readCoordinates(null)), "coordinates");
        inputReader.setFieldWithRetry(flat, () -> flat.setArea(inputReader.readArea(null)), "area");
        inputReader.setFieldWithRetry(flat, () -> flat.setNumberOfRooms(inputReader.readNumberOfRooms(null)), "numberOfRooms");
        inputReader.setFieldWithRetry(flat, () -> flat.setFurnish(inputReader.readFurnish(null)), "furnish");
        inputReader.setFieldWithRetry(flat, () -> flat.setView(inputReader.readView(null)), "view");
        inputReader.setFieldWithRetry(flat, () -> flat.setTransport(inputReader.readTransport(null)), "transport");
        inputReader.setFieldWithRetry(flat, () -> flat.setHouse(inputReader.readHouse(null)), "house");

        if (collectionManager.updateFlat(id, flat)) {
            System.out.println("Элемент с id " + id + " успешно обновлен");
        } else {
            System.out.println("Элемент с id " + id + " не найден");
        }
    }
} 