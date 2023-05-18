package hcmute.edu.hnlbook.controller;

import hcmute.edu.hnlbook.model.Author;
import hcmute.edu.hnlbook.model.DataResponse;
import hcmute.edu.hnlbook.model.Publisher;
import hcmute.edu.hnlbook.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Publisher")
public class PublisherController {
  @Autowired
  private PublisherService publisherService;

  @GetMapping("")
  DataResponse getAllPublisher() {
    List<Publisher> listPublisher = publisherService.getAll();
    return new DataResponse(listPublisher);
  }

  @GetMapping("/{id}")
  DataResponse findPublisherById(@PathVariable Integer id) {
    Optional<Publisher> foundPublisher = publisherService.findById(id);
    if (foundPublisher.isPresent())
      return new DataResponse(foundPublisher);
    else
      throw new RuntimeException("Cannot find publisher with id = " + id);
  }

  @PostMapping("")
  DataResponse insertPublisher(@RequestBody @Validated Publisher newPublisher, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(publisherService.insert(newPublisher));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @PutMapping("")
  DataResponse updatePublisher(@RequestBody @Validated Publisher newPublisher, BindingResult result, @RequestParam Integer id) {
    if (!result.hasErrors()){
      Publisher updatePublisher = publisherService.update(newPublisher, id);
      return new DataResponse(updatePublisher);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @DeleteMapping("")
  DataResponse deletePublisher(@RequestParam Integer id) {
    boolean exists = publisherService.existsById(id);
    if (exists) {
      publisherService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find publisher with id = " + id + " to delete");
  }
}
