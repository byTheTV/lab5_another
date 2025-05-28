import java.util.Scanner;

import collectionManagers.FlatCollectionManager;
import commandManagers.CommandManager;

/**
 * Точка входа в приложение.
 * <p>
 * Приложение ожидает путь к XML-файлу, содержащему данные, переданный в качестве аргумента командной строки.
 * Если аргумент не передан или является пустой строкой, будет использоваться файл по умолчанию со следующим именем: {@code default.xml}.
 * После инициализации менеджера коллекции происходит загрузка данных и запуск командного обработчика в режиме CLI.
 * </p>
 * <p>
 * Пример запуска:
 * <blockquote><pre>
 * java -cp . Main study_groups.xml
 * cd "d:\.Javaproj\lab5\src\" ; if ($?) { javac Main.java } ; if ($?) { java Main study_groups.xml }
 * gradle runProgram --console=plain
 *
 * </pre></blockquote>
 * </p>
 *
 * @author T.V.
 *
 */
public class Main {

    /**
     * Основной метод приложения.
     *
     * @param args Аргументы командной строки, где ожидается, что первый аргумент содержит путь к XML-файлу.
     *             Если аргументы отсутствуют или первый аргумент пуст, используется {@code study_groups.xml}.
     */
    public static void main(String[] args) {
        try {
            // Initialize collection manager
            FlatCollectionManager collectionManager = new FlatCollectionManager();
            
            // Initialize scanner for user input
            Scanner scanner = new Scanner(System.in);
            
            // Initialize command manager
            CommandManager commandManager = new CommandManager(collectionManager, scanner);
            
            // Main program loop
            while (true) {
                try {
                    System.out.print("> ");
                    String input = scanner.nextLine().trim();
                    
                    if (input.isEmpty()) {
                        continue;
                    }
                    
                    commandManager.executeCommand(input);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
} 

/*
javadoc -d doc -sourcepath src -subpackages models:collectionManagers:commandManagers:fileLogic

jetbra.in/s
*/