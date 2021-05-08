package eu.michalszyba.adrlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AdrListApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(AdrListApplication.class, args);

//
//		List<Company> companies = new ArrayList<>();
//		companies.add(new Company("Michal111", "Siechnice"));
//		companies.add(new Company("Michał", "Siechnice2"));
//
//		CompanyRepository companyRepository = run.getBean(CompanyRepository.class);
//		companies.forEach(companyRepository::save);
//
//		companyRepository.findAll().forEach(System.out::println);
//
//		companyRepository.findByCompanyName("Vidoo").forEach(System.out::println);






	}
}
