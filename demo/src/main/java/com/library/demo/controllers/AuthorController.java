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

import com.library.demo.models.Author;
import com.library.demo.services.author.AuthorService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/authors") // Definir la ruta base para todos los endpoints en este controlador
public class AuthorController {

    private AuthorService service;
    
    public AuthorController(AuthorService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Author>> getAllAuthors(){
        return ResponseEntity.ok().body(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Optional<Author> o = service.findById(id);
        if(!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(o.get());
    }

    @GetMapping("/page")
    public ResponseEntity<Iterable<Author>> getAllAuthorsByPage(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    
    @PostMapping
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author, BindingResult result){
        if(result.hasErrors()){
            this.validar(result);
        }
        Author authorDb = service.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDb);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Author> actualizar(@Valid @RequestBody Author author, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            this.validar(result);
        }
        Author authorDb = service.update(id, author);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authorDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthorById(@PathVariable Long id){
        Optional<Author> o = service.findById(id);
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
