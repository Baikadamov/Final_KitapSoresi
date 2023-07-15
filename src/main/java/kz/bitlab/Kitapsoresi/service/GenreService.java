package kz.bitlab.Kitapsoresi.service;


import kz.bitlab.Kitapsoresi.model.Genre;
import kz.bitlab.Kitapsoresi.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
  private final GenreRepository genreRepository;

  public List<Genre> getGenres(){
    return genreRepository.findAll();
  }

  public Genre getGenre(Long id){
    return genreRepository.findById(id).orElse(null);
  }

  public List<Genre> getGenresByIds(List<Long> genreIds){
    return genreRepository.findAllById(genreIds);
  }

}