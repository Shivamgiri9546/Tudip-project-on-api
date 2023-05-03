package com.tudiprestapis.repositories;


import com.tudiprestapis.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatesRepo extends JpaRepository<Candidate, Integer> {

    List<Candidate>findByNameContaining(String name);

    }
