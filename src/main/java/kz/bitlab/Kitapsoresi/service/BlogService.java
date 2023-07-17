package kz.bitlab.Kitapsoresi.service;

import kz.bitlab.Kitapsoresi.dto.BlogDTO;
import kz.bitlab.Kitapsoresi.mapper.BlogMapper;
import kz.bitlab.Kitapsoresi.model.Blog;
import kz.bitlab.Kitapsoresi.model.Post;
import kz.bitlab.Kitapsoresi.repository.BlogRepository;
import kz.bitlab.Kitapsoresi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
  private final BlogRepository blogRepository;
  private final BlogMapper blogMapper;

  public List<BlogDTO> getBlogs() {
    return blogMapper.toDtoList(blogRepository.findAll());
  }

  public BlogDTO addBlog(BlogDTO blog) {
    return blogMapper.toDto(blogRepository.save(blogMapper.toModel(blog)));
  }

  public BlogDTO getBlog(Long id) {
    return blogMapper.toDto(blogRepository.findById(id).orElse(null));
  }

  public BlogDTO updateBlog(BlogDTO blog) {
    return blogMapper.toDto(blogRepository.save(blogMapper.toModel(blog)));
  }

  public void deleteBlog(Long id) {
    blogRepository.deleteById(id);
  }


}
