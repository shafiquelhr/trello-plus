package com.trelloplus.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleTestController {

    @GetMapping("/admin/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "ADMIN access granted!";
    }

    @GetMapping("/dev/test")
    @PreAuthorize("hasAnyRole('DEVELOPER', 'LEAD')")
    public String devAndLeadOnly() {
            return "DEVELOPER or LEAD access granted!";
    }

    @GetMapping("/view/test")
    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR', 'LEAD', 'DEVELOPER', 'INTERNEE')")
    public String allRolesAccess() {
        return "Any authenticated role has access!";
    }
}
