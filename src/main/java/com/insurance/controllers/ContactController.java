package com.insurance.controllers;

import com.insurance.models.dto.CommLinkDTO;
import com.insurance.models.services.CommLinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    @Autowired
    private CommLinkService commLinkService;

    @GetMapping("/contact")
    public String renderContact(@ModelAttribute CommLinkDTO commLinkDTO) {
        return "/pages/contact/contact";
    }

    @PostMapping("/contact")
    public String createInquiry(
            @Valid @ModelAttribute CommLinkDTO inquiry,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderContact(inquiry);

        commLinkService.create(inquiry);
        redirectAttributes.addFlashAttribute("success", "Your request is in. We'll sync back shortly.");
        return "redirect:/contact";
    }

    @GetMapping("/virtual-hq")
    public String renderVirtualHQ() {
        return  "/pages/contact/virtual-hq";
    }
}
