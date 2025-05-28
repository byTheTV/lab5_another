package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда clear очищает коллекцию.
 */
public class Clear extends Command<FlatCollectionManager> {
    public Clear(FlatCollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public String getCommandName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.clearCollection();
    }
} 