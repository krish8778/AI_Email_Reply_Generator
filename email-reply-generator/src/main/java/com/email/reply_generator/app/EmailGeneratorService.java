package com.email.reply_generator.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient webClient){
        this.webClient = webClient;
    }

    public String generateReplyEmail(EmailRequest emailRequest){
        //Build the prompt
        String prompt = generatePrompt(emailRequest);

        //Craft the request
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        //Do request and get response

        String response = webClient.post()
                .uri(geminiApiUrl+geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //Extract and Return the Response

        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        }catch(Exception e){
            return "Error in Processing the Response : " + e.getMessage();
        }
    }

    private String generatePrompt(EmailRequest emailRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("Generate a professional reply email for the following email content. please don't generate a subject line ");
        if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty())
            sb.append("use a ").append(emailRequest.getTone()).append(" tone ");
        sb.append("\nOriginal email : ").append(emailRequest.getEmailContent());
        return sb.toString();
    }
}
