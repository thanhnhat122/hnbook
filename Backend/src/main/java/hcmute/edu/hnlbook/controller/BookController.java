package hcmute.edu.hnlbook.controller;

import hcmute.edu.hnlbook.dto.CustomerBookDTO;
import hcmute.edu.hnlbook.dto.SimpleCustomerBookDTO;
import hcmute.edu.hnlbook.model.Book;
import hcmute.edu.hnlbook.model.DataResponse;
import hcmute.edu.hnlbook.model.Filter;
import hcmute.edu.hnlbook.model.Order;
import hcmute.edu.hnlbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Book")
public class BookController {
  @Autowired private BookService bookService;

  @GetMapping("")
  DataResponse getAllBook() {
    List<Book> listBook = bookService.getAll();
    return new DataResponse(listBook);
  }

  @GetMapping("/{id}")
  DataResponse findBookById(@PathVariable Integer id) {
    Optional<Book> foundBook = bookService.findById(id);
    if (foundBook.isPresent()) {
      return new DataResponse(foundBook);
    } else {
      throw new RuntimeException("Cannot find book with id = " + id);
    }
  }

  @GetMapping("/customer/{id}")
  DataResponse getCustomerBookDTOById(@PathVariable Integer id) {
    CustomerBookDTO foundBook = bookService.getCustomerBookDTO(id);
    if (foundBook != null) {
      return new DataResponse(foundBook);
    } else {
      throw new RuntimeException("Cannot find book with id = " + id);
    }
  }

  @GetMapping("/getAllSimpleBook")
  DataResponse getAllSimpleCustomerBookDTO() {
    List<SimpleCustomerBookDTO> books = bookService.getAllSimpleCustomerBookDTO();
    return new DataResponse(books);
  }

  @GetMapping("/findSimpleBookInSameSeries")
  DataResponse getSimpleCustomerBookDTOInSameSeries(@RequestParam String title) {
    List<SimpleCustomerBookDTO> books = bookService.getSimpleCustomerBookDTOInSameSeries(title);
    return new DataResponse(books);
  }

  @GetMapping("/findSimpleBookByGenre")
  DataResponse getSimpleCustomerBookDTOByGenre(@RequestParam Book.genreEnum genre) {
    List<SimpleCustomerBookDTO> books = bookService.getSimpleCustomerBookDTOByGenre(genre);
    return new DataResponse(books);
  }

  @PostMapping("")
  DataResponse insertBook(@RequestBody @Validated Book newBook, BindingResult result) {
    if (!result.hasErrors()) {
      return new DataResponse(bookService.insert(newBook));
    } else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @PostMapping("/uploadImage")
  DataResponse saveImage(
      @RequestParam("isbn") String isbn, @RequestParam("number") String number, @RequestParam("file") MultipartFile file) {
    return new DataResponse(bookService.uploadImage(isbn, number, file));
  }

  @PutMapping("")
  DataResponse updateBook(
      @RequestBody @Validated Book newBook, BindingResult result, @RequestParam Integer id) {
    if (!result.hasErrors()) {
      Book updateBook = bookService.update(newBook, id);
      return new DataResponse(updateBook);
    } else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @DeleteMapping("")
  DataResponse deleteBook(@RequestParam Integer id) {
    boolean exists = bookService.existsById(id);
    if (exists) {
      bookService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find book with id = " + id + " to delete");
  }

  @GetMapping("/filter")
  DataResponse bookFilter(
      @RequestParam(defaultValue = "0") int minPrice,
      @RequestParam(defaultValue = "2147483647") int maxPrice,
      @RequestParam(defaultValue = "0") int publisherID,
      @RequestParam(defaultValue = "0") int authorID,
      @RequestParam(defaultValue = "NONE") Book.genreEnum genre,
      @RequestParam(defaultValue = "NONE") Book.typeEnum type,
      @RequestParam(defaultValue = "0") int publicationYear) {
    List<Book> bookList = new ArrayList<>();

    // List of book filter
    List<Book> filterByPrice = bookService.findAllByPrice(minPrice, maxPrice);
    List<Book> filterByPublisher = bookService.findAllByPublisher(publisherID);
    List<Book> filterByAuthor = bookService.findAllByAuthor(authorID);
    List<Book> filterByGenre = bookService.findAllByGenre(genre);
    List<Book> filterByType = bookService.findAllByType(type);
    List<Book> filterByPublicationYear = bookService.findAllByPublicationYear(publicationYear);

    // Add books filter to book list
    bookList.addAll(filterByPrice);
    bookList.addAll(filterByPublisher);
    bookList.addAll(filterByAuthor);
    bookList.addAll(filterByGenre);
    bookList.addAll(filterByType);
    bookList.addAll(filterByPublicationYear);

    return new DataResponse(bookList);
  }

  @GetMapping("/filterAllBook")
  DataResponse bookFilterAll(
      @RequestParam(defaultValue = "0") int minPrice,
      @RequestParam(defaultValue = "2147483647") int maxPrice,
      @RequestParam(defaultValue = "0") int publisherID,
      @RequestParam(defaultValue = "0") int authorID,
      @RequestParam(defaultValue = "NONE") Book.genreEnum genre,
      @RequestParam(defaultValue = "NONE") Book.typeEnum type,
      @RequestParam(defaultValue = "0") int publicationYear) {
    List<Book> bookList =
        bookService.filterByAllBook(minPrice, maxPrice, publisherID, authorID, genre, type, publicationYear);
    return new DataResponse(bookList);
  }

  @PostMapping("/addItemToCart")
  DataResponse addItemToCart(
      @RequestParam String email, @RequestParam int bookId, @RequestParam int quantity) {
    return new DataResponse(bookService.addItemToCart(email, bookId, quantity));
  }
  @PostMapping("/filter")
  DataResponse filterBook(@RequestBody Filter filter){
    List<SimpleCustomerBookDTO> bookList = bookService.filterAllBook(filter);
    if (bookList.size() > 0) {
      return new DataResponse(bookList);
    } else {
      throw new RuntimeException("Cannot find book");
    }
  }

  @GetMapping("/sold")
  DataResponse getTotalBookSold(@RequestParam int month, @RequestParam int year) {
    int totalBookSold = bookService.getTotalBookSold(month, year);
    return new DataResponse(totalBookSold);
  }

  @GetMapping("/priceSold")
  DataResponse getTotalBookPriceSold(@RequestParam int month, @RequestParam int year) {
    double totalPrice = bookService.getTotalBookPriceSold(month, year);
    return new DataResponse(totalPrice);
  }


  @GetMapping("/bestSeller")
  DataResponse getBestSeller(@RequestParam int month, @RequestParam int year) {
    List<Book> bookList = bookService.getBestSeller(month, year);
    return new DataResponse(bookList);
  }

  @GetMapping("/bestGenre")
  DataResponse getBestGenre(@RequestParam int month, @RequestParam int year) {
    List<Book.genreEnum> genreList = bookService.getBestGenre(month, year);
    return new DataResponse(genreList);
  }

  @GetMapping("/numOfBestSeller")
  DataResponse getNumOfBestSeller(@RequestParam int month, @RequestParam int year) {
    List<Integer> numList = bookService.getNumOfBestSeller(month, year);
    return new DataResponse(numList);
  }

  @GetMapping("/numOfBestGenre")
  DataResponse getNumOfBestGenre(@RequestParam int month, @RequestParam int year) {
    List<Integer> numList = bookService.getNumOfBestGenre(month, year);
    return new DataResponse(numList);
  }

  @GetMapping("/priceForEachGenre")
  DataResponse getTotalPriceForEachGenre(@RequestParam int month, @RequestParam int year) {
    List<Double> numList = bookService.getTotalPriceForEachGenre(month, year);
    return new DataResponse(numList);
  }

  @GetMapping("/bookSoldForYear")
  DataResponse getBookSoldForYear(@RequestParam int year) {
    List<Integer> numList = bookService.getBookSoldForYear(year);
    return new DataResponse(numList);
  }

  @GetMapping("/totalBookSoldInYear")
  DataResponse getTotalBookSoldInYear(@RequestParam int year) {
    int total = bookService.getTotalBookSoldInYear(year);
    return new DataResponse(total);
  }

  @GetMapping("/totalPriceInYear")
  DataResponse getTotalPriceInYear(@RequestParam int year) {
    double total = bookService.getTotalPriceInYear(year);
    return new DataResponse(total);
  }

  @GetMapping("/findBook")
  DataResponse findBook(@RequestParam String title) {
    return new DataResponse(bookService.findBook(title));
  }

  @PostMapping("/filterSearch")
  DataResponse filterBookSearch(@RequestBody Filter filter){
    List<SimpleCustomerBookDTO> bookList = bookService.filterForSearch(filter);
    if (bookList.size() > 0) {
      return new DataResponse(bookList);
    } else {
      throw new RuntimeException("Cannot find book");
    }
  }

}
