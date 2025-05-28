import java.io.File;
import java.util.Scanner;

import collectionManagers.FlatCollectionManager;
import commandManagers.CommandExecutor;

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
    private static final String DATA_FILE = "data/collection.xml";

    /**
     * Основной метод приложения.
     *
     * @param args Аргументы командной строки, где ожидается, что первый аргумент содержит путь к XML-файлу.
     *             Если аргументы отсутствуют или первый аргумент пуст, используется {@code study_groups.xml}.
     */
    public static void main(String[] args) {
        try {
            // Проверяем существование файла с данными
            File dataFile = new File(DATA_FILE);
            if (!dataFile.exists()) {
                System.err.println("Ошибка: Файл с данными не найден: " + DATA_FILE);
                return;
            }

            // Создаем менеджер коллекции и загружаем данные
            FlatCollectionManager collectionManager = new FlatCollectionManager();
            collectionManager.loadCollection(DATA_FILE);

            // Создаем исполнитель команд и запускаем его
            Scanner scanner = new Scanner(System.in);
            CommandExecutor executor = new CommandExecutor(collectionManager, scanner);
            executor.startExecuting(System.in);

        } catch (Exception e) {
            System.err.println("Ошибка при запуске программы: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 

/*
javadoc -d doc -sourcepath src -subpackages models:collectionManagers:commandManagers:fileLogic

jetbra.in/s
*/