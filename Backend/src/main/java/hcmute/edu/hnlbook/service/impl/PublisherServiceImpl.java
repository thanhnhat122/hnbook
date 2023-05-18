package hcmute.edu.hnlbook.service.impl;

import hcmute.edu.hnlbook.model.Publisher;
import hcmute.edu.hnlbook.repository.PublisherRepository;
import hcmute.edu.hnlbook.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {
  @Autowired
  private PublisherRepository publisherRepository;

  @Override
  public List<Publisher> getAll() {
    return publisherRepository.findAll();
  }

  @Override
  public Optional<Publisher> findById(int id) {
    return publisherRepository.findById(id);
  }

  @Override
  public Publisher insert(Publisher publisher) {
    return publisherRepository.save(publisher);
  }

  @Override
  public Publisher update(Publisher newPublisher, int id) {
    return publisherRepository
        .findById(id)
        .map(
            publisher -> {
              publisher.setName(newPublisher.getName());
              publisher.setCountry(newPublisher.getCountry());
              return publisherRepository.save(publisher);
            })
        .orElseGet(
            () -> publisherRepository.save(newPublisher));
  }

  @Override
  public boolean existsById(int id) {
    return publisherRepository.existsById(id);
  }

  @Override
  public void deleteById(int id) {
    publisherRepository.deleteById(id);
  }
}
