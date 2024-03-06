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
import com.library.demo.models.Book;
import com.library.demo.services.author.AuthorService;
import com.library.demo.services.book.BookService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books") // Definir la ruta base para todos los endpoints en este controlador
public class BookController {

    private BookService service;
    private AuthorService serviceAuthor;
    
    public BookController(BookService service, AuthorService serviceAuthor) {
        this.service = service;
        this.serviceAuthor = serviceAuthor;
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Book>> getAllBooks(){
        return ResponseEntity.ok().body(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> o = service.findById(id);
        if(!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(o.get());
    }

    @GetMapping("/page")
    public ResponseEntity<Iterable<Book>> getAllBooksByPage(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book, BindingResult result) {
        // Verificar si hay errores de validación en el libro recibido
        if (result.hasErrors()) {
            // Si hay errores de validación, manejarlos y devolver una respuesta de error
            ResponseEntity.badRequest().body("Error en la validación del libro.");
        }
    
        Long authorId = book.getIdAuthor();
        Optional<Author> authorOptional = serviceAuthor.findById(authorId);
        if (authorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Author author = authorOptional.get();
        book.setAuthor(author);
        Book savedBook = service.save(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    
    

    @PutMapping("/{id}") 
    public ResponseEntity<Book> actualizar(@Valid @RequestBody Book book, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            this.validar(result);
        }
        Long authorId = book.getIdAuthor();
        Optional<Author> authorOptional = serviceAuthor.findById(authorId);
        if (authorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Author author = authorOptional.get();
        book.setAuthor(author);
        Book bookDb = service.update(id, book);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id){
        Optional<Book> o = service.findById(id);
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
