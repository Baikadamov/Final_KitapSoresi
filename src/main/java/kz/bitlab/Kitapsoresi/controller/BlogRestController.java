package kz.bitlab.Kitapsoresi.controller;

import kz.bitlab.Kitapsoresi.dto.BlogDTO;
import kz.bitlab.Kitapsoresi.model.Blog;
import kz.bitlab.Kitapsoresi.service.BlogService;
import kz.bitlab.Kitapsoresi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/blogs")
public class BlogRestController {

  private final BlogService blogService;
  private final UserService userService;

  @GetMapping
  public List<BlogDTO> getBlogs() {
    return blogService.getBlogs();
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public BlogDTO addBlog(@RequestBody BlogDTO blog) {
    return blogService.addBlog(blog);
  }

  @GetMapping(value = "{id}")
  public BlogDTO getBlog(@PathVariable(name = "id") Long id) {
    return blogService.getBlog(id);
  }

  @PutMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public BlogDTO updateBlog(@RequestBody BlogDTO blog) {
    return blogService.updateBlog(blog);
  }


  @DeleteMapping(value = "{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public void deleteBlog(@PathVariable(name = "id") Long id) {
    blogService.deleteBlog(id);
  }


}
