package College.Applciation.Repository;

import College.Applciation.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses,Long> {
}
