package kz.bitlab.Kitapsoresi;

import kz.bitlab.Kitapsoresi.model.Author;
import kz.bitlab.Kitapsoresi.model.Books;
import kz.bitlab.Kitapsoresi.model.Genre;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooksTest {

  @Test
  public void testGettersAndSetters() {
    Books book = new Books();
    Long id = 1L;
    String name = "Sample Book";
    int year = 2022;
    int point = 4;
    String photo = "sample.jpg";
    String description = "Sample book description";
    int price = 50;
    Author author = new Author();
    List<Genre> genres = new ArrayList<>();

    book.setId(id);
    book.setName(name);
    book.setYear(year);
    book.setPoint(point);
    book.setPhoto(photo);
    book.setDescription(description);
    book.setPrice(price);
    book.setAuthor(author);
    book.setGenre(genres);

    assertEquals(id, book.getId());
    assertEquals(name, book.getName());
    assertEquals(year, book.getYear());
    assertEquals(point, book.getPoint());
    assertEquals(photo, book.getPhoto());
    assertEquals(description, book.getDescription());
    assertEquals(price, book.getPrice());
    assertEquals(author, book.getAuthor());
    assertEquals(genres, book.getGenre());
  }
}
