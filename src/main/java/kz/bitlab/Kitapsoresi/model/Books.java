package kz.bitlab.Kitapsoresi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_books")
@Getter
@Setter
public class Books {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "year")
  private int year;

  @Column(name = "point")
  private int point;

  @Column(name = "photo")
  private String photo;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "price")
  private int price;

  @ManyToOne
  private Author author;

  @ManyToMany
  private List<Genre> genre;

}
