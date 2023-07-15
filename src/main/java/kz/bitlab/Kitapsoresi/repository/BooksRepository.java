package kz.bitlab.Kitapsoresi.repository;


import kz.bitlab.Kitapsoresi.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {


  List<Books> findByNameContainingIgnoreCase(String keyword);


  @Query("SELECT b FROM Books b " +
      "LEFT JOIN b.genre g " +
      "WHERE (:genreId IS NULL OR g.id = :genreId) " +
      "AND (:authorId IS NULL OR b.author.id = :authorId) " +
      "AND (:year IS NULL OR b.year = :year) " +
      "AND (:point IS NULL OR b.point = :point)")
  List<Books> filterBooks(@Param("genreId") Long genreId,
                          @Param("authorId") Long authorId,
                          @Param("year") Integer year,
                          @Param("point") Integer point);

}
