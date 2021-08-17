package com.trainticket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
@Entity
public class Money {
    @Id
    @Column(name = "money_id")
    private String id;

    private String userId;
    private String money; //NOSONAR

    public Money(){
        //Default Constructor
    }

}
