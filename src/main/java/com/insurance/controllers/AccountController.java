package com.insurance.controllers;

import com.insurance.data.entities.UserEntity;
import com.insurance.models.dto.InsuranceDTO;
import com.insurance.models.dto.UserDTO;
import com.insurance.models.dto.mappers.UserMapper;
import com.insurance.models.exceptions.DuplicateEmailException;
import com.insurance.models.exceptions.PasswordsDoNotEqualException;
import com.insurance.models.services.InsuranceService;
import com.insurance.models.services.UserService;
import com.insurance.validation.Editing;
import com.insurance.validation.Registration;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String renderLogin() {
        return "/pages/account/login";
    }

    @GetMapping("/register")
    public String renderRegister(@ModelAttribute UserDTO userDTO) {
        return "/pages/account/register";
    }

    @PostMapping("register")
    public String register(
            @Valid @ModelAttribute @Validated(Registration.class) UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderRegister(userDTO);

        try {
            userService.create(userDTO, false);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "Email already in use.");
            return "/pages/account/register";
        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "The passwords do not match.");
            result.rejectValue("confirmPassword", "error", "The passwords do not match.");
            return "/pages/account/register";
        }

        redirectAttributes.addFlashAttribute("success", "Welcome aboard! Your future is now secure with CyberSure.");
        return "redirect:/account/login";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{role}-details")
    public String renderDetails(
            @PathVariable String role,
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserEntity user) {
            UserDTO userDTO = userService.getById(user.getUserId());
            model.addAttribute("userDetails", userDTO);

            if (role.equals("user")) {
                List<InsuranceDTO> insurances = insuranceService.getInsurances(user.getEmail());
                model.addAttribute("userInsurances", insurances);
            } else if (role.equals("admin")) {
                List<UserDTO> users = userService.getAllUsers();
                model.addAttribute("insuredUsers", users);

            }

            return "/pages/account/" + role + "-details";
        }
        return "redirect:/account/login";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("{userId}")
    public String renderUserDetails(
            @PathVariable Long userId,
            Model model
    ) {
        UserDTO userDTO = userService.getById(userId);
        model.addAttribute("userDetails", userDTO);
        List<InsuranceDTO> insurances = insuranceService.getInsurances(userDTO.getEmail());
        model.addAttribute("userInsurances", insurances);

        return "pages/account/user-details";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{role}-edit")
    public String renderEditForm(
            @PathVariable String role,
            @ModelAttribute UserDTO user
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserEntity loggedUser) {
            UserDTO fetchedUser = userService.getById(loggedUser.getUserId());
            userMapper.updateUserDTO(fetchedUser, user);
            return "pages/account/" + role + "-edit";
        }
        return "redirect:/account/login";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{role}-edit")
    public String handleEditForm(
            @PathVariable String role,
            @Valid @ModelAttribute @Validated(Editing.class) UserDTO user,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "pages/account/" + role + "-edit";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        UserEntity currentUser = userService.findByUsername(currentUserEmail);
        user.setUserId(currentUser.getUserId());

        userService.edit(user);
        redirectAttributes.addFlashAttribute("success", role.equals("admin")
                ? "Admin details were edited successfully."
                : "User details were edited successfully.");

        return "redirect:/account/" + role + "-details";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/delete-account")
    public String deleteLoggedUser(
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String email = userDetails.getUsername();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userService.remove(email);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, response, authentication);
            redirectAttributes.addFlashAttribute("success", "Account purged. You're off the digital grid now.");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to Purge: The system detected an anomaly. Retry.");
            return "redirect:/account/account-details";
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{userId}")
    public String deleteUserById(
            @PathVariable Long userId,
            RedirectAttributes redirectAttributes
    ) {
        userService.removeById(userId);
        redirectAttributes.addFlashAttribute("success", "User has been removed");

        return "redirect:/account/admin-details";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{userId}")
    public String renderEditUser(
            @PathVariable Long userId,
            UserDTO user
    ) {
        UserDTO fetchedUser = userService.getById(userId);
        userMapper.updateUserDTO(fetchedUser, user);

        return "pages/account/user-edit";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{userId}")
    public String editUser(
            @PathVariable Long userId,
            @Valid UserDTO user,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderEditUser(userId, user);

        user.setUserId(userId);
        userService.edit(user);
        redirectAttributes.addFlashAttribute("success", "User has been edited successfully");

        return "redirect:/account/admin-details";
    }
}
