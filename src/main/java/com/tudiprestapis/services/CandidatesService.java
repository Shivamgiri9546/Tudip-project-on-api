package com.tudiprestapis.services;

import com.tudiprestapis.entity.Candidate;
import com.tudiprestapis.payload.CandidateResponse;
import com.tudiprestapis.payload.CandidatesDto;

import java.util.List;

public interface CandidatesService {
    CandidatesDto createCandidates(CandidatesDto candidatesDto);

    CandidateResponse getAllCandidates(Integer pageNumber , Integer pageSize, String sortBy, String sortDir);

    List<CandidatesDto>searchCandidates(String keys);
}
