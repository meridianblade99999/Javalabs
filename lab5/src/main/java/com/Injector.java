package com;

import com.annotations.AutoInjectable;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс предназначен для внедрения зависимостей в объекты на основе аннотации {@code AutoInjectable}.
 * Использует файл конфигурации для сопоставления интерфейсов с их реализациями.
 */
public class Injector {

    /**
     * Объект типа {@link Properties}, содержащий конфигурацию для сопоставления интерфейсов с их реализациями.
     * Используется для загрузки настроек из файла конфигурации и определения, какие реализации следует внедрять в зависимости.
     */
    private final Properties properties;


    /**
     * Конструктор класса {@code Injector}, предназначенного для внедрения зависимостей
     * на основании аннотации {@code AutoInjectable}, используя файл конфигурации.
     *
     * @param propertiesPath путь к файлу конфигурации, содержащему сопоставления интерфейсов
     *                       с их реализациями
     * @throws RuntimeException если произошла ошибка загрузки файла конфигурации
     */
    public Injector(String propertiesPath) {
        properties = new Properties();
        try (InputStream input = Injector.class.getClassLoader().getResourceAsStream(propertiesPath)) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки файла", e);
        }
    }


    /**
     * Выполняет автоматическое внедрение зависимостей в переданный объект. Поля объекта,
     * помеченные аннотацией {@code AutoInjectable}, заполняются экземплярами
     * соответствующих классов, указанных в файле конфигурации.
     *
     * @param <T> тип передаваемого объекта
     * @param object объект, в который необходимо выполнить внедрение зависимостей
     * @return объект того же типа, с внедренными зависимостями
     * @throws RuntimeException если возникает ошибка при создании экземпляра
     *         или настройке зависимостей
     */
    public <T> T inject(T object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String className = properties.getProperty(field.getType().getName());

                try {
                    Class<?> classImpl = Class.forName(className);
                    Object instanceImpl = classImpl.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(object, instanceImpl);
                } catch (Exception e) {
                    throw new RuntimeException("Произошла ошибка", e);
                }
            }
        }

        return object;
    }

}