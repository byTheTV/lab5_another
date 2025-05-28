package commandManagers;

import java.util.Scanner;

import collectionManagers.FlatCollectionManager;
import exceptions.CommandInterruptedException;

/**
 * Класс CommandExecutor отвечает за выполнение команд.
 */
public class CommandExecutor {
    private final CommandManager commandManager;
    private final FlatCollectionManager collectionManager;
    private Scanner scanner;

    /**
     * Constructs a new CommandExecutor with the specified collection manager and scanner.
     * @param collectionManager the collection manager to use
     * @param scanner the scanner to use
     */
    public CommandExecutor(FlatCollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.commandManager = new CommandManager(collectionManager, scanner);
    }

    /**
     * Starts executing commands from the specified input stream.
     * @param inputStream the input stream to read commands from
     */
    public void startExecuting(java.io.InputStream inputStream) {
        scanner = new Scanner(inputStream);
        commandManager.setCurrentMode(CommandMode.CLI_UserMode);

        while (true) {
            try {
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    continue;
                }
                
                commandManager.executeCommand(input);
            } catch (CommandInterruptedException e) {
                System.err.println("Command execution interrupted: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error executing command: " + e.getMessage());
            }
        }
    }

    /**
     * Stops executing commands and closes the scanner.
     */
    public void stopExecuting() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public void executeCommand(String input) {
        commandManager.executeCommand(input);
    }
}
