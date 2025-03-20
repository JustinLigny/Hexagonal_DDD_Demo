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

class ProjectNameTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private ProjectName systemUnderTest;

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
    void shouldAcceptValidProjectName() {
        systemUnderTest = new ProjectName("My Project");

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(), "Expected no violations for valid project name.");
    }

    @Test
    void shouldRejectNullProjectName() {
        systemUnderTest = new ProjectName(null);

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for null project name.");
        assertEquals("Project name cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void shouldRejectBlankProjectName() {
        systemUnderTest = new ProjectName("");

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for blank project name.");
        assertEquals("Project name can only contain letters and spaces", violations.iterator().next().getMessage());
    }

    @Test
    void shouldRejectTooLongProjectName() {
        systemUnderTest = new ProjectName("P".repeat(41));

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for a too long project name.");
        assertEquals("Project name cannot exceed 40 characters", violations.iterator().next().getMessage());
    }

    @Test
    void shouldRejectProjectNameWithInvalidCharacters() {
        systemUnderTest = new ProjectName("Project123");

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for a project name with invalid characters.");
        assertEquals("Project name can only contain letters and spaces", violations.iterator().next().getMessage());
    }


    @Test
    void shouldRejectNullProjectNameMessage() {
        systemUnderTest = new ProjectName(null);

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertEquals("Project name cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void shouldRejectBlankProjectNameMessage() {
        systemUnderTest = new ProjectName("");

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertEquals("Project name can only contain letters and spaces", violations.iterator().next().getMessage());
    }

    @Test
    void shouldRejectTooLongProjectNameMessage() {
        systemUnderTest = new ProjectName("P".repeat(41));

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertEquals("Project name cannot exceed 40 characters", violations.iterator().next().getMessage());
    }

    @Test
    void shouldRejectProjectNameWithInvalidCharactersMessage() {
        systemUnderTest = new ProjectName("Project123");

        Set<ConstraintViolation<ProjectName>> violations = validator.validate(systemUnderTest);

        assertEquals("Project name can only contain letters and spaces", violations.iterator().next().getMessage());
    }
}
