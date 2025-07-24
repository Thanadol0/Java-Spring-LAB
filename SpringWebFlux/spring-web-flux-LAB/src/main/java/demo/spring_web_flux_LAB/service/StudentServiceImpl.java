package demo.spring_web_flux_LAB.service;

import demo.spring_web_flux_LAB.dto.RequestStudent;
import demo.spring_web_flux_LAB.model.Student;
import demo.spring_web_flux_LAB.repository.StudentReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentReactiveRepository repo;

    public StudentServiceImpl(StudentReactiveRepository repo) {
        this.repo = repo;
    }

    private <T> Mono<T> withTrace(Mono<T> mono) {
        String traceId = UUID.randomUUID().toString();
        return mono.doOnSubscribe(__ -> MDC.put("traceId", traceId))
                .doFinally(__ -> MDC.clear());
    }

    @Override
    public Flux<Student> getAllStudent() {
        return repo.findAll();
    }

    @Override
    public Mono<Student> createStudent(RequestStudent body) {
        Student s = new Student(null, body.name(), body.age());
        return withTrace(repo.save(s)
                .doOnSuccess(st -> log.info("CREATE student id={} name={}", st.getId(), st.getName())));
    }

    @Override
    public Mono<Student> updateStudent(RequestStudent body, Integer id) {
        return withTrace(
                repo.findById(id)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Student not found: " + id)))
                        .map(old -> new Student(
                                old.getId(),
                                body.name(),
                                body.age()
                        ))
                        .flatMap(repo::save)
                        .doOnSuccess(st -> log.info("UPDATE id: {}", st.getId()
                        ))
        );
    }

    @Override
    public Mono<Void> deleteStudent(Integer id) {
        return withTrace(
                repo.findById(id)
                        .flatMap(repo::delete)
                        .doOnSuccess( __ -> log.info("DELETE id={} success", id))
        );
    }
}
