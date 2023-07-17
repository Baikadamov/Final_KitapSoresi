package kz.bitlab.Kitapsoresi;

import kz.bitlab.Kitapsoresi.dto.BlogDTO;
import kz.bitlab.Kitapsoresi.service.BlogService;
import kz.bitlab.Kitapsoresi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BlogRestControllerTest {
//
//  @Mock
//  private BlogService blogService;
//
//  @Mock
//  private UserService userService;
//
//  @InjectMocks
//  private BlogRestController blogRestController;
//
//  @BeforeEach
//  public void setUp() {
//    MockitoAnnotations.openMocks(this);
//  }
//
//  @Test
//  public void testGetBlogs() {
//    // Prepare mock data
//    List<BlogDTO> expectedBlogDTOs = new ArrayList<>();
//    expectedBlogDTOs.add(new BlogDTO());
//    expectedBlogDTOs.add(new BlogDTO());
//
//    when(blogService.getBlogs()).thenReturn(expectedBlogDTOs);
//
//    // Perform the test
//    List<BlogDTO> actualBlogDTOs = blogRestController.getBlogs();
//
//    // Verify the result
//    assertEquals(expectedBlogDTOs, actualBlogDTOs);
//    verify(blogService, times(1)).getBlogs();
//  }
//
//  @Test
//  public void testAddBlog() {
//    // Prepare mock data
//    BlogDTO blogDTO = new BlogDTO();
//    BlogDTO expectedBlogDTO = new BlogDTO();
//
//    when(blogService.addBlog(blogDTO)).thenReturn(expectedBlogDTO);
//
//    // Perform the test
//    BlogDTO actualBlogDTO = blogRestController.addBlog(blogDTO);
//
//    // Verify the result
//    assertEquals(expectedBlogDTO, actualBlogDTO);
//    verify(blogService, times(1)).addBlog(blogDTO);
//  }
//

}
