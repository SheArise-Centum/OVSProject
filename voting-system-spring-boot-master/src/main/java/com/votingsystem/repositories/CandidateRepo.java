/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.votingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votingsystem.entity.Candidate;

/**
 *
 * @author Oksana
 */
@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Integer> {
    public Candidate findById(Long id);
}
