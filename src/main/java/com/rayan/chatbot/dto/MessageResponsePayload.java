package com.rayan.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload for response th answer")
public record MessageResponsePayload(

    @Schema(description = "Response", example = "O primeiro filme da saga Star Wars, 'Uma Nova Esperança', foi lançado em 1977, criado por George Lucas.")
    String response

) {
}
