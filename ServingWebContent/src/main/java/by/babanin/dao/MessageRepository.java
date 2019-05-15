package by.babanin.dao;

import by.babanin.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    Iterable<Message> findByTag(String tag);
}
