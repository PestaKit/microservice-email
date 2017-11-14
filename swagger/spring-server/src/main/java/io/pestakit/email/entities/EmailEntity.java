package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class EmailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sender;

    @ElementCollection
    private List<String> recipients;

    @ElementCollection
    private List<String> carbonCopy;

    @ElementCollection
    private List<String> blindCarbonCopy;

    private String subject;
    private String body;

    public long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public void addRecipient(String recipient) {
        recipients.add(recipient);
    }

    public void removeRecipient(String recipient) {
        recipients.remove(recipient);
    }

    public List<String> getCarbonCopy() {
        return carbonCopy;
    }

    public void setCarbonCopy(List<String> carbonCopy) {
        this.carbonCopy = carbonCopy;
    }

    public void addCarbonCopy(String recipient) {
        carbonCopy.add(recipient);
    }

    public void removeCarbonCopy(String recipient) {
        carbonCopy.remove(recipient);
    }

    public List<String> getBlindCarbonCopy() {
        return blindCarbonCopy;
    }

    public void setBlindCarbonCopy(List<String> blindCarbonCopy) {
        this.blindCarbonCopy = blindCarbonCopy;
    }

    public void addBlindCarbonCopy(String recipient) {
        blindCarbonCopy.add(recipient);
    }

    public void removeBlindCarbonCopy(String recipient) {
        blindCarbonCopy.remove(recipient);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
