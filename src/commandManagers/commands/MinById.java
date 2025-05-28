package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда min_by_id выводит любой объект из коллекции, значение поля id которого является минимальным.
 */
public class MinById extends Command<FlatCollectionManager> {
    public MinById(FlatCollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public String getCommandName() {
        return "min_by_id";
    }

    @Override
    public String getDescription() {
        return "вывести любой объект из коллекции, значение поля id которого является минимальным";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        var minFlat = collectionManager.findMinById();
        if (minFlat != null) {
            System.out.println(minFlat);
        } else {
            System.out.println("Коллекция пуста");
        }
    }
} 