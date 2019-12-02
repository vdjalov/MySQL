package softuni.workshop.domain.dtos.projectDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseProjectDto {

	@XmlElement(name = "project")
	private List<ProjectDto> allProjects;
	
	public BaseProjectDto() {
		this.setAllProjects(new ArrayList<ProjectDto>());
	}

	public List<ProjectDto> getAllProjects() {
		return allProjects;
	}

	public void setAllProjects(List<ProjectDto> allProjects) {
		this.allProjects = allProjects;
	}
	
}
