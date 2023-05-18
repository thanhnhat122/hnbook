package hcmute.edu.hnlbook.service;

import hcmute.edu.hnlbook.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
  List<Publisher> getAll();

  Optional<Publisher> findById(int id);
  Publisher insert(Publisher publisher);

  Publisher update(Publisher publisher, int id);

  boolean existsById(int id);

  void deleteById(int id);
}
