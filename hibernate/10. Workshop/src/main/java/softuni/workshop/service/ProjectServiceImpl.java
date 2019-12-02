package softuni.workshop.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import softuni.workshop.domain.dtos.projectDtos.BaseProjectDto;
import softuni.workshop.domain.dtos.projectDtos.ProjectDto;
import softuni.workshop.domain.entities.Project;
import softuni.workshop.repository.ProjectRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.FileUtilImpl;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.ValidatorUtilImpl;
import softuni.workshop.util.XmlParser;
import softuni.workshop.util.XmlParserImpl;


@Service
public class ProjectServiceImpl implements ProjectService {

	private static final String FILE_PATH = "C:\\Users\\OK\\workspace\\workshop-skeleton\\src\\main\\resources\\files\\xmls\\projects.xml";
	
	@Autowired
	private ProjectRepository projectRepository;
	
	private FileUtil fileUtil;
	private ValidatorUtil validatorUtil;
	private XmlParser xmlParser;
	private ModelMapper ModelMapper;
	
	
	@Autowired
	public ProjectServiceImpl() {
		this.fileUtil = new FileUtilImpl();
		this.validatorUtil = new ValidatorUtilImpl();
		this.xmlParser = new XmlParserImpl();
		this.ModelMapper = new ModelMapper();
	}
	
	
    public void importProjects() throws FileNotFoundException, JAXBException{
         BufferedReader bf = this.fileUtil.returnReadXmlFile(FILE_PATH);
         
         BaseProjectDto baseProjectDto = (BaseProjectDto) this.xmlParser.getUnmarshaller(bf, BaseProjectDto.class);
         
         	baseProjectDto.getAllProjects().stream().forEach(project -> {
         		Project currentProject = this.ModelMapper.map(project, Project.class);
         			if(this.validatorUtil.validateObject(currentProject)) {
         				this.projectRepository.saveAndFlush(currentProject);
         			}
         	});   
    }


    public boolean areImported() {
       return this.projectRepository.findAll().size() > 0;
    }

    
    public String readProjectsXmlFile() throws JAXBException, IOException { 
       BufferedReader bf = this.fileUtil.returnReadXmlFile(FILE_PATH);
       
       StringBuilder sb = new StringBuilder();
       String line = "";
       while((line = bf.readLine()) != null) {
    	   sb.append(line).append(System.lineSeparator());
       }
      
      return sb.toString();
    }

    
    public String exportFinishedProjects(){
    	StringBuilder sBuilder = new StringBuilder();
    	
    	this.projectRepository.findAllByIsFinishedIsTrue().forEach(project -> {
    		ProjectDto projectDto = this.ModelMapper.map(project, ProjectDto.class);
    		sBuilder.append(projectDto.toString());
    	});
        return sBuilder.toString();
    }
    
    
    
}



















