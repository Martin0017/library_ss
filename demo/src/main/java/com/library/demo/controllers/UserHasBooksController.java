package com.library.demo.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
import com.library.demo.models.Book;
import com.library.demo.models.User;
import com.library.demo.models.UserHasBooks;
import com.library.demo.services.book.BookService;
import com.library.demo.services.user.UserService;
import com.library.demo.services.userhasbooks.UserHasBooksService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/userhasbooks") // Definir la ruta base para todos los endpoints en este controlador
public class UserHasBooksController {

    private UserHasBooksService service;
    private UserService serviceUser;
    private BookService serviceBook;

    public UserHasBooksController(UserHasBooksService service, UserService serviceUser, BookService serviceBook) {
        this.service = service;
        this.serviceUser = serviceUser;
        this.serviceBook = serviceBook;
    }

    @GetMapping
    public ResponseEntity<Iterable<UserHasBooks>> getAllUserHasBooks() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserHasBooks> getUserHasBooksById(@PathVariable Long id) {
        Optional<UserHasBooks> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(o.get());
    }

    @GetMapping("/page")
    public ResponseEntity<Iterable<UserHasBooks>> getAllUserHasBooksByPage(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<UserHasBooks> createBook(@Valid @RequestBody UserHasBooks userHasBooks,
            BindingResult result) throws NotFoundException {
        if (result.hasErrors()) {
            ResponseEntity.badRequest().body("Error en la validación del libro.");
        }

        Long bookId = userHasBooks.getIdBook();
        Long userId = userHasBooks.getIdUser();

        Optional<Book> bookOptional = serviceBook.findById(bookId);
        Optional<User> userOptional = serviceUser.findById(userId);

        if (userOptional.isEmpty()) {
            throw new NotFoundException();
        }
        User user = userOptional.get();
        userHasBooks.setUser(user);

        if (bookOptional.isEmpty()) {
            throw new NotFoundException();
        }
        Book book = bookOptional.get();
        userHasBooks.setBook(book);

        // Guardar el libro con el autor asociado
        UserHasBooks savedUserHasBook = service.save(userHasBooks);

        // Devolver una respuesta con el libro creado
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserHasBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserHasBooks> actualizar(@Valid @RequestBody UserHasBooks book, @PathVariable Long id,
            BindingResult result) {
        if (result.hasErrors()) {
            this.validar(result);
        }
        UserHasBooks bookDb = service.update(id, book);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserHasBooks> deleteBookById(@PathVariable Long id) {
        Optional<UserHasBooks> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity<Map<String, Object>> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(
                err -> errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errores);
    }
}
