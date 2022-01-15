package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.Task;
import expertostech.autenticacao.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(Integer id);
}
