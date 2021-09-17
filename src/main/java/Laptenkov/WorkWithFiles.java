package Laptenkov;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс {@link WorkWithFiles} для работы с методами класса {@link Path}
 * и класса {@link Files}.
 *
 * @author habatoo
 */
public class WorkWithFiles {
    /**
     * В данном методе необходимо пройти по каталогу и вывести в консоль следующие значение:
     * - количество файлов в каталоге (включая во вложенных каталогах)
     *
     * @param path - путь к каталогу, по которому необходимо предоставить информацию
     */
    public void walkingDirectory(Path path) {

        if (null == path) {
            return;
        }

        try (Stream<Path> files = Files.list(path)) {
            long count = files.count();
            System.out.println(count);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    /**
     * В данном методе необходимо все логи уровня WARN из исходного файла скопировать в новый файл.
     * Пример содержания исходного файла лога:
     * INFO Server starting
     * DEBUG Processes available = 10
     * WARN No database could be detected
     * DEBUG Processes available reset to 0
     * WARN Performing manual recovery
     * INFO Server successfully started
     *
     * @param sourceFile      - исходный файл с логом
     * @param destinationFile - целевой файл
     */
    public void warningsPrinting(Path sourceFile, Path destinationFile) throws IOException {

        if (null == sourceFile || null == destinationFile
                || Files.notExists(sourceFile) || Files.notExists(destinationFile)) {
            throw new IOException("Input or output file does not exist");
        }

        try (BufferedWriter bw = Files.newBufferedWriter(destinationFile, Charset.defaultCharset());
             Stream<String> newSourceFile = Files.lines(sourceFile)) {

            bw.write(newSourceFile.filter(s -> s.startsWith("WARN"))
                    .map(s -> s + "\n")
                    .collect(Collectors.toList())
                    .toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(", ", ""));
        } catch (NoSuchFileException noSuchFileException) {
            noSuchFileException.getMessage();
            noSuchFileException.printStackTrace();
                }
        catch (IOException ioException) {
            ioException.getMessage();
            ioException.printStackTrace();
        }

    }

    /**
     * В данном методе необходимо реализовать создание копии файла средствами пакета {@link java.nio}
     *
     * @param path - исходный файл
     * @return - true при успешном создании копии
     */
    public boolean copyFile(Path path) {
        Path copyPath = Paths.get(".").toAbsolutePath().resolve(Paths.get("copy_" + path.getFileName()));

        if (new WorksWithPaths().workWithPath(path.toString())) {
            try {
                if (Files.exists(copyPath))
                    Files.delete(copyPath);
                Files.copy(path, copyPath);
                return true;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return false;
    }
}
