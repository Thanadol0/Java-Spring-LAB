package demo.spring_web_flux_LAB.repository;

import demo.spring_web_flux_LAB.model.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentReactiveRepository extends ReactiveCrudRepository<Student,Integer> {

    @Query("""
           SELECT s.id, s.name, s.age
           FROM   public.student  AS s
                  CROSS JOIN LATERAL pg_sleep(2)
           """)
    public Flux<Student> findAllStudents();

}
