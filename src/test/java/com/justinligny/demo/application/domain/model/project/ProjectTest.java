package com.justinligny.demo.application.domain.model.project;

import com.justinligny.demo.application.domain.model.project.valueobject.ProjectId;
import com.justinligny.demo.application.domain.model.project.valueobject.ProjectName;
import com.justinligny.demo.application.domain.model.project.valueobject.ProjectReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    private ProjectId projectId;
    private ProjectName projectName;
    private ProjectReference projectReference;
    private Boolean archived;

    @BeforeEach
    void setUp() {
        projectId = new ProjectId(1L);
        projectName = new ProjectName("Valid Project");
        projectReference = new ProjectReference("ABC12345");
        archived = false;
    }

    // ---- Tests for Project.withId() ----

    @Test
    void shouldCreateProjectWithId() {
        Project project = Project.withId(projectId, projectName, projectReference, archived);

        assertNotNull(project);
    }

    @Test
    void projectWithIdShouldHaveNonNullName() {
        Project project = Project.withId(projectId, projectName, projectReference, archived);

        assertNotNull(project.getName());
    }

    @Test
    void projectWithIdShouldHaveNonNullReference() {
        Project project = Project.withId(projectId, projectName, projectReference, archived);

        assertNotNull(project.getProjectReference());
    }

    @Test
    void projectWithIdShouldHaveNonNullArchived() {
        Project project = Project.withId(projectId, projectName, projectReference, archived);

        assertNotNull(project.getArchived());
    }

    @Test
    void projectWithIdShouldHavePresentId() {
        Project project = Project.withId(projectId, projectName, projectReference, archived);

        assertTrue(project.getId().isPresent());
    }

    @Test
    void projectWithIdShouldReturnCorrectId() {
        Project project = Project.withId(projectId, projectName, projectReference, archived);

        assertTrue(project.getId().isPresent());
        assertEquals(projectId, project.getId().get());
    }

    // ---- Tests for Project.withoutId() ----

    @Test
    void shouldCreateProjectWithoutId() {
        Project project = Project.withoutId(projectName, projectReference, archived);

        assertNotNull(project);
    }

    @Test
    void projectWithoutIdShouldHaveNonNullName() {
        Project project = Project.withoutId(projectName, projectReference, archived);

        assertNotNull(project.getName());
    }

    @Test
    void projectWithoutIdShouldHaveNonNullReference() {
        Project project = Project.withoutId(projectName, projectReference, archived);

        assertNotNull(project.getProjectReference());
    }

    @Test
    void projectWithoutIdShouldHaveNonNullArchived() {
        Project project = Project.withoutId(projectName, projectReference, archived);

        assertNotNull(project.getArchived());
    }

    @Test
    void projectWithoutIdShouldHaveEmptyOptionalId() {
        Project project = Project.withoutId(projectName, projectReference, archived);

        assertTrue(project.getId().isEmpty());
    }

    // ---- Tests for getId() ----

    @Test
    void shouldReturnOptionalIdWhenProjectHasId() {
        Project project = Project.withId(projectId, projectName, projectReference, archived);

        assertTrue(project.getId().isPresent());
        assertEquals(projectId, project.getId().get());
    }

    @Test
    void shouldReturnEmptyOptionalIdWhenProjectHasNoId() {
        Project project = Project.withoutId(projectName, projectReference, archived);

        assertTrue(project.getId().isEmpty());
    }
}
