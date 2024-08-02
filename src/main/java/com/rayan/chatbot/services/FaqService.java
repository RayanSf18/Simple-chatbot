package com.rayan.chatbot.services;
import com.rayan.chatbot.domain.FaqAnswer;
import com.rayan.chatbot.utils.FaqAnswers;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class FaqService {

    private final FaqAnswers faqAnswers = new FaqAnswers();

    public String getAnswer(String question) {

        String normalizedQuestion = normalizeQuestion(question);

        Set<String> words = new HashSet<>(Arrays.asList(normalizedQuestion.toLowerCase().split("\\s+")));
        words.removeIf(s -> s.length() <= 1);

        for (FaqAnswer entry : faqAnswers.getAnswers()) {
            for (String keyWord : entry.getKeywords()) {
                String normalizedKeyWord = normalizeQuestion(keyWord);
                if (words.contains(normalizedKeyWord)) {
                    return entry.getAnswer();
                }
            }
        }
        return faqAnswers.getDefaultAnswer();
    }

    private String normalizeQuestion(String question) {
        String normalized = Normalizer.normalize(question, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }


}
