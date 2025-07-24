package demo.spring_web_flux_LAB.controller;

import demo.spring_web_flux_LAB.dto.RequestStudent;
import demo.spring_web_flux_LAB.model.Student;
import demo.spring_web_flux_LAB.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService svc;

    public StudentController(StudentService svc) {
        this.svc = svc;
    }

    @GetMapping
    public Flux<Student> getAll() {
        log.debug("HTTP GET /student");
        return svc.getAllStudent();
    }

    @PostMapping
    public Mono<ResponseEntity<Student>> create(@RequestBody @Validated RequestStudent body) {
        log.debug("HTTP POST /student – payload={}", body);
        return svc.createStudent(body)
                .map(st -> ResponseEntity.created(URI.create("/student/" + st.getId()))
                        .body(st));
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<Student>> update(@PathVariable Integer id,
                                                @RequestBody @Validated RequestStudent body) {
        log.debug("HTTP PATCH /student/{}", id);
        return svc.updateStudent(body, id).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id) {
        log.debug("HTTP DELETE /student/{}", id);
        return svc.deleteStudent(id)
                .thenReturn(ResponseEntity.noContent().build());
    }


}