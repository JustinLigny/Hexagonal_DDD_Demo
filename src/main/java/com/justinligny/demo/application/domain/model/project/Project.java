package com.justinligny.demo.application.domain.model.project;

import com.justinligny.demo.application.domain.model.project.valueobject.ProjectId;
import com.justinligny.demo.application.domain.model.project.valueobject.ProjectName;
import com.justinligny.demo.application.domain.model.project.valueobject.Reference;
import com.justinligny.demo.common.validation.Validation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class Project {

    @Valid
    private final ProjectId id;

    @NotNull(message = "Name cannot be null")
    @Valid
    private final ProjectName name;

    @NotNull(message = "Reference cannot be null")
    @Valid
    private final Reference reference;

    @NotNull(message = "Archived cannot ")

    public Project(
            final ProjectId id,
            final ProjectName name,
            final Reference reference) {

        this.id = id;
        this.name = name;
        this.reference = reference;

        Validation.validate(this);
    }
}
