package Laptenkov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс для тестирования public методов класса {@link WorkWithFiles}.
 *
 * @author habatoo
 */
class WorkWithFilesTest {

    private WorkWithFiles workWithFiles;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private String filesDirectory;

    /**
     * Инициализация экземпляров тестируемого класса {@link WorkWithFilesTest}.
     */
    @BeforeEach
    void setUp() {
        workWithFiles = new WorkWithFiles();
        filesDirectory = "src/test/java/TestFiles/";
        //filesDirectory = "..\\TestFiles\\";

        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Очистка экземпляров тестируемого класса {@link WorkWithFilesTest}.
     */
    @AfterEach
    void tearDown() {
        workWithFiles = null;

        outContent = null;
        System.setOut(originalOut);
    }


    /**
     * Метод  объект класса {@link WorkWithFilesTest#walkingDirectory_Test},
     * проверить метод {@link WorkWithFiles#walkingDirectory},
     *
     * Сценарий проверяет отображение запускает создание объекта {@link Path} и проверяет отображение
     * тесктовой информации
     */
    @Test
    void walkingDirectory_Test() {
        workWithFiles.walkingDirectory(Paths.get("./"));
        assertEquals("14", outContent.toString().trim());
    }

    /**
     * Метод  объект класса {@link WorkWithFilesTest#warningsPrintingSuccess_Test},
     * проверить метод {@link WorkWithFiles#warningsPrinting},
     * Сценарий запускает создание объекта {@link Path} копируемого файла и проверяет,
     * что метод отработал и, что содержимое файла соответствует целевому значению.
     */
    @Test
    void warningsPrintingSuccess_Test() {

        String inputFilePath = filesDirectory + "log.txt";
        String outputFilePath = filesDirectory + "new.txt";
        Paths.get(inputFilePath);
        try {
            workWithFiles.warningsPrinting(Paths.get(inputFilePath), Paths.get(outputFilePath));
            Assertions.assertEquals(
                    "WARN No database could be detected \n" +
                            "WARN Performing manual recovery\n",
                    Files.lines(Paths.get(outputFilePath))
                            .filter(s -> s.startsWith("WARN"))
                            .map(s -> s + "\n")
                            .collect(Collectors.toList())
                            .toString()
                            .replace("[", "")
                            .replace("]", "")
                            .replace(", ", ""));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Метод  объект класса {@link WorkWithFilesTest#warningsPrintingSuccess_Test},
     * проверить метод {@link WorkWithFiles#warningsPrinting},
     * Сценарий запускает создание объекта {@link Path} копируемого файла с значениями
     * копируемого и копии файла null, проверяет что методу выбрасывает IOException.
     */
    @Test
    void warningsPrintingFail_Test() {

        String inputFilePath = filesDirectory + "none_log";
        String outputFilePath = filesDirectory + "none_new";

        Throwable throwable = Assertions.assertThrows(
                IOException.class, () -> workWithFiles.warningsPrinting(
                        Paths.get(inputFilePath), Paths.get(outputFilePath)));
        Assertions.assertEquals("Input or output file does not exist", throwable.getMessage());
    }

    /**
     * Метод  объект класса {@link WorkWithFilesTest#warningsPrintingNull_Test},
     * проверить метод {@link WorkWithFiles#warningsPrinting},
     * Сценарий запускает создание объекта {@link Path} копируемого файла с значениями
     * копируемого и копии файла null, проверяет что методу выбрасывает IOException.
     */
    @Test
    void warningsPrintingNull_Test() {
        Throwable throwable = Assertions.assertThrows(
                IOException.class, () -> workWithFiles.warningsPrinting(
                        null,
                        null));
        Assertions.assertEquals("Input or output file does not exist", throwable.getMessage());
    }

    /**
     * Метод  объект класса {@link WorkWithFilesTest#copyFileSuccess_Test},
     * проверить метод {@link WorkWithFiles#copyFile},
     * Сценарий запускает копирование файла по переданному пути {@link Path}
     * и возвращает <code>true</code> при удачном капировании существующего файла.
     */
    @Test
    void copyFileSuccess_Test() {
        assertEquals(true, workWithFiles.copyFile(Paths.get("README.md")));
    }

    /**
     * Метод  объект класса {@link WorkWithFilesTest#copyFileSuccess_Test},
     * проверить метод {@link WorkWithFiles#copyFile},
     * Сценарий запускает копирование файла по переданному пути {@link Path}
     * и возвращает <code>false</code> при не удачном капировании файла отсуствующего
     * файла.
     */
    @Test
    void copyFileFail_Test() {
        assertEquals(false, workWithFiles.copyFile(Paths.get("README")));
    }

}