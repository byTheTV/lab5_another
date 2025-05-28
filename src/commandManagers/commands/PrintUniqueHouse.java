package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда print_unique_house выводит уникальные значения поля house всех элементов в коллекции.
 */
public class PrintUniqueHouse extends Command<FlatCollectionManager> {
    public PrintUniqueHouse(FlatCollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public String getCommandName() {
        return "print_unique_house";
    }

    @Override
    public String getDescription() {
        return "вывести уникальные значения поля house всех элементов в коллекции";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.printUniqueHouses();
    }
} 