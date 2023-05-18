package hcmute.edu.hnlbook.repository;

import hcmute.edu.hnlbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
  User findByEmail(String username);

  User findByPhone(String phone);

  List<User> findByRole(User.RoleEnum roleAd);
}
