package sondage.algo;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sondage.entity.model.Choice;
import sondage.entity.model.Respondent;
import sondage.entity.model.SurveyItem;

public class AlgoAdapter {

    private List<Choice> choiceList;
    private List<Respondent> respondentsList;
    private List<SurveyItem> itemsList;

    public AlgoAdapter(List<Choice> choices){
        this.choiceList = choices;

        Set<Respondent> respondents = new HashSet<>();
        Set<SurveyItem> items = new HashSet<>();
        for(Choice c : choices) {
            respondents.add(c.getRespondent());
            items.add(c.getItem());
        }

        this.respondentsList = new ArrayList<>(respondents);
        this.itemsList = new ArrayList<>(items);
    }

    public List<Choice> getResult(){
        long[][] result = AlgoAffect.affectation(getNbRespondents(), getNbItems(), getRespons(), getChoix());

        for(int i=0; i<getNbRespondents(); i++ ) {
            for(int j=0; j<getNbItems(); j++ ) {
                System.err.print(result[i][j]);
            }
            System.err.println("");
        }



        List<Choice> finalChoices = new ArrayList<>();

        for(int i = 0; i < getNbRespondents(); ++i){
            for(Choice c : choiceList){
                //System.err.println("ITEM :" + c.getItem() + ": " + result[i][0]);
                //System.err.println("RESPOND :" + c.getRespondent() + ": " + result[i][1]);
                if(c.getItem().getId() != result[i][0]) continue;
                if(c.getRespondent().getId() != result[i][1]) continue;
                finalChoices.add(c);

            }

        }


        return finalChoices;
    }


    private long[][] getRespons(){
        long[][] tableRespons = new long[getNbRespondents() + 1][getNbItems() +1];

        for(int i = 0; i < tableRespons.length; ++i){
            for(int j = 0; j < tableRespons[i].length; ++j){
                if(i == 0 && j == 0) continue;
                if(j == 0) tableRespons[i][j] = this.respondentsList.get(i-1).getId();
                if(i == 0) tableRespons[i][j] = this.itemsList.get(j-1).getId();
                if(i != 0 && j != 0) break;
            }
        }

        System.err.println("avant : ");
        for(int i=0; i<getNbRespondents(); i++ ) {
            for(int j=0; j<getNbItems(); j++ ) {
                System.err.print(tableRespons[i][j]);
            }
            System.err.println("");
        }

        System.err.println("aprés : ");

        for(int i = 1; i < tableRespons.length; ++i){
            for(int j = 1; j < tableRespons[i].length; ++j){
                long idResp = tableRespons[0][i];
                long idItem = tableRespons[j][0];

                for(Choice c : this.choiceList){
                    if(c.getRespondent().getId() != idResp || c.getItem().getId() != idItem) continue;
                    tableRespons[i][j] = c.getScore();
                }
            }
        }

        return tableRespons;
    }

    private long[][] getChoix(){
        long [][] tab = new long[getNbItems()][3];

        for(int i=0; i<tab.length; i++) {
            //besoin de if ou pas pour selectionner le sondage
            for(int j=0; j<2; j++) {
                if(j==0)tab[i][j] = this.itemsList.get(i).getId();
                if(j==1)tab[i][j] = this.itemsList.get(j).getNbPersMin();
                if(j==2)tab[i][j] = this.itemsList.get(j).getNbPersMax();
            }
        }

        return tab;
    }

    public int getNbRespondents(){
        return this.respondentsList.size();
    }

    private int getNbItems(){
        return this.itemsList.size();
    }
}