package sondage.entity.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Représente un sondé.
 */
@Entity
@Table(name = "RESPONDENTS", uniqueConstraints={@UniqueConstraint(columnNames={"email", "survey_id"})})
public class Respondent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email", length = 254, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+", message = "{respondent.email.invalid}")
    @Size(min = 5, max = 254, message = "{respondent.email.badSize}")
    @Email
    private String email;

    @ElementCollection
    @CollectionTable(name = "respondent_tags", joinColumns = @JoinColumn(name = "respondent_id"))
    @Column(name = "tag")
    private Collection<@Pattern(regexp = "[A-Za-z0-9]+([-]?[A-Za-z0-9]+)?", message = "{respondent.tag.invalid}") String> tags;

    @Column(name = "token", length = 100, nullable = true, unique = true)
    @Pattern(regexp = "^[A-Za-z0-9]+", message = "{respondent.token.invalid}")
    private String token;

    /**
     * Date d'expiration du token
     */
    @Column(name = "is_expired", nullable = false)
    private boolean isExpired;


    /**
     * Sondage assigné au sondé.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    /**
     * Assigniation final du sondé.
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_item_id")
    private SurveyItem finalItem;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void addTag(String tag){
        if(this.tags == null)
            this.tags = new HashSet<>();

        this.tags.add(tag);
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public SurveyItem getFinalItem() {
        return finalItem;
    }

    public void setFinalItem(SurveyItem finalItem) {
        this.finalItem = finalItem;
    }

    @Override
    public String toString() {
        return "Respondent{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", tags=" + tags +
                ", token='" + token + '\'' +
                '}';
    }
}
