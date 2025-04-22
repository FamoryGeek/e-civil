package com.e_civil.e_civil.controllers;

import com.e_civil.e_civil.dto.AdminRequest;
import com.e_civil.e_civil.dto.AdminResponse;
import com.e_civil.e_civil.dto.RegisterResponse;
import com.e_civil.e_civil.models.Admin;
import com.e_civil.e_civil.services.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
@CrossOrigin("*")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Admin>  getAdminById(@PathVariable Long id) {
        return  adminService.findById(id);
    }

    @PostMapping
    public ResponseEntity<AdminResponse> createAdmin(@Valid @RequestBody AdminRequest adminRequest) {
        try {
            AdminResponse response = adminService.create(adminRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            AdminResponse errorResponse = AdminResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PatchMapping("{id}")
    public Admin updateAdmin(@RequestBody Admin admin,@PathVariable Long id) {
        return adminService.update(admin, id);
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.delete(id);
    }

}
