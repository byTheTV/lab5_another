package commandManagers.commands;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;
import commandManagers.CommandManager;

/**
 * Команда для выполнения скрипта из файла
 */
public class ExecuteScript extends Command<FlatCollectionManager> {
    private final CommandManager commandManager;

    public ExecuteScript(FlatCollectionManager collectionManager, CommandManager commandManager) {
        super(collectionManager);
        this.commandManager = commandManager;
    }

    @Override
    public String getCommandName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 1;
    }

    @Override
    public void execute(String[] args) {
        String filePath = args[0];
        File scriptFile = new File(filePath);

        if (!scriptFile.exists()) {
            System.err.println("Ошибка: Файл скрипта не найден: " + filePath);
            return;
        }

        if (!scriptFile.canRead()) {
            System.err.println("Ошибка: Нет прав на чтение файла: " + filePath);
            return;
        }

        try (Scanner scriptScanner = new Scanner(new FileInputStream(scriptFile))) {
            System.out.println("Выполняю скрипт из файла: " + filePath);
            
            while (scriptScanner.hasNextLine()) {
                String line = scriptScanner.nextLine().trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    commandManager.executeCommand(line);
                }
            }
            
            System.out.println("Скрипт успешно выполнен");
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }
} 