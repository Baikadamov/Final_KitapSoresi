package kz.bitlab.Kitapsoresi;

import kz.bitlab.Kitapsoresi.model.Blog;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlogTest {

  @Test
  public void testGettersAndSetters() {
    Blog blog = new Blog();
    Long id = 1L;
    String title = "Sample Title";
    String caption = "Sample Caption";
    LocalDateTime createdAt = LocalDateTime.now();

    blog.setId(id);
    blog.setTitle(title);
    blog.setCaption(caption);
    blog.setCreatedAt(createdAt);

    assertEquals(id, blog.getId());
    assertEquals(title, blog.getTitle());
    assertEquals(caption, blog.getCaption());
    assertEquals(createdAt, blog.getCreatedAt());
  }
}
