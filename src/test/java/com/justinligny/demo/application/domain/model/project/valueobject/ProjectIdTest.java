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

class ProjectIdTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private ProjectId systemUnderTest;

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
    void shouldAcceptValidProjectId() {
        systemUnderTest = new ProjectId(1L);

        Set<ConstraintViolation<ProjectId>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(),
                "Expected no violations for valid ProjectId.");
    }

    @Test
    void shouldRejectNullProjectId() {
        systemUnderTest = new ProjectId(null);

        Set<ConstraintViolation<ProjectId>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(),
                "Expected exactly one violation for null ProjectId.");
    }

    @Test
    void shouldRejectNegativeProjectId() {
        systemUnderTest = new ProjectId(-1L);

        Set<ConstraintViolation<ProjectId>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(),
                "Expected exactly one violation for negative ProjectId.");
    }

    @Test
    void shouldRejectNullProjectIdMessage() {
        systemUnderTest = new ProjectId(null);

        Set<ConstraintViolation<ProjectId>> violations = validator.validate(systemUnderTest);

        assertEquals("Project's id cannot be null", violations.iterator().next().getMessage(),
                "Expected specific error message for null ProjectId.");
    }

    @Test
    void shouldRejectNegativeProjectIdMessage() {
        systemUnderTest = new ProjectId(-1L);

        Set<ConstraintViolation<ProjectId>> violations = validator.validate(systemUnderTest);

        assertEquals("Project's id cannot be negative", violations.iterator().next().getMessage(),
                "Expected specific error message for negative ProjectId.");
    }
}
