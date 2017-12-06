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

    /**
     * Get email's ID
     *
     * @return email's ID
     */
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

    /**
     * Get email's sender
     *
     * @return email's sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Set email's sender
     *
     * @param sender email's sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Get email's recipient list
     *
     * @return email's recipient list
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Set email's recipient list
     *
     * @param recipients email's recipient list
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    /**
     * Add an email's recipient in the recipient list
     *
     * @param recipient recipient to add
     */
    public void addRecipient(String recipient) {
        if (recipients == null) {
            recipients = new ArrayList<>();
        }

        recipients.add(recipient);
    }

    /**
     * Remove an email's recipient in the recipient list
     *
     * @param recipient recipient to remove
     */
    public void removeRecipient(String recipient) {
        if (recipients != null) {
            recipients.remove(recipient);
        }
    }

    /**
     * Get email's carbon copy list
     *
     * @return email's carbon copy list
     */
    public List<String> getCarbonCopy() {
        return carbonCopy;
    }

    /**
     * Set email's carbon copy list
     *
     * @param carbonCopy email's carbon copy list
     */
    public void setCarbonCopy(List<String> carbonCopy) {
        this.carbonCopy = carbonCopy;
    }

    /**
     * Add an email's carbon copy in the carbon copy list
     *
     * @param cc carbon copy to add
     */
    public void addCarbonCopy(String cc) {
        if (carbonCopy == null) {
            carbonCopy = new ArrayList<>();
        }

        carbonCopy.add(cc);
    }

    /**
     * Remove an email's carbon copy in the carbon copy list
     *
     * @param cc carbon copy to remove
     */
    public void removeCarbonCopy(String cc) {
        if (carbonCopy != null) {
            carbonCopy.remove(cc);
        }
    }

    /**
     * Get email's blind carbon copy list
     *
     * @return email's blind carbon copy list
     */
    public List<String> getBlindCarbonCopy() {
        return blindCarbonCopy;
    }

    /**
     * Set email's blind carbon copy list
     *
     * @param blindCarbonCopy email's blind carbon copy list
     */
    public void setBlindCarbonCopy(List<String> blindCarbonCopy) {
        this.blindCarbonCopy = blindCarbonCopy;
    }

    /**
     * Add an email's carbon copy in the blind carbon copy list
     *
     * @param bcc blind carbon copy to add
     */
    public void addBlindCarbonCopy(String bcc) {
        if (blindCarbonCopy == null) {
            blindCarbonCopy = new ArrayList<>();
        }

        blindCarbonCopy.add(bcc);
    }

    /**
     * Remove an email's carbon copy in the blind carbon copy list
     *
     * @param bcc blind carbon copy to remove
     */
    public void removeBlindCarbonCopy(String bcc) {
        if (blindCarbonCopy != null) {
            blindCarbonCopy.remove(bcc);
        }
    }

    /**
     * Get email's subject
     *
     * @return email's subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set email's body
     *
     * @param subject email's subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Get email's body
     *
     * @return email's body
     */
    public String getBody() {
        return body;
    }

    /**
     * Set email's body
     *
     * @param body email's body
     */
    public void setBody(String body) {
        this.body = body;
    }
}
