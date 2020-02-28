package io.goranintelligence.ppmtool.services;


import io.goranintelligence.ppmtool.domain.Backlog;
import io.goranintelligence.ppmtool.domain.Project;
import io.goranintelligence.ppmtool.domain.User;
import io.goranintelligence.ppmtool.exceptions.ProjectIdException;
import io.goranintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.goranintelligence.ppmtool.repositories.BacklogRepository;
import io.goranintelligence.ppmtool.repositories.ProjectRepository;
import io.goranintelligence.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username){

        if (project.getId() != null){
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());

            if(existingProject!=null && (!existingProject.getProjectLeader().equals(username))){
                throw new ProjectNotFoundException("Projekt nije pronadjen u Vasoj bazi");
            }else if(existingProject == null ){
                throw new ProjectNotFoundException("Projekt sa ID: '"+project.getProjectIdentifier()+"' ne moze biti azuriran jer ne postoji");
            }
        }

        try{



            User user = userRepository.findByUsername(username);

            project.setUser(user);
            project.setProjectLeader(user.getUsername());

            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Projekt ID'"+project.getProjectIdentifier().toUpperCase()+"' Postoji!");
        }

    }


    public Project findProjectByIdentifier(String projectId, String username){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Projekt ID'"+projectId+"' ne postoji!");
        }

        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Projekt nije pronadjen u Vasoj bazi...");
        }



        return project;
    }

    public Iterable<Project> findAllProjects(String username){
        return projectRepository.findAllByProjectLeader(username);
    }


    public void deleteProjectByIdentifier(String projectid,String username){


        projectRepository.delete(findProjectByIdentifier(projectid,username));
    }

}
