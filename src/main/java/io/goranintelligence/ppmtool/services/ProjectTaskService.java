package io.goranintelligence.ppmtool.services;

import io.goranintelligence.ppmtool.domain.Backlog;
import io.goranintelligence.ppmtool.domain.Project;
import io.goranintelligence.ppmtool.domain.ProjectTask;
import io.goranintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.goranintelligence.ppmtool.repositories.BacklogRepository;
import io.goranintelligence.ppmtool.repositories.ProjectRepository;
import io.goranintelligence.ppmtool.repositories.ProjectTaskRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask,String username){



            //PTs to be added to a specific project, project !=null,BL exists
            Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier,username).getBacklog();


            //set the BL to PT
            projectTask.setBacklog(backlog);
            // we want pSequ to be like this: IDPRO-1 IDPRO-2
            Integer BacklogSequence = backlog.getPTSequence();
            //update the BL sequence
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);

            //add sqeuence to Project Task
            projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            //INITAL priority when priory null
            if(projectTask.getPriority()==null||projectTask.getPriority()==0){ // FUTURE: need .getPriority==0 to handle the form
                projectTask.setPriority(3);
            }
            //INITAL status when status is null
            if(projectTask.getStatus()==""||projectTask.getStatus()==null){
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String id,String username) {

        projectService.findProjectByIdentifier(id, username);

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }


    public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id, String username){

        //make sure we are searching on the right backlog
        projectService.findProjectByIdentifier(backlog_id, username);


        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if (projectTask==null){
            throw new ProjectNotFoundException("Projekt Task: '"+pt_id+"' ne postoji.");
        }

        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Projekt Tasks '"+pt_id+"' ne postoji u Projektu: '"+backlog_id+"'");
        }

        return  projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id,String pt_id,String username){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id,username);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    public void deletePTbyProjectSequence(String backlog_id,String pt_id, String username){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id,username);

        projectTaskRepository.delete(projectTask);
    }
}
