package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import io.agileintelligence.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){



        try{
            //PTs to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            //set the backlog to pt
            projectTask.setBacklog(backlog);
            //we want our project sequence tobe like this: IDPRO-1, IDPRO-2....
            Integer backlogSequence = backlog.getPTSequence();
            // Update the BL SEQUENCE
            backlogSequence++;

            projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            backlog.setPTSequence(backlogSequence);

            //INITIAL priority when priority is null, refactor to enums
            if(projectTask.getPriority() == null ){
                projectTask.setPriority(3);
            }
            //INITIAL status when status is null
            if(projectTask.getStatus()== null || projectTask.getStatus()==""){
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project not Found");
        }


    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {

        Project project = projectRepository.findByProjectIdentifier(backlog_id);

        if(project == null){
            throw new ProjectNotFoundException("Project with ID: "+backlog_id+" does not exists");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }
}
