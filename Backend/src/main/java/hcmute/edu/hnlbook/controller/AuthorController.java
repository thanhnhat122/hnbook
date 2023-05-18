package hcmute.edu.hnlbook.controller;

import hcmute.edu.hnlbook.model.Author;
import hcmute.edu.hnlbook.model.DataResponse;
import hcmute.edu.hnlbook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Author")
public class AuthorController {
  @Autowired
  private AuthorService authorService;

  @GetMapping("")
  DataResponse getAllAuthor() {
    List<Author> listAuthor = authorService.getAll();
    return new DataResponse(listAuthor);
  }

  @GetMapping("/{id}")
  DataResponse findAuthorById(@PathVariable Integer id) {
    Optional<Author> foundAuthor = authorService.findById(id);
    if (foundAuthor.isPresent())
      return new DataResponse(foundAuthor);
    else
      throw new RuntimeException("Cannot find author with id = " + id);
  }

  @PostMapping("")
  DataResponse insertAuthor(@RequestBody @Validated Author newAuthor, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(authorService.insert(newAuthor));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @PutMapping("")
  DataResponse updateAuthor(@RequestBody @Validated Author newAuthor, BindingResult result, @RequestParam Integer id) {
    if (!result.hasErrors()){
      Author updateAuthor = authorService.update(newAuthor, id);
      return new DataResponse(updateAuthor);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @DeleteMapping("")
  DataResponse deleteAuthor(@RequestParam Integer id) {
    boolean exists = authorService.existsById(id);
    if (exists) {
      authorService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find author with id = " + id + " to delete");
  }
}
