package kz.bitlab.Kitapsoresi.controller;


import kz.bitlab.Kitapsoresi.model.Author;
import kz.bitlab.Kitapsoresi.model.Books;
import kz.bitlab.Kitapsoresi.model.Genre;
import kz.bitlab.Kitapsoresi.model.User;
import kz.bitlab.Kitapsoresi.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

  @Autowired
  private UserService userService;

  private final BooksService booksService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final BlogService blogService;

  @Value("${file.books.viewPath}")
  private String viewPath;

  @Value("${file.books.uploadPath}")
  private String uploadPath;

  @Value("${file.books.defaultPicture}")
  private String defaultPicture;


  @GetMapping(value = "/")
  public String index(Model model) {
    model.addAttribute("books", booksService.getAllBooks());
    model.addAttribute("genres", genreService.getGenres());
    model.addAttribute("author", authorService.getAuthors());
    return "index";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping(value = "/blog")
  public String blog() {
    return "blog";
  }

  @GetMapping(value = "/details/{booksId}")
  public String bookDetails(@PathVariable(name = "booksId") Long id, Model model) {

    Books book = booksService.getBook(id);
    model.addAttribute("book", book);

    List<Author> authorModelList = authorService.getAuthors();
    model.addAttribute("authors", authorModelList);

    List<Genre> genreModelList = genreService.getGenres();
    genreModelList.removeAll(book.getGenre());
    model.addAttribute("genres", genreModelList);


    return "details";
  }


  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @GetMapping(value = "/admin")
  public String adminPanelPage(Model model) {
    List<Books> latestBooks = booksService.getLatestAddedBooks(10); // Get the latest 10 added books
    model.addAttribute("authors", authorService.getAuthors());
    model.addAttribute("genres", genreService.getGenres());
    model.addAttribute("books", latestBooks);
    model.addAttribute("bookCount", booksService.getBooksCount());
    model.addAttribute("usersCount", userService.getUsersCount());
    model.addAttribute("blog", blogService.getBlogs());
    return "admin";
  }


  @GetMapping(value = "/books")
  public String books(Model model) {
    model.addAttribute("books", booksService.getAllBooks());
    model.addAttribute("genres", genreService.getGenres());
    model.addAttribute("authors", authorService.getAuthors());
    return "books";
  }


  @GetMapping("/books/filter")
  public String filterBooks(@RequestParam(value = "genre", required = false) Long genreId,
                            @RequestParam(value = "author", required = false) Long authorId,
                            @RequestParam(value = "year", required = false) Integer year,
                            @RequestParam(value = "point", required = false) Integer point,
                            Model model) {


    model.addAttribute("filteredBooks", booksService.filterBooks(genreId, authorId, year, point));
    model.addAttribute("books", booksService.getAllBooks());
    model.addAttribute("searchedBooks", null);
    model.addAttribute("genres", genreService.getGenres());
    model.addAttribute("authors", authorService.getAuthors());

    return "books-filtered";
  }


  @GetMapping("/books/search")
  public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
    List<Books> searchedBooks = booksService.searchBooks(keyword);
    model.addAttribute("searchedBooks", searchedBooks);
    model.addAttribute("books", booksService.getAllBooks());
    model.addAttribute("genres", genreService.getGenres());
    model.addAttribute("authors", authorService.getAuthors());
    return "books-filtered";
  }


  @PostMapping(value = "/books/add")
  public String addNewBook(@RequestParam(name = "name") String name,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "price") int price,
                           @RequestParam(name = "point") int point,
                           @RequestParam(name = "year") int year,
                           @RequestParam(name = "authorModelid") Long authorModelId,
                           @RequestParam(name = "genres") List<Long> genreIds,
                           @RequestParam(name = "photo") MultipartFile file) {

    if ((file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))) {
      try {
        String picName = DigestUtils.sha1Hex("picture" + name);
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadPath + picName + ".jpg");
        Files.write(path, bytes);

        Books books = new Books();
        books.setName(name);
        books.setPoint(point);
        books.setYear(year);
        books.setDescription(description);
        books.setPrice(price);

        // Retrieve the author object using the authorModelId
        Author author = authorService.getAuthor(authorModelId);
        books.setAuthor(author);

        // Retrieve the genre objects using the genreIds
        List<Genre> genres = genreService.getGenresByIds(genreIds);
        books.setGenre(genres);

        books.setPhoto(picName);

        booksService.addBook(books);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return "redirect:/books";

  }

  @GetMapping(value = "/viewphoto/{url}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
  public @ResponseBody
  byte[] viewProfilePhoto(@PathVariable(name = "url") String url) throws Exception {

    String pictureUrl = viewPath + defaultPicture;

    if (url != null) {
      pictureUrl = viewPath + url + ".jpg";
    }

    InputStream in;

    try {

      ClassPathResource resource = new ClassPathResource(pictureUrl);
      in = resource.getInputStream();

    } catch (Exception e) {

      ClassPathResource resource = new ClassPathResource(viewPath + defaultPicture);
      in = resource.getInputStream();
      e.printStackTrace();
    }

    return IOUtils.toByteArray(in);

  }


  @PostMapping(value = "/delete-book")
  public String deleteBook(@RequestParam(name = "id") Long id) {
    booksService.delete(id);
    return "redirect:/books";
  }

  @PostMapping(value = "/save-book")
  public String saveBook(Books books, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {

    }
    booksService.saveBook(books);
    return "redirect:/books";
  }

  @PostMapping(value = "/save-profile")
  public String saveProfile(@RequestParam(name = "id") Long id,
                            @RequestParam(name = "fullName") String fullName,
                            @RequestParam(name = "city") String city,
                            @RequestParam(name = "contacts") String contacts,
                            @RequestParam(name = "bio") String bio,
                            @RequestParam(name = "phone_number") int phone_number,
                            @RequestParam(name = "image") MultipartFile file) {

    try {
      String picName = DigestUtils.sha1Hex("picture" + bio);

      byte[] bytes = file.getBytes();
      Path path = Paths.get(uploadPath + picName + ".jpg");
      Files.write(path, bytes);


      User user = userService.getUser(id);

      if (user != null) {
        user.setFullName(fullName);
        user.setCity(city);
        user.setContacts(contacts);
        user.setBio(bio);
        user.setPhone_number(phone_number);
        user.setImage(picName);

        userService.saveUser(user);
        return "redirect:/profile";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/";
  }


}
