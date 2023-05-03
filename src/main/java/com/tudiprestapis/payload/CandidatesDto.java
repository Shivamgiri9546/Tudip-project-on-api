package com.tudiprestapis.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatesDto {
        private int id;
        private String name;
        private String phoneNumber;
        private String email;
        private String address;

    }


