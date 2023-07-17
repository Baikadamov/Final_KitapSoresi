package kz.bitlab.Kitapsoresi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_blog")
@Getter
@Setter
public class Blog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "caption", columnDefinition = "TEXT")
  private String caption;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

}