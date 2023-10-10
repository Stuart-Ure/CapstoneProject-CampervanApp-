package com.codeclan.FinalProject.models;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_sustainability_history")
public class UserSustainabilityHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long history_id;
    private Long action_id;
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public UserSustainabilityHistory(Long history_id, Long action_id, Date date) {
        this.history_id = history_id;
        this.action_id = action_id;
        this.date = date;
    }
    public UserSustainabilityHistory(){}

    public Long getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Long history_id) {
        this.history_id = history_id;
    }



    public Long getAction_id() {
        return action_id;
    }

    public void setAction_id(Long action_id) {
        this.action_id = action_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}






