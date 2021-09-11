package Laptenkov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс для тестирования public методов класса {@link WorkWithFiles}.
 *
 * @author habatoo
 */
class WorkWithFilesTest {

    WorkWithFiles workWithFiles;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    /**
     * Инициализация экземпляров тестируемого класса {@link WorkWithFilesTest}.
     */
    @BeforeEach
    void setUp() {
        workWithFiles = new WorkWithFiles();

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
     * Сценарий запускает создание объекта {@link Path} и проверяет отображение
     * тесктовой информации
     */
    @Test
    void walkingDirectory_Test() {
        workWithFiles.walkingDirectory(Paths.get("./"));
        assertEquals("11", outContent.toString().trim());
    }

    /**
     * Метод  объект класса {@link WorkWithFilesTest#warningsPrintingSuccess_Test},
     * проверить метод {@link WorkWithFiles#warningsPrinting},
     * Сценарий запускает создание объекта {@link Path} копируемого файла
     */
    @Test
    void warningsPrintingSuccess_Test() {
        workWithFiles.warningsPrinting(Paths.get("log"), Paths.get("new"));
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