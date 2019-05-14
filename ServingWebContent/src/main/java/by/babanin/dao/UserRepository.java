package by.babanin.dao;

import by.babanin.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
