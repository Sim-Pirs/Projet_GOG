package sondage.entity.web;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sondage.entity.model.*;

import java.util.Collection;
import java.util.Date;

public interface IDirectoryManager {

    // créer un utilisateur anonyme
    User newUser();

    // identifier un utilisateur
    boolean login(User user, String email, String password);

    // oublier l'utilisateur
    void logout(User user);

    void sendAccessSurveyMail(String emailTo, String token, String surveyName);

    /* ****************** POLLSTER ******************** */
    Pollster findPollsterByEmail(String email);

    void savePollster(Pollster pollster);
    /* ***************** FIN POLLSTER ***************** */

    /* ******************* SURVEY ********************* */
    Survey saveSurvey(Survey survey);

    Survey findSurveyById(long id);

    Collection<Survey> findSurveyByPollsterId(long id);

    void deleteSurveyById(long id);
    /* ***************** FIN SURVEY ******************* */

    /* ****************** SURVEY ITEM ***************** */
    SurveyItem findSurveyItemById(long id);

    int updateSurveyById(long id,
                         String name,
                         String description,
                         Date endDate,
                         Pollster pollster,
                         Collection<SurveyItem> items,
                         Collection<Respondent> respondents);

    void deleteSurveyItemById(long id);
    /* *************** FIN SURVEY ITEM **************** */

    /* ****************** RESPONDENT ****************** */
    Respondent findRespondentById(long id);

    Respondent findRespondentByEmailAndSurveyId(String email, long id);

    Collection<Respondent> findAllRespondentsBySurveyId(long surveyId);

    Respondent findRespondentByToken(String token);

    void updateRespondentAccessById(long id, String token, boolean isExpired);

    void saveRespondent(Respondent respondent);

    void deleteRespondentsBySurveyId(long surveyId);
    /* **************** FIN RESPONDENT **************** */

    /* ******************** CHOICE ******************** */
    Choice findChoiceByRespondentIdAndItemId(long idResp, long idItem);

    Choice saveChoice(Choice choice);

    void deleteChoiceByRespondentIdAndItemId(long idResp, long idItem);
    /* ****************** FIN CHOICE ****************** */

}