package commandManagers.commands;

import commandManagers.Command;
import commandManagers.CommandManager;
import commandManagers.CommandMode;
import commandManagers.InputReader;
import collectionManagers.StudyGroupCollectionManager;
import models.*;
import java.util.Scanner;
import java.time.LocalDate;

public class Add extends Command {
    private final CommandManager commandManager;

    public Add(StudyGroupCollectionManager collectionManager, Scanner scanner, CommandManager commandManager) {
        super(false, collectionManager);
        this.commandManager = commandManager;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public void execute() {
        try {
            InputReader inputReader = new InputReader(commandManager.getScanner(), commandManager.getCurrentMode());

            StudyGroup studyGroup = new StudyGroup();

            if (commandManager.getCurrentMode() == CommandMode.CLI_UserMode) {
                // Интерактивный режим с повторными попытками
                inputReader.SetFieldWithRetry(studyGroup, () -> studyGroup.setName(inputReader.readName(null)), "название группы");
                inputReader.SetFieldWithRetry(studyGroup, () -> studyGroup.setCoordinates(inputReader.readCoordinates(null)), "координаты");
                inputReader.SetFieldWithRetry(studyGroup, () -> studyGroup.setStudentsCount(inputReader.readStudentsCount(null)), "количество студентов");
                inputReader.SetFieldWithRetry(studyGroup, () -> studyGroup.setExpelledStudents(inputReader.readExpelledStudents(null)), "отчисленные студенты");
                inputReader.SetFieldWithRetry(studyGroup, () -> studyGroup.setTransferredStudents(inputReader.readTransferredStudents(null)), "переведенные студенты");
                inputReader.SetFieldWithRetry(studyGroup, () -> studyGroup.setFormOfEducation(inputReader.readFormOfEducation(null)), "форма обучения");
                inputReader.SetFieldWithRetry(studyGroup, () -> studyGroup.setGroupAdmin(inputReader.readGroupAdmin(null)), "администратор группы");
            } else {
                // Режим чтения из файла - без повторных попыток
                try {
                    studyGroup.setName(inputReader.readName(null));
                    studyGroup.setCoordinates(inputReader.readCoordinates(null));
                    studyGroup.setStudentsCount(inputReader.readStudentsCount(null));
                    studyGroup.setExpelledStudents(inputReader.readExpelledStudents(null));
                    studyGroup.setTransferredStudents(inputReader.readTransferredStudents(null));
                    studyGroup.setFormOfEducation(inputReader.readFormOfEducation(null));
                    studyGroup.setGroupAdmin(inputReader.readGroupAdmin(null));
                } catch (Exception e) {
                    System.out.println("Ошибка при чтении данных из файла: " + e.getMessage());
                    return;
                }
            }

            collectionManager.add(studyGroup);
            System.out.println("Элемент успешно добавлен в коллекцию");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            if (commandManager.getCurrentMode() == CommandMode.CLI_UserMode) {
                execute();
            }
        }
    }

    @Override
    public boolean checkArgument(Object argument) {
        return true;
    }
}

