package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @ElementCollection
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ParameterEntity> parameters;

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

    public List<ParameterEntity> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterEntity> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(ParameterEntity parameter) {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }

        parameters.add(parameter);
    }

    public void removeParameter(ParameterEntity parameter) {
        if (parameters != null) {
            parameters.remove(parameter);
        }
    }
}
