package hcmute.edu.hnlbook.service;


import hcmute.edu.hnlbook.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
  List<Author> getAll();

  Optional<Author> findById(int id);

  Author insert(Author author);

  Author update(Author author, int id);

  boolean existsById(int id);

  void deleteById(int id);
}
