package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда exit завершает программу (без сохранения).
 */
public class Exit extends Command<FlatCollectionManager> {
    public Exit() {
        super(null);
    }

    @Override
    public String getCommandName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "завершить программу (без сохранения)";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Завершение программы...");
        System.exit(0);
    }
} 