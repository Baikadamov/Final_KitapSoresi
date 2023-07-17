package kz.bitlab.Kitapsoresi;

import kz.bitlab.Kitapsoresi.model.Permission;
import kz.bitlab.Kitapsoresi.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

  @Test
  public void testGettersAndSetters() {
    User user = new User();
    Long id = 1L;
    String email = "sample@example.com";
    String password = "password";
    String fullName = "John Doe";
    String image = "user.jpg";
    String city = "Sample City";
    String contacts = "user@example.com";
    int phoneNumber = 123456789;
    String bio = "Sample user bio";
    List<Permission> permissions = new ArrayList<>();

    user.setId(id);
    user.setEmail(email);
    user.setPassword(password);
    user.setFullName(fullName);
    user.setImage(image);
    user.setCity(city);
    user.setContacts(contacts);
    user.setPhoneNumber(phoneNumber);
    user.setBio(bio);
    user.setPermissions(permissions);

    assertEquals(id, user.getId());
    assertEquals(email, user.getEmail());
    assertEquals(password, user.getPassword());
    assertEquals(fullName, user.getFullName());
    assertEquals(image, user.getImage());
    assertEquals(city, user.getCity());
    assertEquals(contacts, user.getContacts());
    assertEquals(phoneNumber, user.getPhoneNumber());
    assertEquals(bio, user.getBio());
    assertEquals(permissions, user.getPermissions());
  }

  @Test
  public void testUserDetailsMethods() {
    User user = new User();
    String email = "sample@example.com";
    String password = "password";
    List<Permission> permissions = new ArrayList<>();

    user.setEmail(email);
    user.setPassword(password);
    user.setPermissions(permissions);

    assertEquals(email, user.getUsername());
    assertEquals(password, user.getPassword());
    assertTrue(user.isAccountNonExpired());
    assertTrue(user.isAccountNonLocked());
    assertTrue(user.isCredentialsNonExpired());
    assertTrue(user.isEnabled());
    assertEquals(permissions, user.getAuthorities());
  }
}
