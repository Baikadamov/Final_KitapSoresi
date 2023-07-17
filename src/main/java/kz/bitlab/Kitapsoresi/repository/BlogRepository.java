package kz.bitlab.Kitapsoresi.repository;

import kz.bitlab.Kitapsoresi.model.Blog;
import kz.bitlab.Kitapsoresi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
}
