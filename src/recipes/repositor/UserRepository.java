package recipes.repositor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailIgnoreCase(String email);

}
