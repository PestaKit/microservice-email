package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to organize templates by tags or categories
 *
 * author: Loan Lassalle
 */
@Entity
public class EmailEntity implements Serializable {

    // TODO: JavaDoc et commentaires

    /**
     * Email's ID
     * Used to identify email by database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Email's url
     * Used to get the email
     */
    private String url;

    /**
     * Email's sender
     */
    private String sender;

    /**
     * Email's recipient list
     */
    @ElementCollection
    private List<String> recipients;

    /**
     * Email's carbon copy list
     */
    @ElementCollection
    private List<String> carbonCopy;

    /**
     * Email's blind carbon copy list
     */
    @ElementCollection
    private List<String> blindCarbonCopy;

    /**
     * Email's subject
     */
    private String subject;

    /**
     * Email's body
     */
    // TODO: Voir pour une gestion dynamique. class Text ?
    @Column(length=10000)
    private String body;

    public long getId() {
        return id;
    }

    /**
     * Get email's url
     *
     * @return email's url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set email's url
     *
     * @param url email's url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
        if (recipients == null) {
            recipients = new ArrayList<>();
        }

        recipients.add(recipient);
    }

    public void removeRecipient(String recipient) {
        if (recipients != null) {
            recipients.remove(recipient);
        }
    }

    public List<String> getCarbonCopy() {
        return carbonCopy;
    }

    public void setCarbonCopy(List<String> carbonCopy) {
        this.carbonCopy = carbonCopy;
    }

    public void addCarbonCopy(String recipient) {
        if (carbonCopy == null) {
            carbonCopy = new ArrayList<>();
        }

        carbonCopy.add(recipient);
    }

    public void removeCarbonCopy(String recipient) {
        if (carbonCopy != null) {
            carbonCopy.remove(recipient);
        }
    }

    public List<String> getBlindCarbonCopy() {
        return blindCarbonCopy;
    }

    public void setBlindCarbonCopy(List<String> blindCarbonCopy) {
        this.blindCarbonCopy = blindCarbonCopy;
    }

    public void addBlindCarbonCopy(String recipient) {
        if (blindCarbonCopy == null) {
            blindCarbonCopy = new ArrayList<>();
        }

        blindCarbonCopy.add(recipient);
    }

    public void removeBlindCarbonCopy(String recipient) {
        if (blindCarbonCopy != null) {
            blindCarbonCopy.remove(recipient);
        }
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
