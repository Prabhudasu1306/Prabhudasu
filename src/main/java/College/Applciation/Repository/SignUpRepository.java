package College.Applciation.Repository;

import College.Applciation.Model.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignUpRepository extends JpaRepository<SignUp,Integer> {
}
