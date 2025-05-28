package commandManagers.commands;

import collectionManagers.FlatCollectionManager;
import commandManagers.Command;

/**
 * Команда help выводит справку по доступным командам.
 */
public class Help extends Command<FlatCollectionManager> {
    public Help(FlatCollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public String getCommandName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public boolean checkArgument(String[] args) {
        return args.length == 0;
    }

    @Override
    public void execute(String[] args) {
        // ANSI escape-последовательности для цвета
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";

        System.out.println("================================================");
        System.out.println(ANSI_GREEN + "Доступные команды:" + ANSI_RESET);
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести информацию о коллекции");
        System.out.println("show : вывести все элементы коллекции");
        System.out.println("add : добавить новый элемент в коллекцию");
        System.out.println("update {id} : обновить значение элемента коллекции по id");
        System.out.println("remove_by_id {id} : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script {file_name} : считать и исполнить скрипт из файла");
        System.out.println("exit : завершить программу (без сохранения)");
        System.out.println("remove_at {index} : удалить элемент, находящийся в заданной позиции коллекции");
        System.out.println("min_by_id : вывести любой объект из коллекции, значение поля id которого является минимальным");
        System.out.println("count_less_than_house {house} : вывести количество элементов, значение поля house которых меньше заданного");
        System.out.println("print_unique_house : вывести уникальные значения поля house всех элементов в коллекции");
        System.out.println("add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("================================================");
    }
} 