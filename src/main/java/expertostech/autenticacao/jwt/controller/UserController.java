package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.User;
import expertostech.autenticacao.jwt.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserController(UserRepository repository, PasswordEncoder encoder) {
        this.userRepository = repository;
        this.encoder = encoder;
    }

    //@CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody  User user){

        if (!userRepository.findByEmail(user.getEmail()).isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já existente");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<User> put(@RequestBody User user, Principal principal){
        String email = principal.getName();
        Optional<User> userUpdate = userRepository.findByEmail(email);

        User newUser = userUpdate.get();

        BeanUtils.copyProperties(user, newUser, "id", "email", "password");

        return ResponseEntity.ok(userRepository.save(newUser));
    }

}
