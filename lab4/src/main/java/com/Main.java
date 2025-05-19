package com;

import com.csv.CsvPersonReader;
import com.models.Person;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

/**
 * Главный класс программы, содержащий точку входа - метод main.
 *
 * Данный класс выполняет следующие задачи:
 * - Создает экземпляр класса {@code CsvPersonReader} для чтения данных из файла CSV.
 * - Читает список объектов {@code Person} из указанного CSV-файла ("foreign_names.csv").
 * - Перебирает считанные объекты и выводит их информацию на консоль.
 */
public class Main {

    /**
     * Точка входа в приложение.
     *
     * Метод выполняет следующие действия:
     * 1. Создает экземпляр класса {@code CsvPersonReader} для чтения данных из CSV-файла.
     * 2. Считывает список объектов {@code Person} из файла "foreign_names.csv".
     * 3. Перебирает полученные объекты {@code Person} и выводит их информацию на консоль.
     *
     * @param args массив строковых аргументов командной строки
     */
    public static void main(String[] args) throws CsvValidationException, IOException {
        CsvPersonReader csvReader = new CsvPersonReader();
        List<Person> persons = csvReader.readFromFile("foreign_names.csv");
        for (Person person : persons) {
            System.out.println(person);
        }
    }

}