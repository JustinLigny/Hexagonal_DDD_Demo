package com.justinligny.demo.application.domain.model.project.valueobject;

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

class ProjectDescriptionTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private ProjectDescription systemUnderTest;

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
    void shouldAcceptValidDescription() {
        systemUnderTest = new ProjectDescription("This is a valid project description.");

        Set<ConstraintViolation<ProjectDescription>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(), "Expected no violations for a valid description.");
    }

    @Test
    void shouldRejectNullDescription() {
        systemUnderTest = new ProjectDescription(null);

        Set<ConstraintViolation<ProjectDescription>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for null description.");
    }

    @Test
    void shouldRejectTooLongDescription() {
        systemUnderTest = new ProjectDescription("A".repeat(501));

        Set<ConstraintViolation<ProjectDescription>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for too long description.");
    }

    @Test
    void shouldRejectNullDescriptionMessage() {
        systemUnderTest = new ProjectDescription(null);

        Set<ConstraintViolation<ProjectDescription>> violations = validator.validate(systemUnderTest);

        assertEquals("Project description cannot be null", violations.iterator().next().getMessage(),
                "Expected specific error message for null description.");
    }

    @Test
    void shouldRejectTooLongDescriptionMessage() {
        systemUnderTest = new ProjectDescription("A".repeat(501));

        Set<ConstraintViolation<ProjectDescription>> violations = validator.validate(systemUnderTest);

        assertEquals("Project description cannot exceed 500 characters", violations.iterator().next().getMessage(),
                "Expected specific error message for too long description.");
    }
}
