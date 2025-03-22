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

class ProjectReferenceTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private ProjectReference systemUnderTest;

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
    void shouldAcceptValidReference() {
        systemUnderTest = new ProjectReference("ABCD1234");

        Set<ConstraintViolation<ProjectReference>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(), "Expected no violations for a valid reference.");
    }

    @Test
    void shouldDetectOneViolationForNullReference() {
        systemUnderTest = new ProjectReference(null);

        Set<ConstraintViolation<ProjectReference>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for null reference.");
    }

    @Test
    void shouldHaveCorrectMessageForNullReference() {
        systemUnderTest = new ProjectReference(null);

        Set<ConstraintViolation<ProjectReference>> violations = validator.validate(systemUnderTest);

        assertEquals("Project reference cannot be blank", violations.iterator().next().getMessage(),
                "Expected specific error message for null reference.");
    }

    @Test
    void shouldDetectOneViolationForInvalidReferenceLength() {
        systemUnderTest = new ProjectReference("ABC123");

        Set<ConstraintViolation<ProjectReference>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for reference length not equal to 8.");
    }

    @Test
    void shouldHaveCorrectMessageForInvalidReferenceLength() {
        systemUnderTest = new ProjectReference("ABC123");

        Set<ConstraintViolation<ProjectReference>> violations = validator.validate(systemUnderTest);

        assertEquals("Project reference must be 8 characters long, using only A-Z and 0-9", violations.iterator().next().getMessage(),
                "Expected specific error message for reference length.");
    }

    @Test
    void shouldDetectOneViolationForInvalidReferenceCharacters() {
        systemUnderTest = new ProjectReference("ABC123!@");

        Set<ConstraintViolation<ProjectReference>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for reference containing invalid characters.");
    }

    @Test
    void shouldHaveCorrectMessageForInvalidReferenceCharacters() {
        systemUnderTest = new ProjectReference("ABC123!@");

        Set<ConstraintViolation<ProjectReference>> violations = validator.validate(systemUnderTest);

        assertEquals("Project reference must be 8 characters long, using only A-Z and 0-9", violations.iterator().next().getMessage(),
                "Expected specific error message for reference containing invalid characters.");
    }
}
