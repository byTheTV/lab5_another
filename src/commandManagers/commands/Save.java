package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда для сохранения коллекции в файл
 */
public class Save extends Command<FlatCollectionManager> {
    private static final String DEFAULT_FILE = "data/collection.xml";

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
        try {
            collectionManager.saveCollection(DEFAULT_FILE);
            System.out.println("Коллекция успешно сохранена в файл: " + DEFAULT_FILE);
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении коллекции: " + e.getMessage());
        }
    }
} 