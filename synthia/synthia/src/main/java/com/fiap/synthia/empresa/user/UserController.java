package com.fiap.synthia.empresa.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import com.fiap.synthia.empresa.token.AuthService;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository repository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        authService.registerUser(user);
        return "redirect:/login";
    }

    
    @GetMapping("{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> users = repository.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        verificarseexisteUser(id);
        user.setId(id);
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        verificarseexisteUser(id);
        repository.deleteById(id);
        return ResponseEntity.ok("User deletado com sucesso");
    }

    private void verificarseexisteUser(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "id do User não encontrado"));
    }
}
