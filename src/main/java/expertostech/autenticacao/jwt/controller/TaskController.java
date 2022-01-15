package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.Task;
import expertostech.autenticacao.jwt.model.User;
import expertostech.autenticacao.jwt.repository.TaskRepository;
import expertostech.autenticacao.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/teste")
    public String getString(){
        return "Acessou";
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Task task, Principal principal){
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);

        task.setUser(user.get());
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(Principal principal){
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);

        return ResponseEntity.ok(taskRepository.findByUserId(user.get().getId()));
    }



}
