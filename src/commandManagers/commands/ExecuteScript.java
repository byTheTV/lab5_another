package commandManagers.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда execute_script считывает и исполняет скрипт из файла.
 */
public class ExecuteScript extends Command<FlatCollectionManager> {
    public ExecuteScript(FlatCollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public String getCommandName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из файла";
    }

    @Override
    public boolean checkArgument(String[] args) {
        if (args.length != 1) {
            return false;
        }
        File file = new File(args[0]);
        return file.exists() && file.isFile() && file.canRead();
    }

    @Override
    public void execute(String[] args) {
        String filePath = args[0];
        try (Scanner scriptScanner = new Scanner(new File(filePath))) {
            while (scriptScanner.hasNextLine()) {
                String line = scriptScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    collectionManager.executeScript(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
        }
    }
} 