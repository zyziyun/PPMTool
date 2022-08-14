package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.agileintelligence.ppmtool.repositories.ProjectRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRespository projectRespository;

    public Project saveOrUpdateProject(Project project) {
        String id = project.getProjectIdentifier().toUpperCase();
        // update
        if (project.getId() != null) {
            Project exist = projectRespository.findByProjectIdentifier(id);
            if (exist == null) {
                throw new ProjectNotFoundException("Project ID '"+ id +"' cannot be updated because it doesn't exist");
            }
        }
        try {
            project.setProjectIdentifier(id);
            return projectRespository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '"+ id +"' already exist");
        }
    }

    public Project findProjectByIdentifer(String projectId) {
        Project project = projectRespository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID '"+ projectId.toUpperCase() +"' does not exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRespository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRespository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Cannot Project with ID '"+ projectId +"'. This project does not exist");
        }
        projectRespository.delete(project);
    }
}
