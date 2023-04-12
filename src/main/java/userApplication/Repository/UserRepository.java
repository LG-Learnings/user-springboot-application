package userApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import userApplication.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
