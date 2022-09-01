package com.mytest.mail.model;

import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Getter
public class SenderDto {

    private String from;
    private String to;
    private String subject;
    private String content;

    @Builder
    public SenderDto(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public SendEmailRequest toSendRequestDto() {
        Destination destination = new Destination().withToAddresses(this.to);

        Message message = new Message()
            .withSubject(createContent(this.subject))
            .withBody(new Body().withHtml(createContent(this.content)));

        return new SendEmailRequest()
            .withSource(this.from)
            .withDestination(destination).withMessage(message);
    }

    private Content createContent(String text) {
        return new Content().withCharset("UTF-8")
            .withData(text);
    }
}
