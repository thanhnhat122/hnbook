package hcmute.edu.hnlbook.repository;

import hcmute.edu.hnlbook.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
