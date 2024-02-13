package com.artemnizhnyk.tasklist.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Request for login")
@Data
public class JwtRequest {

    @Schema(description = "Email", example = "johndoe@gmail.com")
    @NotNull(message = "Username must be not null")
    private String username;
    @Schema(description = "Password", example = "mysecretpassword")
    @NotNull(message = "Password must be not null")
    private String password;
}
