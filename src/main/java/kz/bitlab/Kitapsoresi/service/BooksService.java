package kz.bitlab.Kitapsoresi.service;


import kz.bitlab.Kitapsoresi.model.Books;
import kz.bitlab.Kitapsoresi.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksService {
  private final BooksRepository booksRepository;
  private final GenreService genreService;


  public List<Books> filterBooks(Long genreId, Long authorId, Integer year, Integer point) {
    if (genreId == null && authorId == null && year == null && point == null) {
      return booksRepository.findAll();
    } else {
      return booksRepository.filterBooks(genreId, authorId, year, point);
    }
  }

  public List<Books> searchBooks(String keyword) {
    return booksRepository.findByNameContainingIgnoreCase(keyword);
  }


  public Books addBook(Books books) {
    return booksRepository.save(books);
  }

  public Books getBook(Long id) {
    return booksRepository.findById(id).orElse(null);
  }

  public long getBooksCount() {
    return booksRepository.count();
  }

  public List<Books> getAllBooks() {
    return booksRepository.findAll();
  }

  public List<Books> getLatestAddedBooks(int limit) {
    Sort sort = Sort.by(Sort.Direction.DESC, "id");
    PageRequest pageRequest = PageRequest.of(0, limit, sort);
    return booksRepository.findAll(pageRequest).getContent();
  }

  public Books saveBook(Books books) {
    return booksRepository.save(books);
  }

  public void delete(Long id) {
    booksRepository.deleteById(id);
  }


}
