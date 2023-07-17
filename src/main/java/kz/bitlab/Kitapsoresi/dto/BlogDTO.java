package kz.bitlab.Kitapsoresi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlogDTO {

  private Long id;
  private String title;
  private String caption;
  private LocalDateTime createdAt;

}
