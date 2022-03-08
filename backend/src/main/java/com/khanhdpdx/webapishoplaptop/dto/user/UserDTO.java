package com.khanhdpdx.webapishoplaptop.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@PasswordMatches
public class UserDTO {
    @NotNull
    @NotEmpty(message = "Không được bỏ trống username")
    private String username;
    @NotNull
    @NotEmpty(message = "Không được bỏ trống email")
    @ValidEmail
    @ExistEmail
    private String email;
    @NotNull
    @NotEmpty(message = "Không được bỏ trống pasword")
    private String password;
    @NotNull
    @NotEmpty(message = "Không được bỏ trống confirmPassword")
    private String confirmPassword;
}
