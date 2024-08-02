package com.rayan.chatbot.controllers;

import com.rayan.chatbot.dto.MessageRequestPayload;
import com.rayan.chatbot.dto.MessageResponsePayload;
import com.rayan.chatbot.services.FaqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/chat")
@Tag(name = "FaqController")
public class FaqController {

    private final Logger logger = LoggerFactory.getLogger(FaqController.class);

    private final FaqService faqService;

    @Operation(summary = "Register a answer", method = "POST", description = "This endpoint register a new answer.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Answer send with successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponsePayload.class))),
        @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponsePayload> answerQuestion(@Valid @RequestBody MessageRequestPayload payload) {
        String response = faqService.getAnswer(payload.question());
        return ResponseEntity.ok().body(new MessageResponsePayload(response));
    }
}
