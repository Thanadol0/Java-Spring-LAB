package demo.spring_web_flux_LAB.service;

import demo.spring_web_flux_LAB.dto.RequestStudent;
import demo.spring_web_flux_LAB.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Flux<Student> getAllStudent();

    Mono<Student> createStudent(RequestStudent body);

    Mono<Student> updateStudent(RequestStudent body, Integer id     );

    Mono<Void> deleteStudent(Integer id);

}
