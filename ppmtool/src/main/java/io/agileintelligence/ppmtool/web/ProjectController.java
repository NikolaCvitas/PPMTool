package io.agileintelligence.ppmtool.web;


import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    private static Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        log.debug(project.toString());

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null){
            log.debug("errorMap: "+errorMap.toString());
            return errorMap;
        }

        Project project1 = projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public  List<Project> findAll(){
       return (List<Project>) projectService.findAll();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){

        Project project = projectService.findProjectByIdentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProjectByIdentifier(@PathVariable String projectId){

        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with Id "+projectId+ " is deleted.", HttpStatus.OK);
    }



}
