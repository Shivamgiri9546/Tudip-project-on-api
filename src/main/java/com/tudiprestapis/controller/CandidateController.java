package com.tudiprestapis.controller;

import com.tudiprestapis.config.AppConstant;
import com.tudiprestapis.payload.CandidateResponse;
import com.tudiprestapis.payload.CandidatesDto;
import com.tudiprestapis.services.CandidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController { 

    @Autowired
    private CandidatesService candidatesService;
    
    //create data
    @PostMapping("/")
    public ResponseEntity<CandidatesDto> createCandidates(@RequestBody CandidatesDto candidatesDto){
        CandidatesDto createCandidate = candidatesService.createCandidates(candidatesDto);
        return  new ResponseEntity<>(createCandidate, HttpStatus.CREATED);
    }
    //get Data
    @GetMapping("/")
    public ResponseEntity<CandidateResponse> getAllCandidates(
            @RequestParam(value="pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value="pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY, required = false)String sortBy,
            @RequestParam(value="sortDir", defaultValue = AppConstant.SORT_DIR, required = false)String sortDir){
        CandidateResponse candidateResponse=candidatesService.getAllCandidates(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<CandidateResponse>(candidateResponse, HttpStatus.OK);
    }
    
    // search
    @GetMapping("/search/{keys}")
    public ResponseEntity<List<CandidatesDto>>searchCandidatesByName(
            @PathVariable("keys") String keys){
        List<CandidatesDto> result = candidatesService.searchCandidates(keys);
return  new ResponseEntity<List<CandidatesDto>>(result, HttpStatus.OK);
    }
}
