package com.raf.fwk.security.ctrl;

import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.raf.fwk.security.domain.RoleUser;
import com.raf.fwk.security.domain.UserEntity;
import com.raf.fwk.security.dto.UserDto;
import com.raf.fwk.security.service.SecurityService;
import com.raf.fwk.security.service.UserEntityService;

import lombok.RequiredArgsConstructor;

/**
 * Abstract controller for user registration and login.
 * 
 * @author RAF
 */
@Controller
@RequiredArgsConstructor
public class UserController {

  /** The welcome page. */
  private static final String WELCOME_PAGE = "welcome";

  /** The registration page. */
  private static final String REGISTRATION_PAGE = "registration";

  /** The login page. */
  private static final String LOGIN_PAGE = "login";

  /** The user entity service. */
  @Resource
  private UserEntityService userEntityService;

  /** The security service. */
  @Resource
  private SecurityService securityService;

  /** The user validator. */
  @Resource
  private Validator userValidator;

  /**
   * Open the registration page.
   * 
   * @param model
   *          the model
   * @return the registration page
   */
  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public final String registration(final Model model) {
    model.addAttribute("userForm", new UserDto());

    return REGISTRATION_PAGE;
  }

  /**
   * Register the registration form.
   * 
   * @param userForm
   *          the user form
   * @param bindingResult
   *          the result
   * @param model
   *          the model
   * @return the registration page
   */
  @Transactional
  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public final String registration(@ModelAttribute("userForm") final UserDto userForm,
      final BindingResult bindingResult,
      final Model model) {
    final String forward;
    this.userValidator.validate(userForm, bindingResult);

    if (bindingResult.hasErrors()) {
      forward = REGISTRATION_PAGE;
    } else {
      final UserEntity entity = getUser(userForm);
      entity.getRoles().add(RoleUser.ROLE_USER);
      this.userEntityService.save(entity);

      this.securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

      forward = "redirect:/" + WELCOME_PAGE;
    }
    return forward;
  }

  /**
   * Display the login page.
   * 
   * @param model
   *          the model
   * @param error
   *          the error message
   * @param logout
   *          the logout message
   * @return the login page
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public final String login(final Model model, final String error, final String logout) {
    if (error != null) {
      model.addAttribute("error", "Your username and password is invalid.");
    }

    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully.");
    }

    return LOGIN_PAGE;
  }

  /**
   * Display the welcome page.
   * 
   * @param model
   *          the model
   * @return the welcome page
   */
  @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
  public final String welcome(final Model model) {
    return WELCOME_PAGE;
  }

  /**
   * Create the user entity from the user form.
   * 
   * @param userForm
   *          the user form
   * @return the user entity
   */
  private UserEntity getUser(final UserDto userForm) {
    final UserEntity userEntity = new UserEntity();
    userEntity.setRoles(new HashSet<>());
    BeanUtils.copyProperties(userForm, userEntity);
    return userEntity;

  }

}
