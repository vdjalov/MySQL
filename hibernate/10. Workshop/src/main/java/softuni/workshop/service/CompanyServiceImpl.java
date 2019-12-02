package softuni.workshop.service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import softuni.workshop.domain.dtos.companyDtos.BaseCompanyDto;
import softuni.workshop.domain.entities.Company;
import softuni.workshop.repository.CompanyRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.FileUtilImpl;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.ValidatorUtilImpl;
import softuni.workshop.util.XmlParser;
import softuni.workshop.util.XmlParserImpl;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final String FILE_PATH = "C:\\Users\\OK\\workspace\\workshop-skeleton\\src\\main\\resources\\files\\xmls\\companies.xml"; 
	
	@Autowired
	private CompanyRepository companyRepository;
	
	private FileUtil fileUtil;
	private XmlParser xmlParser;
	private ModelMapper modelMapper;
	private ValidatorUtil validatorUtil;
	
	@Autowired
	public CompanyServiceImpl() {
		this.fileUtil = new FileUtilImpl();
		this.xmlParser = new XmlParserImpl();
		this.modelMapper = new ModelMapper();
		this.validatorUtil = new ValidatorUtilImpl();
	}
	
	
    public void importCompanies() throws FileNotFoundException, JAXBException {
    	
    	BufferedReader bufferedReader = this.fileUtil.returnReadXmlFile(FILE_PATH);
    	BaseCompanyDto baseCompanyDto = 
    			(BaseCompanyDto) this.xmlParser.getUnmarshaller(bufferedReader, BaseCompanyDto.class);
    	
    	baseCompanyDto.getAllCompanies().forEach(company -> {
    		Company currentCompany = this.modelMapper.map(company, Company.class);
    		if(this.validatorUtil.validateObject(company)) {
    			this.companyRepository.saveAndFlush(currentCompany);
    		}
    	});
    }

    
    public boolean areImported() {
        return this.companyRepository.findAll().size() > 0;
    }

    
    public String readCompaniesXmlFile() throws IOException {
    	BufferedReader bf = this.fileUtil.returnReadXmlFile(FILE_PATH);
    	
    	StringBuilder sBuilder = new StringBuilder();
    	String line = "";
    	
    	while((line = bf.readLine()) != null) {
    		sBuilder.append(line).append(System.lineSeparator());
    	}
    	
        return sBuilder.toString();
    }
}
