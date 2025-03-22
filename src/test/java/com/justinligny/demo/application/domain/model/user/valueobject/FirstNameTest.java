package com.justinligny.demo.application.domain.model.user.valueobject;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstNameTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private FirstName systemUnderTest;

    @BeforeAll
    static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        validatorFactory.close();
    }

    @Test
    void shouldAcceptValidFirstName() {
        systemUnderTest = new FirstName("John");

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(), "Expected no violations for valid first name.");
    }

    @Test
    void shouldRejectNullFirstName() {
        systemUnderTest = new FirstName(null);

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for null first name.");
    }

    @Test
    void shouldRejectBlankFirstName() {
        systemUnderTest = new FirstName("");

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for blank first name.");
    }

    @Test
    void shouldRejectFirstNameWithOnlySpaces() {
        systemUnderTest = new FirstName(" ");

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for blank first name.");
    }

    @Test
    void shouldRejectTooLongFirstName() {
        systemUnderTest = new FirstName("J".repeat(41));

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for a too long first name.");
    }

    @Test
    void shouldRejectNullFirstNameMessage() {
        systemUnderTest = new FirstName(null);

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertEquals("First name cannot be null or blank", violations.iterator().next().getMessage(),
                "Expected specific error message for null first name.");
    }

    @Test
    void shouldRejectBlankFirstNameMessage() {
        systemUnderTest = new FirstName("");

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertEquals("First name cannot be null or blank", violations.iterator().next().getMessage(),
                "Expected specific error message for blank first name.");
    }

    @Test
    void shouldRejectFirstNameWithOnlySpacesMessage() {
        systemUnderTest = new FirstName(" ");

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertEquals("First name cannot be null or blank", violations.iterator().next().getMessage(),
                "Expected specific error message for blank first name.");
    }


    @Test
    void shouldRejectTooLongFirstNameMessage() {
        systemUnderTest = new FirstName("J".repeat(41));

        Set<ConstraintViolation<FirstName>> violations = validator.validate(systemUnderTest);

        assertEquals("First name cannot exceed 40 characters", violations.iterator().next().getMessage(),
                "Expected specific error message for too long first name.");
    }
}
