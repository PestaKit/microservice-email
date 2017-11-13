package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EmailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private Person sender;
//    private List<Person> recipients;

    private String subject;
    private String body;

    public long getId() {
        return id;
    }

//    public Person getSender() {
//        return sender;
//    }
//
//    public void setSender(Person sender) {
//        this.sender = sender;
//    }
//
//    public List<Person> getRecipients() {
//        return recipients;
//    }
//
//    public void setRecipients(List<Person> recipients) {
//        this.recipients = recipients;
//    }
//
//    public void addRecipient(Person recipient) {
//        recipients.add(recipient);
//    }
//
//    public void removeRecipient(Person recipient) {
//        recipients.remove(recipient);
//    }

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
