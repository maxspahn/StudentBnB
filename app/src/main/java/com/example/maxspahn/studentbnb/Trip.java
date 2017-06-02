package com.example.maxspahn.studentbnb;

import java.util.Date;

/**
 * Created by Arturo on 31/05/2017.
 */

public class Trip {
    Date initialDate;
    Date finalDate;
    User visitingUser;
    User hostUser;
    Evaluation evaluation = null;
    //TODO option to evaluate trips

    public Trip(Date initialDate, Date finalDate, User u, User hostU) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.visitingUser = u;
        this.hostUser = hostU;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public User getVisitingUser() {
        return visitingUser;
    }

    public void setVisitingUser(User visitingUser) {
        this.visitingUser = visitingUser;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public User getHostUser() {
        return hostUser;
    }

    public void setHostUser(User hostUser) {
        this.hostUser = hostUser;
    }
}
