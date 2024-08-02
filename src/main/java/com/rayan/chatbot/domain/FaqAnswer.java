package com.rayan.chatbot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FaqAnswer {

    private List<String> keywords;

    private String answer;
}
