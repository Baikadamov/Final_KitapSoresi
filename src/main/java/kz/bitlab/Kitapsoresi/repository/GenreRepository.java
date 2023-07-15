package kz.bitlab.Kitapsoresi.repository;


import kz.bitlab.Kitapsoresi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
