package hcmute.edu.hnlbook.repository;

import hcmute.edu.hnlbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
  @Query(value = "SELECT * FROM Book b WHERE b.Price >= :minPrice AND b.Price <= :maxPrice", nativeQuery = true)
  List<Book> findAllByPrice(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);

  @Query(value = "SELECT * FROM Book b WHERE b.PublisherID = :publisherID", nativeQuery = true)
  List<Book> findAllByPublisher(@Param("publisherID") int publisherID);

  @Query(value = "SELECT * FROM Book b WHERE b.AuthorID = :authorID", nativeQuery = true)
  List<Book> findAllByAuthor(@Param("authorID") int authorID);

//  @Query(value = "SELECT * FROM Book b WHERE b.Genre = :genre", nativeQuery = true)
//  List<Book> findAllByGenre(@Param("genre") Book.genreEnum genre);
  List<Book> findAllByGenre(Book.genreEnum genre);

  

  @Query(value = "SELECT * FROM Book b WHERE b.Type = :type", nativeQuery = true)
  List<Book> findAllByType(@Param("type") Book.typeEnum type);

  @Query(value = "SELECT * FROM Book b WHERE b.PublicationYear = :publicationYear", nativeQuery = true)
  List<Book> findAllByPublicationYear(@Param("publicationYear") int publicationYear);

  @Transactional
  @Modifying
  @Query("UPDATE Book SET Image = :image WHERE Isbn = :isbn")
  void updateBookImage(String image, String isbn);

  @Query(value = "SELECT * FROM Book WHERE title LIKE %:title%", nativeQuery = true)
  List<Book> findByTitle(@Param("title") String title);

  @Query(value = "SELECT * FROM Book WHERE title LIKE :title%", nativeQuery = true)
  List<Book> findBookInSameSeries(@Param("title") String title);
}
