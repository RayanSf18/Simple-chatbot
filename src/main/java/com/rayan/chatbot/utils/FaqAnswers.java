package com.rayan.chatbot.utils;

import com.rayan.chatbot.domain.FaqAnswer;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FaqAnswers {

    private final Logger logger = LoggerFactory.getLogger(FaqAnswers.class);

    private List<FaqAnswer> answers = new ArrayList<>();
    private String defaultAnswer;

    public FaqAnswers() {
        try {
            JSONTokener tokener = new JSONTokener(new FileInputStream("src/main/resources/static/answers.json"));
            JSONObject faqData = new JSONObject(tokener);

            JSONArray faqArray = faqData.getJSONArray("faq");

            for (int i = 0; i < faqArray.length(); i++) {
                JSONObject faqEntry = faqArray.getJSONObject(i);

                JSONArray keywordsArray = faqEntry.getJSONArray("keywords");
                List<String> keywords = new ArrayList<>();

                for (int j = 0; j < keywordsArray.length(); j++) {
                    keywords.add(keywordsArray.getString(j));
                }

                String answer = faqEntry.getString("answer");

                this.answers.add(new FaqAnswer(keywords, answer));
            }

            this.defaultAnswer = faqData.getString("default");
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", e.getMessage());
        }
    }
}