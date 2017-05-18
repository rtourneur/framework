package com.raf.fwk.security.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.raf.fwk.security.dto.UserDto;
import com.raf.fwk.security.service.UserEntityService;
import com.raf.fwk.util.aop.Loggable;

import lombok.NoArgsConstructor;

/**
 * Validator for {@link UserDto}.
 * 
 * @author RAF.
 */
@Component
@NoArgsConstructor
public class UserValidator implements Validator {

  /** The user entity service. */
  @Autowired
  private UserEntityService userEntityService;

  /**
   * Return <code>true</code> if the class is derived from {@link UserDto}.
   * 
   * @param clazz
   *          the class
   * @return <code>true</code> if the class is derived from UserDto
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public final boolean supports(final Class<?> clazz) {
    return UserDto.class.isAssignableFrom(clazz);
  }

  /**
   * Validate the object.
   * 
   * @param target
   *          the object
   * @param errors
   *          the errors
   * @see Validator#validate(Object, Errors)
   */
  @Override
  @Loggable
  @Transactional
  public final void validate(final Object target, final Errors errors) {
    final UserDto user = (UserDto) target;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
    if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
      errors.rejectValue("username", "Size.userForm.username");
    }
    if (this.userEntityService.findByUsername(user.getUsername()) != null) {
      errors.rejectValue("username", "Duplicate.userForm.username");
    }

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
    if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
      errors.rejectValue("password", "Size.userForm.password");
    }

    if (!user.getPasswordConfirm().equals(user.getPassword())) {
      errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
    }

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "NotEmpty");
    if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
      errors.rejectValue("mail", "Size.userForm.mail");
    }
    if (this.userEntityService.findByMail(user.getMail()) != null) {
      errors.rejectValue("mail", "Duplicate.userForm.mail");
    }
  }

}
