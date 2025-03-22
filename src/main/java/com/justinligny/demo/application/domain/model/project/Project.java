package com.justinligny.demo.application.domain.model.project;

import com.justinligny.demo.application.domain.model.project.valueobject.ProjectId;
import com.justinligny.demo.application.domain.model.project.valueobject.ProjectName;
import com.justinligny.demo.application.domain.model.project.valueobject.ProjectReference;
import com.justinligny.demo.common.validation.Validation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString
public final class Project {

    @Valid
    private final ProjectId id;

    @NotNull(message = "Name cannot be null")
    @Valid
    private final ProjectName name;

    @NotNull(message = "ProjectReference cannot be null")
    @Valid
    private final ProjectReference projectReference;

    @NotNull(message = "Archived cannot be null")
    private final Boolean archived;

    private Project(
            final ProjectId id,
            final ProjectName name,
            final ProjectReference reference,
            final Boolean archived) {

        this.id = id;
        this.name = name;
        this.projectReference = reference;
        this.archived = archived;

        Validation.validate(this);
    }

    public static Project withoutId(
            final ProjectName name,
            final ProjectReference reference,
            final Boolean archived) {

        return new Project(null, name, reference, archived);
    }

    public static Project withId(
            final ProjectId id,
            final ProjectName name,
            final ProjectReference reference,
            final Boolean archived) {

        return new Project(id, name, reference, archived);
    }

    public Optional<ProjectId> getId() {
        return Optional.ofNullable(id);
    }
}
