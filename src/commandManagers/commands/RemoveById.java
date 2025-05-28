package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда remove_by_id удаляет элемент из коллекции по его id.
 */
public class RemoveById extends Command<FlatCollectionManager> {
    public RemoveById(FlatCollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public String getCommandName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
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
        if (collectionManager.removeFlatById(id)) {
            System.out.println("Элемент с id " + id + " успешно удален");
        } else {
            System.out.println("Элемент с id " + id + " не найден");
        }
    }
}