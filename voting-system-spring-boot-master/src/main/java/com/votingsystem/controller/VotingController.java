/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.votingsystem.controller;

import com.votingsystem.entity.Candidate;
import com.votingsystem.entity.Citizen;
import com.votingsystem.repositories.CandidateRepo;
import com.votingsystem.repositories.CitizenRepo;

import java.util.List;
//import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oksana
 */
@Controller
public class VotingController {
    
    public final Logger logger = Logger.getLogger(VotingController.class);
    
    @Autowired
    CitizenRepo citizenRepo;
    
    @Autowired
    CandidateRepo candidateRepo;
    
    @RequestMapping("/")
    public String gotToVote(){
        logger.info("returning vote.html");
        return "vote.html";
    }
    
    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam String name, Model model, HttpSession session){        
        logger.info("getting citizen from db");
        
        Citizen citizen = citizenRepo.findByName(name);
        
        logger.info("putting citizen into session");
        session.setAttribute("citizen", citizen);
        
        if(citizen.getHasVoted()){
            return "/alreadyVoted.html";             
        }
        else
        {
            logger.info("putting candidates into model");
            List<Candidate> candidates = candidateRepo.findAll();            
            model.addAttribute("candidates", candidates);
            return "/performVote.html";   
        }
        
    }
    
    @RequestMapping(value = "/doLoginJs", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Citizen> doLoginJs(@RequestParam String name){        
        logger.info("getting citizen from db");
        
        Citizen citizen = citizenRepo.findByName(name);
        
        logger.info("putting citizen into session");
//        session.setAttribute("citizen", citizen);
        
//        return json
// to test with postman: get http://localhost:8181/doLoginJs?name=oks
            //return citizen; 
            return new ResponseEntity<>(citizen, HttpStatus.OK);  

    }
    
    @RequestMapping("/voteFor")
    public String voteFor(@RequestParam Long id, HttpSession session){
        Citizen ct = (Citizen)session.getAttribute("citizen");
        
        if(!ct.getHasVoted()){
            ct.setHasVoted(true);
            
            Candidate c = candidateRepo.findById(id);
            logger.info("voting for candidate " + c.getName());            
            c.setNumberOfVotes(c.getNumberOfVotes()+1);
            
            candidateRepo.save(c);            
            citizenRepo.save(ct);
            
            return "voted.html";
        }        
        return "alreadyVoted.html";        
    }
}
