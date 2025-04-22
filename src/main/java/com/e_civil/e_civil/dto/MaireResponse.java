package com.e_civil.e_civil.dto;

import com.e_civil.e_civil.Enum.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaireResponse {
    private Long id;
    private String email;
    private EnumRole role;
    private String message;
    private boolean success;
}
