package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда save сохраняет коллекцию в файл.
 */
public class Save extends Command<FlatCollectionManager> {
    public Save(FlatCollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public String getCommandName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.saveCollection("collection.xml");
        System.out.println("Коллекция успешно сохранена в файл");
    }
} 