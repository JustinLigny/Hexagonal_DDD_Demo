package com.justinligny.demo.application.domain.model.shared.valueobject;

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

class EmailTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Email systemUnderTest;

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
    void shouldAcceptValidEmail() {
        systemUnderTest = new Email("valid.email@example.com");

        Set<ConstraintViolation<Email>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(), "Expected no violations for valid email.");
    }

    @Test
    void shouldRejectBlankEmail() {
        systemUnderTest = new Email("");

        Set<ConstraintViolation<Email>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for blank email.");
    }

    @Test
    void shouldRejectNullEmail() {
        systemUnderTest = new Email(null);

        Set<ConstraintViolation<Email>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for null email.");
    }

    @Test
    void shouldRejectInvalidEmailFormat() {
        systemUnderTest = new Email("invalid-email");

        Set<ConstraintViolation<Email>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for invalid email format.");
    }

    @Test
    void shouldRejectInvalidEmailFormatMessage() {
        systemUnderTest = new Email("invalid-email");

        Set<ConstraintViolation<Email>> violations = validator.validate(systemUnderTest);

        assertEquals("Email must be in a valid format", violations.iterator().next().getMessage(),
                "Expected specific error message for invalid email format.");
    }


    @Test
    void shouldRejectTooLongEmail() {
        systemUnderTest = new Email("a".repeat(64) + "@" + "a".repeat(40) + ".com");

        Set<ConstraintViolation<Email>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(),
                "Expected exactly one violation for email length exceeding 100 characters.");
    }

    @Test
    void shouldRejectTooLongEmailMessage() {
        systemUnderTest = new Email("a".repeat(64) + "@" + "a".repeat(40) + ".com");

        Set<ConstraintViolation<Email>> violations = validator.validate(systemUnderTest);

        assertEquals("Email cannot exceed 100 characters", violations.iterator().next().getMessage(),
                "Expected specific error message for email exceeding 100 characters.");
    }

    @Test
    void shouldAcceptEmailWithAtMost64CharactersBeforeAt() {
        String email = "a".repeat(64) + "@example.com";

        Set<ConstraintViolation<Email>> violations = validator.validate(new Email(email));

        assertEquals(0, violations.size(), "Expected no violations for valid email.");
    }

    @Test
    void shouldRejectEmailWithMoreThan64CharactersBeforeAt() {
        String email = "a".repeat(65) + "@example.com";

        Set<ConstraintViolation<Email>> violations = validator.validate(new Email(email));

        assertEquals(1, violations.size(), "Expected 1 violation for invalid email format.");
    }

    @Test
    void shouldRejectEmailWithMoreThan64CharactersBeforeAtMessage() {
        String email = "a".repeat(65) + "@example.com";

        Set<ConstraintViolation<Email>> violations = validator.validate(new Email(email));

        assertEquals("Email must be in a valid format", violations.iterator().next().getMessage());
    }

    @Test
    void shouldRejectTooLongEmailWithSpecialChars() {
        systemUnderTest = new Email("a".repeat(101) + "@example.com");

        Set<ConstraintViolation<Email>> violations = validator.validate(systemUnderTest);

        assertEquals(2, violations.size(),
                "Expected 2 violations for email length exceeding 100 characters and email is not in a valid format");
    }
}
