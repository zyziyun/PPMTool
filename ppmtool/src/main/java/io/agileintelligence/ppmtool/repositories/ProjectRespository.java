package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRespository extends CrudRepository<Project, Long> {
    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
}
