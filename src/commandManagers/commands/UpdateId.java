package commandManagers.commands;

import commandManagers.Command;
import commandManagers.CommandManager;
import commandManagers.CommandMode;
import commandManagers.InputReader;
import collectionManagers.StudyGroupCollectionManager;
import models.*;
import java.util.Scanner;
import java.time.LocalDate;

public class UpdateId extends Command {
    private final CommandManager commandManager;

    public UpdateId(StudyGroupCollectionManager collectionManager, Scanner scanner, CommandManager commandManager) {
        super(true, collectionManager);
        this.commandManager = commandManager;
    }

    @Override
    public String getName() {
        return "update_id";
    }

    @Override
    public String getDescr() {
        return "обновить значение элемента коллекции по id";
    }

    @Override
    public void execute() {
        if (argument == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }

        long id;
        try {
            id = Long.parseLong((String) argument);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Аргумент должен быть числом", e);
        }

        try {
            StudyGroup oldGroup = collectionManager.getById(id);
            if (oldGroup == null) {
                System.out.println("Элемент с таким id не найден");
                return;
            }

            StudyGroup newGroup = new StudyGroup();
            newGroup.setId(oldGroup.getId());
            InputReader inputReader = new InputReader(commandManager.getScanner(), commandManager.getCurrentMode());

            if (commandManager.getCurrentMode() == CommandMode.CLI_UserMode) {
                // Интерактивный режим с повторными попытками
                inputReader.SetFieldWithRetry(newGroup, () -> newGroup.setName(inputReader.readName(oldGroup.getName())), "название группы");
                inputReader.SetFieldWithRetry(newGroup, () -> newGroup.setCoordinates(inputReader.readCoordinates(oldGroup.getCoordinates())), "координаты");
                inputReader.SetFieldWithRetry(newGroup, () -> newGroup.setStudentsCount(inputReader.readStudentsCount(oldGroup.getStudentsCount())), "количество студентов");
                inputReader.SetFieldWithRetry(newGroup, () -> newGroup.setExpelledStudents(inputReader.readExpelledStudents(oldGroup.getExpelledStudents())), "отчисленные студенты");
                inputReader.SetFieldWithRetry(newGroup, () -> newGroup.setTransferredStudents(inputReader.readTransferredStudents(oldGroup.getTransferredStudents())), "переведенные студенты");
                inputReader.SetFieldWithRetry(newGroup, () -> newGroup.setFormOfEducation(inputReader.readFormOfEducation(oldGroup.getFormOfEducation())), "форма обучения");
                inputReader.SetFieldWithRetry(newGroup, () -> newGroup.setGroupAdmin(inputReader.readGroupAdmin(oldGroup.getGroupAdmin())), "администратор группы");
            } else {
                // Режим скрипта: читаем данные без повторных попыток
                newGroup.setName(inputReader.readName(oldGroup.getName()));
                newGroup.setCoordinates(inputReader.readCoordinates(oldGroup.getCoordinates()));
                newGroup.setStudentsCount(inputReader.readStudentsCount(oldGroup.getStudentsCount()));
                newGroup.setExpelledStudents(inputReader.readExpelledStudents(oldGroup.getExpelledStudents()));
                newGroup.setTransferredStudents(inputReader.readTransferredStudents(oldGroup.getTransferredStudents()));
                newGroup.setFormOfEducation(inputReader.readFormOfEducation(oldGroup.getFormOfEducation()));
                newGroup.setGroupAdmin(inputReader.readGroupAdmin(oldGroup.getGroupAdmin()));
            }

            collectionManager.updateById((int) id, newGroup);
            System.out.println("Элемент успешно обновлен");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            if (commandManager.getCurrentMode() == CommandMode.CLI_UserMode) {
                execute(); // Повторный вызов в случае ошибки в CLI
            }
        }
    }

    @Override
    public boolean checkArgument(Object argument) {
        try {
            Long.parseLong((String) argument);
            return true;
        } catch (NumberFormatException | ClassCastException e) {
            return false;
        }
    }
}