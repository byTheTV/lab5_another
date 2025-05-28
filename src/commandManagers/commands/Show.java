package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда show выводит все элементы коллекции.
 */
public class Show extends Command<FlatCollectionManager> {
    private final FlatCollectionManager collectionManager;

    public Show(FlatCollectionManager collectionManager) {
        super(collectionManager);
        this.collectionManager = collectionManager;
    }

    @Override
    public String getCommandName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести все элементы коллекции";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.showCollection();
    }
} 