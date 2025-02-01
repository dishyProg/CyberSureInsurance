package com.insurance.controllers;

import com.insurance.data.entities.InsuranceEntity;
import com.insurance.models.dto.InsuranceDTO;
import com.insurance.models.dto.mappers.InsuranceMapper;
import com.insurance.models.services.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuranceService insuranceService;

    // Region: Helping Methods

    private String renderProductForm(String insuranceName, String insuranceType, String viewPath, Model model) {
        InsuranceDTO insuranceDTO = new InsuranceDTO();
        insuranceDTO.setInsuranceName(insuranceName);
        insuranceDTO.setInsuranceType(insuranceType);
        model.addAttribute("insuranceDTO", insuranceDTO);
        return viewPath;
    }

    private String handleFormSubmission(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            String errorViewPath,
            String successMessage
    ) {
        if (result.hasErrors()) {
            model.addAttribute("error", true);
            return errorViewPath;
        }

        String email = userDetails.getUsername();
        InsuranceEntity insurance = insuranceMapper.toEntity(insuranceDTO);
        insuranceService.create(email, insurance);
        redirectAttributes.addFlashAttribute("success", successMessage);
        return "redirect:/account/user-details";
    }

    // Endregion

    @GetMapping
    public String renderProducts() {
        return "pages/products/products";
    }

    @GetMapping("/neural-health-protection")
    public String renderNeuralHealthProtection() {
        return "pages/products/neural-health-protection/neural-health-protection";
    }

    @GetMapping("/guard-your-mind")
    public String renderGuardYourMind(Model model) {
        return renderProductForm(
                "Neural Health Protection",
                "Elite Guard",
                "pages/products/neural-health-protection/get-protection",
                model
        );
    }

    @Secured("ROLE_USER")
    @PostMapping("/guard-your-mind")
    public String guardYourMind(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        return handleFormSubmission(
                userDetails,
                insuranceDTO,
                result,
                redirectAttributes,
                model,
                "pages/products/neural-health-protection/get-protection",
                "Neural Shield activated. Your mind is now untouchable in the digital storm."
        );
    }

    @GetMapping("/data-asset-shielding")
    public String renderDataAssetShielding() {
        return "pages/products/data-asset-shielding/data-asset-shielding";
    }

    @GetMapping("/lock-your-data")
    public String renderLockYourData(Model model) {
        return renderProductForm(
                "Data Asset Shielding",
                "Elite DataGuard",
                "pages/products/data-asset-shielding/get-protection",
                model
        );
    }

    @Secured("ROLE_USER")
    @PostMapping("/lock-your-data")
    public String lockYourData(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        return handleFormSubmission(
                userDetails,
                insuranceDTO,
                result,
                redirectAttributes,
                model,
                "pages/products/data-asset-shielding/get-protection",
                "Asset shielding online. The grid is secure."
        );
    }

    @GetMapping("/neon-drive-protection")
    public String renderNeonDriveProtection() {
        return "pages/products/neon-drive-protection/neon-drive-protection";
    }

    @GetMapping("/protect-your-ride")
    public String renderProtectYourRide(Model model) {
        return renderProductForm(
                "Neon Drive Protection",
                "Elite CyberDrive",
                "pages/products/neon-drive-protection/get-protection",
                model
        );
    }

    @Secured("ROLE_USER")
    @PostMapping("/protect-your-ride")
    public String protectYourRide(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        return handleFormSubmission(
                userDetails,
                insuranceDTO,
                result,
                redirectAttributes,
                model,
                "pages/products/neon-drive-protection/get-protection",
                "Cyber roads are yours. Protection activated."
        );
    }

    @GetMapping("/cyber-glide-travel-coverage")
    public String renderCyberGlideTravelCoverage() {
        return "pages/products/cyber-glide/cyber-glide";
    }

    @GetMapping("/travel-safe")
    public String renderTravelSafe(Model model) {
        return renderProductForm(
                "CyberGlide Travel Coverage",
                "Elite TravelGuard",
                "pages/products/cyber-glide/get-protection",
                model
        );
    }

    @Secured("ROLE_USER")
    @PostMapping("/travel-safe")
    public String travelSafe(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        return handleFormSubmission(
                userDetails,
                insuranceDTO,
                result,
                redirectAttributes,
                model,
                "pages/products/cyber-glide/get-protection",
                "Travel with confidence. CyberGlide protection is onboard."
        );
    }

    @GetMapping("/augment-guard")
    public String renderAugmentGuard() {
        return "pages/products/augment-guard/augment-guard";
    }

    @GetMapping("/shield-enhancements")
    public String renderShieldEnhancements(Model model) {
        return renderProductForm(
                "AugmentGuard",
                "Elite AugmentGuard",
                "pages/products/augment-guard/get-protection",
                model
        );
    }

    @Secured("ROLE_USER")
    @PostMapping("/shield-enhancements")
    public String shieldEnhancements(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        return handleFormSubmission(
                userDetails,
                insuranceDTO,
                result,
                redirectAttributes,
                model,
                "pages/products/augment-guard/get-protection",
                "Defense upgraded. Your augmentations are locked and shielded."
        );
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("delete/{insuranceId}")
    public String deleteInsurance(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long insuranceId,
            RedirectAttributes redirectAttributes
    ) {
        if (userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            insuranceService.remove(insuranceId);
            redirectAttributes.addFlashAttribute("success", "Insurance deactivated. You're now riding solo.");
            return "redirect:/account/user-details";
        } else if (userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            insuranceService.remove(insuranceId);
            redirectAttributes.addFlashAttribute("success", "Insurance deactivated.");
            return "redirect:/account/admin-details";
        }
        return "redirect:/";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("{insuranceId}")
    public String renderInsuranceDetail(
            @PathVariable Long insuranceId,
            Model model
    ) {
        InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
        model.addAttribute("insurance", insuranceDTO);
        return "pages/products/detail";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/edit/{insuranceId}")
    public String renderEditForm(
            @PathVariable Long insuranceId,
            InsuranceDTO insurance
    ) {
        InsuranceDTO fetchedInsurance = insuranceService.getById(insuranceId);
        insuranceMapper.updateInsuranceDto(fetchedInsurance, insurance);

        return switch (insurance.getInsuranceName()) {
            case "Neural Health Protection" -> "pages/neural-insurance-edit.html";
            case "Data Asset Shielding" -> "pages/data-insurance-edit.html";
            case "Neon Drive Protection" -> "pages/neon-insurance-edit.html";
            case "CyberGlide Travel Coverage" -> "pages/glide-insurance-edit.html";
            case "AugmentGuard" -> "pages/augment-insurance-edit.html";
            default -> "redirect:/account/user-details";
        };
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/edit/{insuranceId}")
    public String editInsurance(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long insuranceId,
            @Valid InsuranceDTO insurance,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderEditForm(insuranceId, insurance);

        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            insurance.setInsuranceId(insuranceId);
            insuranceService.edit(insurance);
            redirectAttributes.addFlashAttribute("success", "Update successful. System integrity maintained.");
            return "redirect:/account/user-details";
        } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            insurance.setInsuranceId(insuranceId);
            insuranceService.edit(insurance);
            redirectAttributes.addFlashAttribute("success", "Update successful. System integrity maintained.");
            return "redirect:/account/admin-details";
        }
        return "redirect:/";
    }
}
