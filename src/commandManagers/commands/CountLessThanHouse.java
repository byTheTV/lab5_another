package commandManagers.commands;

import java.util.Scanner;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;
import commandManagers.CommandMode;
import commandManagers.InputReader;
import models.House;

/**
 * Команда count_less_than_house выводит количество элементов, значение поля house которых меньше заданного.
 */
public class CountLessThanHouse extends Command<FlatCollectionManager> {
    private final Scanner scanner;
    private final InputReader inputReader;

    public CountLessThanHouse(FlatCollectionManager collectionManager, Scanner scanner) {
        super(collectionManager);
        this.scanner = scanner;
        this.inputReader = new InputReader(scanner, CommandMode.CLI_UserMode);
    }

    @Override
    public String getCommandName() {
        return "count_less_than_house";
    }

    @Override
    public String getDescription() {
        return "вывести количество элементов, значение поля house которых меньше заданного";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        House house = inputReader.readHouse(null);
        long count = collectionManager.countLessThanHouse(house);
        System.out.println("Количество элементов с house меньше заданного: " + count);
    }
} 