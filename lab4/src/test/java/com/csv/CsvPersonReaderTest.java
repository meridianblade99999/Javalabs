package com.csv;

import com.models.Department;
import com.models.EGender;
import com.models.Person;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvPersonReaderTest {

    @Test
    void testReadFromFileWithNonExistentFile() throws Exception {
        // Arrange
        String csvFilePath = "non-existent-file.csv";
        CsvPersonReader csvPersonReader = new CsvPersonReader();

        // Assert
        assertThrows(FileNotFoundException.class, () -> csvPersonReader.readFromFile(csvFilePath),
            "Non exists file name should throw a FileNotFoundException.");
    }

    @Test
    void testReadFromFileWithValidFile() throws Exception {
        // Arrange
        String validFilePath = "valid_data.csv";
        CsvPersonReader csvPersonReader = new CsvPersonReader();

        // Act
        List<Person> persons = csvPersonReader.readFromFile(validFilePath);

        // Assert
        assertNotNull(persons, "The persons list should not be null for a valid file.");
        assertFalse(persons.isEmpty(), "The persons list should not be empty for a valid file.");
        assertEquals(2, persons.size(), "The list size should match the number of records in the valid file.");
    }

    @Test
    void testReadFromFileWithEmptyFile() throws Exception {
        // Arrange
        String emptyFilePath = "empty_data.csv";
        CsvPersonReader csvPersonReader = new CsvPersonReader();

        // Act
        List<Person> persons = csvPersonReader.readFromFile(emptyFilePath);

        // Assert
        assertNotNull(persons, "The persons list should not be null for an empty file.");
        assertTrue(persons.isEmpty(), "The persons list should be empty for an empty file.");
    }

    @Test
    void testReadFromFileWithInvalidDataFile() throws Exception {
        // Arrange
        String invalidFilePath = "invalid_data.csv";
        CsvPersonReader csvPersonReader = new CsvPersonReader();

        // Act & Assert
        assertThrows(RuntimeException.class, () -> csvPersonReader.readFromFile(invalidFilePath),
            "Invalid data file should throw a RuntimeException.");
    }
}