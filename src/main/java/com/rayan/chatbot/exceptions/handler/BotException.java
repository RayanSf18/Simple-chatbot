package com.rayan.chatbot.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BotException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Chatbot internal server error.");
        return problemDetail;
    }
}
