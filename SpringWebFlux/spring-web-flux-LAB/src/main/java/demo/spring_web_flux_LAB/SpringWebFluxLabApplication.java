package demo.spring_web_flux_LAB;

import demo.spring_web_flux_LAB.model.Student;
import demo.spring_web_flux_LAB.repository.StudentReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringWebFluxLabApplication{

//	@Autowired
//	StudentReactiveRepository studentReactiveRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxLabApplication.class, args);
	}

//	CommandlineRunner Test
//	@Override
//	public void run(String... args) throws Exception {
//		Flux<Student> studentFlux = studentReactiveRepository.findAll();
//		studentFlux.subscribe(System.out::println);
//	}


}
