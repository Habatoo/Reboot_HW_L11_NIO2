package Laptenkov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс для тестирования public методов класса {@link WorksWithPaths}.
 *
 * @author habatoo
 */
class WorksWithPathsTest {

    WorksWithPaths worksWithPaths;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    /**
     * Инициализация экземпляров тестируемого класса {@link WorksWithPathsTest}.
     */
    @BeforeEach
    void setUp() {
        worksWithPaths = new WorksWithPaths();



        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Очистка экземпляров тестируемого класса {@link WorksWithPathsTest}.
     */
    @AfterEach
    void tearDown() {
        worksWithPaths = null;

        outContent = null;
        System.setOut(originalOut);
    }

    /**
     * Метод  объект класса {@link WorksWithPathsTest#workWithPathSuccessTrue_Test},
     * проверить метод {@link WorksWithPaths#workWithPath},
     * Сценарий запускает создание объекта {@link Path} и выдает
     * <code>true</code> при передаче в качестве параметра пути с существующим файлом.
     */
    @Test
    void workWithPathSuccessTrue_Test() {
        assertEquals(true, worksWithPaths.workWithPath("README.md"));
    }

    /**
     * Метод  объект класса {@link WorksWithPathsTest#workWithPathSuccessTrue_Test},
     * проверить метод {@link WorksWithPaths#workWithPath},
     * Сценарий запускает создание объекта {@link Path} и выдает
     * <code>false</code> при передаче в качестве параметра пути с не существующим файлом.
     */
    @Test
    void workWithPathSuccessFalse_Test() {
        assertEquals(false, worksWithPaths.workWithPath("None"));
    }

    /**
     * Метод  объект класса {@link WorksWithPathsTest#workWithPathFailFalse_Test},
     * проверить метод {@link WorksWithPaths#workWithPath},
     * Сценарий запускает создание объекта {@link Path} и выдает
     * <code>false</code> при передаче в качестве параметра пути null.
     */
    @Test
    void workWithPathFailFalse_Test() {
        assertEquals(false, worksWithPaths.workWithPath(null));
    }

    /**
     * Метод  объект класса {@link WorksWithPathsTest#workWithPathSuccessOut_Test},
     * проверить метод {@link WorksWithPaths#workWithPath},
     * Сценарий запускает создание объекта {@link Path} и проверяет отображение
     * тесктовой информации
     * если сущность существует, то вывести в консоль информацию:
     * - абсолютный путь
     * - родительский путь
     * Если сущность является файлом, то вывести в консоль:
     * - размер
     * - время последнего изменения
     */
    @Test
    void workWithPathSuccessOut_Test() {
        assertEquals(true, worksWithPaths.workWithPath("README.md"));
        assertEquals("/home/habatoo/Documents/Learning/Java/Sber JD/lesson_11/homework11/README.md\n" +
                "null\n" +
                "465\n" +
                "2021-09-08T12:40:03.410543Z", outContent.toString().trim());
    }

}