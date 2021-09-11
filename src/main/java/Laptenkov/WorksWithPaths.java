package Laptenkov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс {@link WorksWithPaths} для работы с методами класса {@link Path}.
 *
 * @author habatoo
 */
public class WorksWithPaths {
    /**
     * Создать объект класса {@link Path}, проверить существование и чем является (файл или директория).
     * Если сущность существует, то вывести в консоль информацию:
     * - абсолютный путь
     * - родительский путь
     * Если сущность является файлом, то вывести в консоль:
     * - размер
     * - время последнего изменения
     * Необходимо использовать {@link Path}
     *
     * @param fileName - имя файла
     * @return - true, если файл успешно создан
     */
    public boolean workWithPath(String fileName) {
        if (null != fileName) {
            Path testFilePath = Paths.get(fileName);

            if (null != testFilePath) {
                System.out.println(testFilePath.toAbsolutePath());
                System.out.println(testFilePath.getParent());

                if (Files.exists(testFilePath)) {
                    try {
                        System.out.println(Files.size(testFilePath));
                        System.out.println(Files.getLastModifiedTime(testFilePath));
                        return true;
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }

        return false;
    }
}
