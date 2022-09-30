/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.votingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 * @author Oksana
 */
@Entity
@Table(name="citizens")
public class Citizen {
    
    @Id
    @Column(name="id")
    private Long id;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    @Column(name="citizen_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Citizen(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Citizen() {
    }
    
    @Column(name="hasvoted")
    private Boolean hasVoted;

    public Boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(Boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
    
}
