package com.embarkx.ChallengeApp.controller;

import com.embarkx.ChallengeApp.Challenge;
import com.embarkx.ChallengeApp.service.ChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if (isChallengeAdded) {
            return new ResponseEntity<>("Challenge added succesfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Challenge not added", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable() String month) {
        Challenge challenge= challengeService.getChallange(month);
        if (challenge != null){
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>updateChallenge(@PathVariable Long id, @RequestBody Challenge challenge){
      boolean isChallegedUpdated = challengeService.updateChallege(id, challenge);
        if (isChallegedUpdated) {
            return new ResponseEntity<>("Challenge updated succesfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Challenge is not updated succesfully", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable("id") Long id){
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);
        if (isChallengeDeleted){
            return new ResponseEntity<>("Challege deleted succesfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>( "Challege is not deleted succesfully", HttpStatus.NOT_FOUND);
        }
    }
}
