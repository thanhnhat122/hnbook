package hcmute.edu.hnlbook.service;

import hcmute.edu.hnlbook.dto.CustomerBookDTO;
import hcmute.edu.hnlbook.dto.SimpleCustomerBookDTO;
import hcmute.edu.hnlbook.model.Book;
import hcmute.edu.hnlbook.model.Filter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {
  List<Book> getAll();

  Optional<Book> findById(int id);

  CustomerBookDTO getCustomerBookDTO(Integer id);

  List<SimpleCustomerBookDTO> getAllSimpleCustomerBookDTO();

  List<SimpleCustomerBookDTO> getSimpleCustomerBookDTOInSameSeries(String title);

  List<SimpleCustomerBookDTO> getSimpleCustomerBookDTOByGenre(Book.genreEnum genre);

  String uploadImage(String isbn, String number, MultipartFile file);

  Book insert(Book book);

  Book update(Book book, int id);

  boolean existsById(int id);

  void deleteById(int id);

  List<Book> findAllByPrice(int minPrice, int maxPrice);

  List<Book> findAllByPublisher(int publisherID);

  List<Book> findAllByAuthor(int authorID);

  List<Book> findAllByGenre(Book.genreEnum genre);

  List<Book> findAllByType(Book.typeEnum type);

  List<Book> findAllByPublicationYear(int publicationYear);

  List<Book> filterByAllBook(
      int minPrice,
      int maxPrice,
      int publisherID,
      int authorID,
      Book.genreEnum genre,
      Book.typeEnum type,
      int publicationYear);

  Object addItemToCart(String email, int bookId, int quantity);

  List<SimpleCustomerBookDTO> filterAllBook(Filter filter);

  List<SimpleCustomerBookDTO> filterForSearch(Filter filter);

  int getTotalBookSold(int month, int year);

  double getTotalBookPriceSold(int month, int year);

  List<Book> getBestSeller(int month, int year);

  List<Book.genreEnum> getBestGenre(int month, int year);

  List<Integer> getNumOfBestSeller(int month, int year);

  List<Integer> getNumOfBestGenre(int month, int year);

  List<Double> getTotalPriceForEachGenre(int month, int year);

  List<Integer> getBookSoldForYear(int year);

  int getTotalBookSoldInYear(int year);

  double getTotalPriceInYear(int year);

  List<Book> findBook(String name);

}
