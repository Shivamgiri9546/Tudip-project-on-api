package com.tudiprestapis.services.Impl;

import com.tudiprestapis.entity.Candidate;
import com.tudiprestapis.payload.CandidateResponse;
import com.tudiprestapis.payload.CandidatesDto;
import com.tudiprestapis.repositories.CandidatesRepo;
import com.tudiprestapis.services.CandidatesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatesServiceImpl implements CandidatesService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  CandidatesRepo candidatesRepo;

    @Override
    public CandidatesDto createCandidates(CandidatesDto candidatesDto) {
      Candidate candidate= dtoToCandidate(candidatesDto);
      Candidate saveCandidate=candidatesRepo.save(candidate);

            return candidatesToDto(saveCandidate);
        }
        @Override
     public CandidateResponse getAllCandidates(Integer pageNumber , Integer pageSize, String sortBy, String sortDir) {

        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }else {
            sort=Sort.by(sortBy).descending();
        }


            Pageable p= PageRequest.of(pageNumber,pageSize, sort);
            Page<Candidate> pageCandidates=candidatesRepo.findAll(p);
            List<Candidate> listCandidates=pageCandidates.getContent();

        List<CandidatesDto> candidatesDtos = listCandidates.stream().map(candidate -> candidatesToDto(candidate)).collect(Collectors.toList());
        CandidateResponse candidateResponse=new CandidateResponse();
        candidateResponse.setContent(candidatesDtos);
        candidateResponse.setPageNumber(pageCandidates.getNumber());
        candidateResponse.setPageSize(pageCandidates.getSize());
        candidateResponse.setTotalElements(pageCandidates.getTotalElements());
        candidateResponse.setTotalPages(pageCandidates.getTotalPages());
        candidateResponse.setLastPage(pageCandidates.isLast());

        return candidateResponse;
    }

    @Override
    public List<CandidatesDto> searchCandidates(String keys) {
        List<Candidate> candidates = candidatesRepo.findByNameContaining(keys);
        List<CandidatesDto> candidatesDto1 = candidates.stream().map((candidate) -> modelMapper.map(candidate, CandidatesDto.class))
                .collect(Collectors.toList());
        return candidatesDto1;
    }

    private Candidate dtoToCandidate(CandidatesDto candidatesDto) {
        Candidate candidate= modelMapper.map(candidatesDto,Candidate.class);
               return candidate;
    }
    private CandidatesDto candidatesToDto(Candidate candidate){
        CandidatesDto candidatesDto=modelMapper.map(candidate, CandidatesDto.class);
        return candidatesDto;
    }

}
