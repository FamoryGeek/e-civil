package com.e_civil.e_civil.dto;

import com.e_civil.e_civil.Enum.EnumRole;
import com.e_civil.e_civil.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private Utilisateur user;
    private EnumRole role;
}
