package kz.bitlab.Kitapsoresi.service;


import kz.bitlab.Kitapsoresi.model.Author;
import kz.bitlab.Kitapsoresi.model.Books;
import kz.bitlab.Kitapsoresi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorRepository authorRepository;

  public List<Author> getAuthors(){
    return authorRepository.findAll();
  }

  public Author getAuthor(Long id) {
    return authorRepository.findById(id).orElse(null);
  }
}