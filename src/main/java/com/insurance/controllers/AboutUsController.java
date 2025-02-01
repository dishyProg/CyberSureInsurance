package com.insurance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about-us")
public class AboutUsController {

    @GetMapping
    public String renderAboutUs() {
        return "/pages/about-us/about-us";
    }

    @GetMapping("/nexus-of-protection")
    public String renderNexusOfProtection() {
        return "/pages/about-us/nexus-of-protection";
    }

    @GetMapping("/digital-legacy")
    public String renderDigitalLegacy() {
        return "/pages/about-us/digital-legacy";
    }

    @GetMapping("/meet-us")
    public String renderMeetUs() {
        return "/pages/about-us/meet-us";
    }

    @GetMapping("/ethical-protocols")
    public String renderEthicalProtocols() {
        return "/pages/about-us/ethical-protocols";
    }

    @GetMapping("/work-opportunities")
    public String renderWorkOpportunities() {
        return "/pages/about-us/work-opportunities";
    }

    @GetMapping("/jobs")
    public String renderJobs() {
        return "/pages/about-us/jobs";
    }

    @GetMapping("/holo-press")
    public String renderHoloPress() {
        return "/pages/about-us/holo-press";
    }

    @GetMapping("/security-protocols")
    public String renderSafetyProtocols() {
        return "/pages/about-us/security-protocols";
    }
}
