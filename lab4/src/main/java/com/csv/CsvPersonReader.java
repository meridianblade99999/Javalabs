package com.csv;

import com.models.Department;
import com.models.EGender;
import com.models.Person;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс {@code CsvPersonReader} предназначен для чтения данных о людях из CSV-файлов.
 * Данные из файла преобразуются в список объектов {@code Person}.
 * Каждая строка файла представляет одного человека, за исключением заголовка,
 * который игнорируется.
 *
 * Основные методы:
 * - {@code readFromFile}: считывает данные из CSV-файла и возвращает список объектов {@code Person}.
 * - {@code createPerson}: создаёт объект {@code Person} на основе данных строки из файла.
 *
 * Поддерживаемый формат CSV:
 * - Символ-разделитель: точка с запятой (";").
 * - Первая строка файла — заголовки (игнорируется при чтении).
 * - Поля:
 *     1. ID (целое число)
 *     2. Имя (строка)
 *     3. Пол (строка: "Male" или "Female")
 *     4. День рождения (строка)
 *     5. Название отдела (строка)
 *     6. Зарплата (целое число)
 *
 * Используемые зависимости:
 * - {@code Person}: класс для хранения данных о каждом человеке, включая его основные свойства
 *   (имя, зарплата, пол и т.д.).
 * - {@code Department}: класс, представляющий отдел, к которому человек относится.
 * - {@code EGender}: перечисление для определения пола.
 * - Библиотека OpenCSV для обработки файлов формата CSV.
 */
public class CsvPersonReader {

    /**
     * Считывает данные из CSV-файла, создаёт список объектов {@code Person} и возвращает его.
     * Каждая строка файла представляет данные одного объекта {@code Person}.
     * Первая строка файла (заголовок) пропускается.
     *
     * @param csvFilePath путь к CSV-файлу, содержащему данные в формате с разделителем ";". Путь указывается
     *                    относительно ресурсов класса.
     * @return список объектов {@code Person}, извлечённых из файла.
     * @throws IOException если произошла ошибка при чтении файла.
     * @throws CsvValidationException если формат CSV-файла не соответствует ожиданиям.
     * @throws FileNotFoundException если файл по указанному пути не найден.
     */
    public List<Person> readFromFile(String csvFilePath) throws IOException, CsvValidationException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath);
         CSVReader reader = in == null ? null : new CSVReaderBuilder(new InputStreamReader(in, StandardCharsets.UTF_8))
             .withCSVParser(new CSVParserBuilder()
                 .withSeparator(';')
                 .build())
             .build();
        if (reader == null) {
            throw new FileNotFoundException(csvFilePath);
        }

        reader.readNext();//пропускаем 1 строку

        List<Person> persons = new ArrayList<>();
        Map<String, Department> departmentMap = new HashMap<>();
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            persons.add(createPerson(nextLine, departmentMap));
        }
        return persons;
    }

    /**
     * Создает объект {@code Person} на основе данных строки и карты отделов.
     * Если в карте отделов отсутствует отдел с указанным именем, он создается и добавляется в карту.
     *
     * @param line массив строк, содержащий данные для создания объекта {@code Person}.
     *             Данные должны следовать фиксированному формату:
     *             - line[0]: ID (целое число),
     *             - line[1]: имя (строка),
     *             - line[2]: пол (строка, "Male" или "Female"),
     *             - line[3]: день рождения (строка),
     *             - line[4]: название отдела (строка),
     *             - line[5]: зарплата (целое число).
     * @param departmentMap карта, где ключами являются названия отделов (строка),
     *                      а значениями - соответствующие объекты класса {@code Department}.
     *                      Используется для избежания создания дублированных объектов отдела.
     * @return новый объект {@code Person}, содержащий данные, извлеченные из строки,
     *         и соответствующий объект отдела из карты или вновь созданный.
     * @throws NumberFormatException если числовые данные имеют неправильный формат.
     * @throws IllegalArgumentException если переданное значение пола не соответствует ожидаемым значениям.
     */
    private Person createPerson(String[] line, Map<String, Department> departmentMap) {
        String departmentName = line[4];
        Department department = departmentMap.get(departmentName);
        if (department == null) {
            department = new Department(departmentName);
            departmentMap.put(departmentName, department);
        }

        EGender gender = EGender.getGender(line[2]);

        return new Person(Integer.parseInt(line[0]), line[1], gender, department,
            Integer.parseInt(line[5]), line[3]);
    }

}