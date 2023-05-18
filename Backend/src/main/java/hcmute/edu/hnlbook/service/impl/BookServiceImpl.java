package hcmute.edu.hnlbook.service.impl;

import hcmute.edu.hnlbook.dto.CustomerBookDTO;
import hcmute.edu.hnlbook.dto.SimpleCustomerBookDTO;
import hcmute.edu.hnlbook.model.*;
import hcmute.edu.hnlbook.repository.*;
import hcmute.edu.hnlbook.service.BookService;
import hcmute.edu.hnlbook.service.OrderItemService;
import hcmute.edu.hnlbook.service.OrderService;
import hcmute.edu.hnlbook.service.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static hcmute.edu.hnlbook.model.Order.StatusEnum.Chua_Dat_Hang;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    private String titleSearch = "";

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public CustomerBookDTO getCustomerBookDTO(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            CustomerBookDTO customerBookDTO = modelMapper.map(book, CustomerBookDTO.class);
            customerBookDTO.setAuthor(authorRepository.findById(customerBookDTO.getAuthorId()));
            customerBookDTO.setPublisher(publisherRepository.findById(customerBookDTO.getPublisherId()));
            customerBookDTO.setImageArray(customerBookDTO.getImage().split(", "));
            return customerBookDTO;
        }
        return null;
    }

    public List<SimpleCustomerBookDTO> bookToSimpleCustomerBookDTO(List<Book> books) {
        List<SimpleCustomerBookDTO> bookDTOs = new ArrayList<>();
        if (books.size() > 0) {
            for (Book book : books) {
                SimpleCustomerBookDTO simpleCustomerBookDTO = modelMapper.map(book, SimpleCustomerBookDTO.class);

                List<Review> reviews = reviewRepository.findByBookId(simpleCustomerBookDTO.getId());
                double totalRate = 0.0;
                int ratingNumber = 0;
                for (Review review : reviews) {
                    totalRate = totalRate + review.getRate();
                    ratingNumber++;
                }
                if (ratingNumber != 0) {
                    totalRate = totalRate / ratingNumber;
                }

                simpleCustomerBookDTO.setTotalRate(totalRate);
                simpleCustomerBookDTO.setRatingNumber(ratingNumber);

                simpleCustomerBookDTO.setImage(simpleCustomerBookDTO.getImage().split(", ")[0]);

                bookDTOs.add(simpleCustomerBookDTO);
            }

            return bookDTOs;
        }
        return null;
    }

    @Override
    public List<SimpleCustomerBookDTO> getAllSimpleCustomerBookDTO() {
        List<Book> books = bookRepository.findAll();
        return bookToSimpleCustomerBookDTO(books);
    }

    @Override
    public List<SimpleCustomerBookDTO> getSimpleCustomerBookDTOInSameSeries(String title) {
        List<Book> books = bookRepository.findBookInSameSeries(title);
        return bookToSimpleCustomerBookDTO(books);
    }

    @Override
    public List<SimpleCustomerBookDTO> getSimpleCustomerBookDTOByGenre(Book.genreEnum genre) {
        List<Book> books = bookRepository.findAllByGenre(genre);
        return bookToSimpleCustomerBookDTO(books);
    }

    @Override
    public String uploadImage(String isbn, String number, MultipartFile file) {
        if (!storageService.isImage(file)) {
            throw new RuntimeException("The file is not an image");
        }

        String url = storageService.uploadImage(file, "hnlbook_book/" + isbn + "_" + number);

        if (url.equals("")) {
            throw new RuntimeException("Fail to upload image");
        }

        bookRepository.updateBookImage(url, isbn);
        return url;
    }

    @Override
    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book newBook, int id) {
        return bookRepository
                .findById(id)
                .map(
                        book -> {
                            book.setAuthorId(newBook.getAuthorId());
                            book.setPublisherId(newBook.getPublisherId());
                            book.setTitle(newBook.getTitle());
                            book.setIsbn(newBook.getIsbn());
                            book.setGenre(newBook.getGenre());
                            book.setType(newBook.getType());
                            book.setPublicationYear(newBook.getPublicationYear());
                            book.setPrice(newBook.getPrice());
                            book.setRemain(newBook.getRemain());
                            book.setSell(newBook.getSell());
                            book.setSize(newBook.getSize());
                            book.setPageNumber(newBook.getPageNumber());
                            book.setImage(newBook.getImage());
                            book.setDescription(newBook.getDescription());
                            return bookRepository.save(book);
                        })
                .orElseGet(() -> bookRepository.save(newBook));
    }

    @Override
    public boolean existsById(int id) {
        return bookRepository.existsById(id);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByPrice(int minPrice, int maxPrice) {
        if (minPrice > 0 && maxPrice < 2147483647)
            return bookRepository.findAllByPrice(minPrice, maxPrice);
        return null;
    }

    @Override
    public List<Book> findAllByPublisher(int publisherID) {
        if (publisherID != 0) return bookRepository.findAllByPublisher(publisherID);
        return null;
    }

    @Override
    public List<Book> findAllByAuthor(int authorID) {
        if (authorID != 0) return bookRepository.findAllByAuthor(authorID);
        return null;
    }

    @Override
    public List<Book> findAllByGenre(Book.genreEnum genre) {
        if (!genre.equals(Book.genreEnum.NONE)) return bookRepository.findAllByGenre(genre);
        return null;
    }

    @Override
    public List<Book> findAllByType(Book.typeEnum type) {
        if (!type.equals(Book.typeEnum.NONE)) return bookRepository.findAllByType(type);
        return null;
    }

    @Override
    public List<Book> findAllByPublicationYear(int publicationYear) {
        if (publicationYear != 0) return bookRepository.findAllByPublicationYear(publicationYear);
        return null;
    }

    @Override
    public List<Book> filterByAllBook(
            int minPrice,
            int maxPrice,
            int publisherID,
            int authorID,
            Book.genreEnum genre,
            Book.typeEnum type,
            int publicationYear) {
        List<Book> bookList = bookRepository.findAll();

        if (minPrice != 0) removeNotInCondition(bookList, "minPrice", minPrice);
        if (maxPrice != 0) removeNotInCondition(bookList, "maxPrice", maxPrice);
        if (publisherID != 0) removeNotInCondition(bookList, "publisherID", publisherID);
        if (authorID != 0) removeNotInCondition(bookList, "authorID", authorID);
        if (!genre.equals(Book.genreEnum.NONE)) removeNotInCondition(bookList, "genre", genre.toString());
        if (!type.equals(Book.typeEnum.NONE)) removeNotInCondition(bookList, "type", type.toString());
        if (publicationYear != 0) removeNotInCondition(bookList, "publicationYear", publicationYear);

        return bookList;
    }


    private void removeNotInCondition(List<Book> bookList, String sortBy, int property) {
        int length = bookList.size();
        for (int i = 0; i < length; i++) {
            Book b = bookList.get(i);
            switch (sortBy) {
                case "minPrice" -> {
                    if (b.getPrice() < property) {
                        bookList.remove(b);
                        i -= 1;
                        length = bookList.size();
                    }
                }
                case "maxPrice" -> {
                    if (b.getPrice() > property) {
                        bookList.remove(b);
                        i -= 1;
                        length = bookList.size();
                    }
                }
                case "publisherID" -> {
                    if (b.getPublisherId() != property) {
                        bookList.remove(b);
                        i -= 1;
                        length = bookList.size();
                    }
                }
                case "authorID" -> {
                    if (b.getAuthorId() != property) {
                        bookList.remove(b);
                        i -= 1;
                        length = bookList.size();
                    }
                }
                case "publicationYear" -> {
                    if (b.getPublicationYear() != property) {
                        bookList.remove(b);
                        i -= 1;
                        length = bookList.size();
                    }
                }
            }
        }
    }

    private void removeNotInCondition(List<Book> bookList, String sortBy, String property) {
        int length = bookList.size();
        for (int i = 0; i < length; i++) {
            Book b = bookList.get(i);
            switch (sortBy) {
                case "genre": {
                    if (!b.getGenre().toString().equals(property)) {
                        bookList.remove(b);
                        i -= 1;
                        length = bookList.size();
                    }
                    break;
                }
                case "type": {
                    if (!b.getType().toString().equals(property)) {
                        bookList.remove(b);
                        i -= 1;
                        length = bookList.size();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public Object addItemToCart(String email, int bookId, int quantity) {
        Order order = orderService.getOrder(email);
        if (order != null) {
            OrderItem existOrderItem = orderItemService.findExistBookInOrder(order.getID(), bookId);
            if (existOrderItem != null) {
                OrderItem orderItem = new OrderItem(existOrderItem.getOrderID(), existOrderItem.getBookID(), existOrderItem.getQuantity() + quantity);
                return orderItemService.updateOrderItem(orderItem, existOrderItem.getID());
            }

            OrderItem orderItem = new OrderItem(order.getID(), bookId, quantity);
            return orderItemService.insertOrderItem(orderItem);
        } else {
            order = new Order(email, Chua_Dat_Hang);
            order = orderRepository.save(order);
            OrderItem orderItem = new OrderItem(order.getID(), bookId, quantity);
            return orderItemService.insertOrderItem(orderItem);
        }
    }

    @Override
    public List<SimpleCustomerBookDTO> filterAllBook(Filter filter) {
        List<Book> bookList = bookRepository.findAllByGenre(filter.getGenre());

        if (filter.getMinPrice() != -1) removeNotInCondition(bookList, "minPrice", (int) filter.getMinPrice());
        if (filter.getMaxPrice() != -1) removeNotInCondition(bookList, "maxPrice", (int) filter.getMaxPrice());
        if (filter.getPublicationYear() != -1)
            removeNotInCondition(bookList, "publicationYear", filter.getPublicationYear());
        if (filter.getPublisherIds().size() != 0) checkListPublisher(bookList, filter.getPublisherIds());
        if (filter.getAuthorIds().size() != 0) checkListAuthor(bookList, filter.getAuthorIds());
        if (!filter.getType().equals(Book.typeEnum.NONE))
            removeNotInCondition(bookList, "type", filter.getType().toString());
        return bookToSimpleCustomerBookDTO(bookList);
    }

    public void checkListPublisher(List<Book> bookList, ArrayList<Integer> arrayList) {
        int length = bookList.size();
        int length1 = arrayList.size();
        for (int i = 0; i < length; i++) {
            int temp = 0;
            for (int j = 0; j < length1; j++) {
                if (bookList.get(i).getPublisherId() == arrayList.get(j)) {
                    temp = 1;
                    break;
                }
            }
            if (temp == 0) {
                bookList.remove(bookList.get(i));
                i -= 1;
                length = bookList.size();
            }

        }
    }

    public void checkListAuthor(List<Book> bookList, ArrayList<Integer> arrayList) {
        int length = bookList.size();
        int length1 = arrayList.size();
        for (int i = 0; i < length; i++) {
            int temp = 0;
            for (int j = 0; j < length1; j++) {
                if (bookList.get(i).getAuthorId() == arrayList.get(j)) {
                    temp = 1;
                    break;
                }
            }
            if (temp == 0) {
                bookList.remove(bookList.get(i));
                i -= 1;
                length = bookList.size();
            }

        }
    }

    @Override
    public int getTotalBookSold(int month, int year) {
        List<Order> orderList = getConfirmedOrdersAndValidDate(month, year);

        //get total books in order list
        int totalBookSold = 0;
        for (Order order : orderList) {
            //get order item list by order id
            List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getID());
            for (OrderItem orderItem : orderItemList) {
                // plus quantity per order item in one order
                totalBookSold += orderItem.getQuantity();
            }
        }

        return totalBookSold;
    }

    @Override
    public double getTotalBookPriceSold(int month, int year) {
        List<Order> orderList = getConfirmedOrdersAndValidDate(month, year);

        //get total books in order list
        double totalPrice = 0;
        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getID());
            for (OrderItem orderItem : orderItemList) {
                //get book by book id of order item
                Optional<Book> book = bookRepository.findById(orderItem.getBookID());
                int quantity = orderItem.getQuantity();
                if (book.isPresent()) {
                    // plus price to totalPrice
                    totalPrice = totalPrice + (book.get().getPrice() * quantity);
                }
            }
        }
        return totalPrice;
    }

    private List<Order> getConfirmedOrdersAndValidDate(int month, int year) {
        List<Order> orderList = orderRepository.findAll();
        //get "Đã nhận" order list
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            int id = order.getID();
            if (order.getStatus() != Order.StatusEnum.Da_Nhan) {
                orderList.remove(order);
                i--;
            }
        }

        //get order list in month and year
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            int orderMonth = order.getOrderDate().getMonth() + 1;
            int orderYear = order.getOrderDate().getYear() + 1900;
            if (orderMonth != month || orderYear != year) {
                orderList.remove(order);
                i--;
            }
        }

        return orderList;
    }

    @Override
    public List<Book> getBestSeller(int month, int year) {
        List<Order> orderList = getConfirmedOrdersAndValidDate(month, year);

        // get map list <bookId, quantity>
        Map<Integer, Integer> map = new HashMap<>();
        for (Order order : orderList) {
            //get order item list by order id
            List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getID());
            for (OrderItem orderItem : orderItemList) {
               if(!map.containsKey(orderItem.getBookID())) {
                   map.put(orderItem.getBookID(), orderItem.getQuantity());
               }
               else {
                   int newQuantity = map.get(orderItem.getBookID()) + orderItem.getQuantity();
                   map.put(orderItem.getBookID(), newQuantity);
               }
            }
        }

        // sort map
        List<Integer> sortList = new ArrayList<>(map.values());
        sortList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

//        System.out.println("Map" + map);
//        System.out.println("Sort list: " + sortList);

        // add value to book list
        List<Book> bookList = new ArrayList<>();
        for (Integer item: sortList)
        {
            for (Map.Entry<Integer, Integer> mapValue : map.entrySet()) {
                if(Objects.equals(mapValue.getValue(), item)) {
                    Optional<Book> book = bookRepository.findById(mapValue.getKey());
                    book.ifPresent(bookList::add);
                    map.remove(mapValue.getKey());
                    break;
                }
            }
        }

        return bookList;
    }

    private Comparator<Book> sortDecreaseBookSold() {

        return new Comparator<>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getSell() > o2.getSell()) {
                    return -1;
                } else if (o1.getSell() < o2.getSell()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }

    @Override
    public List<Book.genreEnum> getBestGenre(int month, int year) {
        List<Order> orderList = getConfirmedOrdersAndValidDate(month, year);

        //get total books in order list
        Map<Book.genreEnum, Integer> genreMap = new HashMap<>();
        List<Book> bestSellerList = new ArrayList<>();
        for (Order order : orderList) {
            //get order item list by order id
            List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getID());
            for (OrderItem orderItem : orderItemList) {
                // add all book to bestSellerList
                Optional<Book> book = bookRepository.findById(orderItem.getBookID());
                if (book.isPresent()) {
                    Book tempBook = book.get();
                    Book.genreEnum genre = book.get().getGenre();
                    int quantity = orderItem.getQuantity();
                    // if tempBook isn't existed in bestSellerList -> add to bestSellerList.
                    /*if (!bestSellerList.contains(tempBook)) {
                        bestSellerList.add(tempBook);
                        // add key (genre) and value (sell) to map
                        // if key is exist, plus value
                        if (!genreMap.containsKey(tempBook.getGenre())) {
                            genreMap.put(tempBook.getGenre(), tempBook.getSell());
                        }
                        else {
                            int value = genreMap.get(tempBook.getGenre()) + tempBook.getSell();
                            genreMap.put(tempBook.getGenre(), value);
                        }*/

                    if(!genreMap.containsKey(genre)) {
                        genreMap.put(genre, quantity);
                    }
                    else {
                        int newQuantity = genreMap.get(genre) + quantity;
                        genreMap.put(genre, newQuantity);
                    }
                }
            }
        }

        List<Integer> sortList = new ArrayList<>(genreMap.values());
        sortList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

//        System.out.println("Map" + genreMap);
//        System.out.println("Sort list: " + sortList);

        List<Book.genreEnum> sortedGenre = new ArrayList<>();
        for (Integer item: sortList)
        {
            for (Map.Entry<Book.genreEnum, Integer> mapValue : genreMap.entrySet()) {
                if(Objects.equals(mapValue.getValue(), item)) {
                    sortedGenre.add(mapValue.getKey());
                }
            }
        }

        return sortedGenre;
    }

    @Override
    public List<Integer> getNumOfBestSeller(int month, int year) {
        List<Order> orderList = getConfirmedOrdersAndValidDate(month, year);

        // get map list <bookId, quantity>
        Map<Integer, Integer> map = new HashMap<>();
        for (Order order : orderList) {
            //get order item list by order id
            List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getID());
            for (OrderItem orderItem : orderItemList) {
                if(!map.containsKey(orderItem.getBookID())) {
                    map.put(orderItem.getBookID(), orderItem.getQuantity());
                }
                else {
                    int newQuantity = map.get(orderItem.getBookID()) + orderItem.getQuantity();
                    map.put(orderItem.getBookID(), newQuantity);
                }
            }
        }

        // sort map
        List<Integer> sortList = new ArrayList<>(map.values());
        sortList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        return sortList;
    }

    @Override
    public List<Integer> getNumOfBestGenre(int month, int year) {
        List<Order> orderList = getConfirmedOrdersAndValidDate(month, year);

        //get total books in order list
        Map<Book.genreEnum, Integer> genreMap = new HashMap<>();
        List<Book> bestSellerList = new ArrayList<>();
        for (Order order : orderList) {
            //get order item list by order id
            List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getID());
            for (OrderItem orderItem : orderItemList) {
                // add all book to bestSellerList
                Optional<Book> book = bookRepository.findById(orderItem.getBookID());
                if (book.isPresent()) {
                    Book tempBook = book.get();
                    Book.genreEnum genre = book.get().getGenre();
                    int quantity = orderItem.getQuantity();
                    // if tempBook isn't existed in bestSellerList -> add to bestSellerList.
                    /*if (!bestSellerList.contains(tempBook)) {
                        bestSellerList.add(tempBook);
                        // add key (genre) and value (sell) to map
                        // if key is exist, plus value
                        if (!genreMap.containsKey(tempBook.getGenre())) {
                            genreMap.put(tempBook.getGenre(), tempBook.getSell());
                        }
                        else {
                            int value = genreMap.get(tempBook.getGenre()) + tempBook.getSell();
                            genreMap.put(tempBook.getGenre(), value);
                        }*/

                    if(!genreMap.containsKey(genre)) {
                        genreMap.put(genre, quantity);
                    }
                    else {
                        int newQuantity = genreMap.get(genre) + quantity;
                        genreMap.put(genre, newQuantity);
                    }
                }
            }
        }

        List<Integer> sortList = new ArrayList<>(genreMap.values());
        sortList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        return sortList;
    }

    @Override
    public List<Double> getTotalPriceForEachGenre(int month, int year) {
        List<Order> orderList = getConfirmedOrdersAndValidDate(month, year);

        // Map<Genre, Price>
        Map<Book.genreEnum, Double> genreMap = new HashMap<>();

        for (Order order : orderList) {
            //get order item list by order id
            List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getID());
            for (OrderItem orderItem : orderItemList) {
                // add all book to bestSellerList
                Optional<Book> book = bookRepository.findById(orderItem.getBookID());
                if (book.isPresent()) {
                    Book tempBook = book.get();
                    Book.genreEnum genre = book.get().getGenre();
                    int quantity = orderItem.getQuantity();
                    double price = book.get().getPrice();

                    if(!genreMap.containsKey(genre)) {
                        genreMap.put(genre, price * quantity);
                    }
                    else {
                        double newQuantity = genreMap.get(genre) + price * quantity;
                        genreMap.put(genre, newQuantity);
                    }
                }
            }
        }

        List<Double> sortList = new ArrayList<>(genreMap.values());
        sortList.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });

        System.out.println("Map" + genreMap);
        System.out.println("Sort list: " + sortList);


        return sortList;
    }

    @Override
    public List<Integer> getBookSoldForYear(int year) {
        List<Integer> listBookSoldForYear = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            int totalBookSoldInMonth = getTotalBookSold(i+1, year);
            listBookSoldForYear.add(totalBookSoldInMonth);
        }
        return listBookSoldForYear;
    }

    @Override
    public int getTotalBookSoldInYear(int year) {
        int total = 0;
        for(int i = 0; i < 12; i++) {
            total += getTotalBookSold(i+1, year);
        }

        return total;
    }

    @Override
    public double getTotalPriceInYear(int year) {
        double total = 0;
        for(int i = 0; i < 12; i++) {
            total += getTotalBookPriceSold(i+1, year);
        }
        return total;
    }

    @Override
    public List<Book> findBook(String name) {
        this.titleSearch = name;
        return bookRepository.findByTitle(name);
    }

    @Override
    public List<SimpleCustomerBookDTO> filterForSearch(Filter filter) {
        List<Book> bookList = bookRepository.findByTitle(this.titleSearch);

        if (filter.getMinPrice() != -1) removeNotInCondition(bookList, "minPrice", (int) filter.getMinPrice());
        if (filter.getMaxPrice() != -1) removeNotInCondition(bookList, "maxPrice", (int) filter.getMaxPrice());
        if (filter.getPublicationYear() != -1)
            removeNotInCondition(bookList, "publicationYear", filter.getPublicationYear());
        if (filter.getPublisherIds().size() != 0) checkListPublisher(bookList, filter.getPublisherIds());
        if (filter.getAuthorIds().size() != 0) checkListAuthor(bookList, filter.getAuthorIds());
        if (!filter.getType().equals(Book.typeEnum.NONE))
            removeNotInCondition(bookList, "type", filter.getType().toString());
        return bookToSimpleCustomerBookDTO(bookList);
    }
}
