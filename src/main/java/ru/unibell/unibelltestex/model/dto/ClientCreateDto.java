package ru.unibell.unibelltestex.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.unibell.unibelltestex.util.ExceptionMessage;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientCreateDto {

    @NotBlank(message = ExceptionMessage.NON_VALID_NAME)
    private String name;

}
