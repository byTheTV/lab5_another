package commandManagers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import collectionManagers.FlatCollectionManager;
import commandManagers.commands.Add;
import commandManagers.commands.AddIfMin;
import commandManagers.commands.Clear;
import commandManagers.commands.CountLessThanHouse;
import commandManagers.commands.ExecuteScript;
import commandManagers.commands.Exit;
import commandManagers.commands.Help;
import commandManagers.commands.Info;
import commandManagers.commands.MinById;
import commandManagers.commands.PrintUniqueHouse;
import commandManagers.commands.RemoveById;
import commandManagers.commands.Save;
import commandManagers.commands.Show;
import commandManagers.commands.Update;

/**
 The CommandManager class is responsible for managing all available commands in the application.
 It provides a static method getCommandMap() that returns a HashMap of all commands where the key is
 the command name and the value is an instance of the corresponding Command subclass.
 */
public class CommandManager {

    /**
     A LinkedHashMap object that stores all available commands in the application. The key is the command name
     and the value is an instance of the corresponding Command subclass.
     */
    private final Map<String, Command<FlatCollectionManager>> commands;
    private final FlatCollectionManager collectionManager;
    private final Scanner scanner;
    private CommandMode currentMode = CommandMode.CLI_UserMode;

    /**
     * Новый конструктор, получающий заранее созданный экземпляр FlatCollectionManager.
     *
     * @param scanner сканер для чтения ввода
     * @param collectionManager корректно инициализированный менеджер коллекции
     */
    public CommandManager(FlatCollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.commands = new HashMap<>();
        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("help", new Help(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("add", new Add(collectionManager, scanner));
        commands.put("update", new Update(collectionManager, scanner));
        commands.put("remove_by_id", new RemoveById(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("save", new Save(collectionManager));
        commands.put("execute_script", new ExecuteScript(collectionManager, this));
        commands.put("exit", new Exit());
        commands.put("min_by_id", new MinById(collectionManager));
        commands.put("count_less_than_house", new CountLessThanHouse(collectionManager, scanner));
        commands.put("print_unique_house", new PrintUniqueHouse(collectionManager));
        commands.put("add_if_min", new AddIfMin(collectionManager, scanner));
    }

    public void initializeData(String dataFile) {
        System.out.println("Data initialization logic needs to be updated for Flats.");
    }

    /**
     Returns the commandMap HashMap that stores all available commands in the application.
     @return the HashMap of all commands where the key is the command name and the value is an instance
     of the corresponding Command subclass.
     */
    public HashMap<String, Command<FlatCollectionManager>> getCommandMap() {
        return new HashMap<>(commands);
    }

    public void setCurrentMode(CommandMode mode) {
        this.currentMode = mode;
    }

    public CommandMode getCurrentMode() {
        return currentMode;
    }

    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Universe method for command executing.
     */
    public void executeCommand(String input) {
        String[] parts = input.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        Command<FlatCollectionManager> command = commands.get(commandName);
        if (command == null) {
            System.out.println("Unknown command: " + commandName);
            return;
        }

        if (!command.checkArgument(args)) {
            System.out.println("Invalid arguments for command: " + commandName);
            return;
        }

        command.execute(args);
    }
}
