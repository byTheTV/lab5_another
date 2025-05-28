package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;
import commandManagers.CommandMode;
import commandManagers.InputReader;
import models.Flat;

import java.util.Scanner;

/**
 * Команда add добавляет новый элемент в коллекцию.
 */
public class Add extends Command<FlatCollectionManager> {
    private final Scanner scanner;
    private final InputReader inputReader;

    public Add(FlatCollectionManager collectionManager, Scanner scanner) {
        super(collectionManager);
        this.scanner = scanner;
        this.inputReader = new InputReader(scanner, CommandMode.CLI_UserMode);
    }

    @Override
    public String getCommandName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        Flat flat = new Flat();
        
        inputReader.setFieldWithRetry(flat, () -> flat.setName(inputReader.readName(null)), "name");
        inputReader.setFieldWithRetry(flat, () -> flat.setCoordinates(inputReader.readCoordinates(null)), "coordinates");
        inputReader.setFieldWithRetry(flat, () -> flat.setArea(inputReader.readArea(null)), "area");
        inputReader.setFieldWithRetry(flat, () -> flat.setNumberOfRooms(inputReader.readNumberOfRooms(null)), "numberOfRooms");
        inputReader.setFieldWithRetry(flat, () -> flat.setFurnish(inputReader.readFurnish(null)), "furnish");
        inputReader.setFieldWithRetry(flat, () -> flat.setView(inputReader.readView(null)), "view");
        inputReader.setFieldWithRetry(flat, () -> flat.setTransport(inputReader.readTransport(null)), "transport");
        inputReader.setFieldWithRetry(flat, () -> flat.setHouse(inputReader.readHouse(null)), "house");

        collectionManager.addFlat(flat);
        System.out.println("Элемент успешно добавлен в коллекцию");
    }
}

