package kz.bitlab.Kitapsoresi.controller;


import kz.bitlab.Kitapsoresi.model.User;
import kz.bitlab.Kitapsoresi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

  @Autowired
  private UserService userService;

  //  registration
  @GetMapping(value = "/register")
  public String register() {
    return "register";
  }

  @GetMapping(value = "/login")
  public String login() {
    return "login";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping(value = "/profile")
  public String profilePage() {
    return "profile";
  }

  @GetMapping(value = "/403-page")
  public String accessDenied() {
    return "403";
  }

  @GetMapping(value = "/update-password-page")
  public String updaetPasswordPage() {
    return "update-password";
  }


  @PostMapping(value = "/to-update-password")
  public String toUpdatePassword(
      @RequestParam(name = "user_old_password") String oldPassword,
      @RequestParam(name = "user_new_password") String newPassword,
      @RequestParam(name = "user_repeat_new_password") String repeatNewPassword) {

    if (newPassword.equals(repeatNewPassword)) {

      User user = userService.updatePassword(newPassword, oldPassword);
      if (user != null) {
        return "redirect:/update-password-page?success";
      } else {
        return "redirect:/update-password-page?oldpassworderror";
      }

    } else {
      return "redirect:/update-password-page?passwordmismatch";
    }
  }


  @PostMapping(value = "/to-sign-up")
  public String toSignUp(@RequestParam(name = "user_email") String email,
                         @RequestParam(name = "user_password") String password,
                         @RequestParam(name = "user_repeat_password") String repeatPassword,
                         @RequestParam(name = "user_full_name") String fullName) {
    if (password.equals(repeatPassword)) {
      User user = new User();
      user.setEmail(email);
      user.setFullName(fullName);
      user.setPassword(password);
      User newUser = userService.addUser(user);
      if (newUser != null) {
        return "redirect:/login?success";
      } else {
        return "redirect:/register?emailerror";
      }
    } else {
      return "redirect:/register?passworderror";
    }
  }

}
