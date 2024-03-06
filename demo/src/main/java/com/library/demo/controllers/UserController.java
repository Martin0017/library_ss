package com.library.demo.controllers;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.demo.models.User;
import com.library.demo.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok().body(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> o = service.findById(id);
        if(!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().body(o.get());
    }

    @GetMapping("/page")
    public ResponseEntity<Iterable<User>> getAllUsersByPage(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            this.validar(result);
        }
        User userDb = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDb);
    }

    @PostMapping("/byemail")
    public ResponseEntity<Optional<User>> findByEmailUser(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            this.validar(result);
        }
        Optional<User> userDb = service.findByEmail(user.getEmailUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDb);
    }
    

    @PutMapping("/{id}") 
    public ResponseEntity<User> actualizar(@Valid @RequestBody User user, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            this.validar(result);
        }
        User userDb = service.update(id, user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id){
        Optional<User> o = service.findById(id);
        if(!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity<Map<String, Object>> validar(BindingResult result){
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> 
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage() )
        );

        return ResponseEntity.badRequest().body(errores);
    }
}
