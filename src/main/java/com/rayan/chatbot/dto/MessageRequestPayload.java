package com.rayan.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Payload for new answer")
public record MessageRequestPayload(

    @Schema(description = "Answer", example = "Quando foi o inicio do starswars ?")
    @NotBlank String question

) {

}