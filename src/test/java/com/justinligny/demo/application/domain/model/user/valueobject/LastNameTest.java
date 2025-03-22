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

class LastNameTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private LastName systemUnderTest;

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
    void shouldAcceptValidLastName() {
        systemUnderTest = new LastName("Doe");

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(), "Expected no violations for valid last name.");
    }

    @Test
    void shouldRejectNullLastName() {
        systemUnderTest = new LastName(null);

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(),
                "Expected exactly one violation for null last name.");
    }

    @Test
    void shouldRejectBlankLastName() {
        systemUnderTest = new LastName("");

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for blank last name.");
    }

    @Test
    void shouldRejectLastNameWithOnlySpaces() {
        systemUnderTest = new LastName(" ");

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for blank last name.");
    }

    @Test
    void shouldRejectTooLongLastName() {
        systemUnderTest = new LastName("D".repeat(41));

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(),
                "Expected exactly one violation for last name length exceeding 40 characters.");
    }

    @Test
    void shouldRejectNullLastNameMessage() {
        systemUnderTest = new LastName(null);

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertEquals("Last name cannot be null or blank", violations.iterator().next().getMessage(),
                "Expected exactly one violation for null last name.");
    }

    @Test
    void shouldRejectBlankLastNameMessage() {
        systemUnderTest = new LastName("");

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertEquals("Last name cannot be null or blank", violations.iterator().next().getMessage(),
                "Expected exactly one violation for blank last name.");
    }

    @Test
    void shouldRejectLastNameWithOnlySpacesMessage() {
        systemUnderTest = new LastName(" ");

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertEquals("Last name cannot be null or blank", violations.iterator().next().getMessage(),
                "Expected specific error message for blank last name.");
    }

    @Test
    void shouldRejectTooLongLastNameMessage() {
        systemUnderTest = new LastName("D".repeat(41));

        Set<ConstraintViolation<LastName>> violations = validator.validate(systemUnderTest);

        assertEquals("Last name cannot exceed 40 characters", violations.iterator().next().getMessage(),
                "Expected exactly one violation last name exceeding length 40 characters.");
    }
}
