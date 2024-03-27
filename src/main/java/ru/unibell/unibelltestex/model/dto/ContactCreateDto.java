package ru.unibell.unibelltestex.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.unibell.unibelltestex.util.ExceptionMessage;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactCreateDto {

    @NotBlank(message = ExceptionMessage.NON_VALID_NAME)
    private String contactName;

    @Pattern(regexp = "[+0-9-]*", message = ExceptionMessage.NON_VALID_PHONE_NUMBER)
    private String phoneNumber;

    @Email(message = ExceptionMessage.NON_VALID_EMAIL)
    private String email;

}
