package hcmute.edu.hnlbook.service.impl;

import hcmute.edu.hnlbook.model.Author;
import hcmute.edu.hnlbook.repository.AuthorRepository;
import hcmute.edu.hnlbook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
  @Autowired
  private AuthorRepository authorRepository;


  @Override
  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> findById(int id) {
    return authorRepository.findById(id);
  }

  @Override
  public Author insert(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public Author update(Author newAuthor, int id) {
    return authorRepository
        .findById(id)
        .map(
            author -> {
              author.setFirstName(newAuthor.getFirstName());
              author.setLastName(newAuthor.getLastName());
              return authorRepository.save(author);
            })
        .orElseGet(
            () -> authorRepository.save(newAuthor));
  }

  @Override
  public boolean existsById(int id) {
    return authorRepository.existsById(id);
  }

  @Override
  public void deleteById(int id) {
    authorRepository.deleteById(id);
  }
}
