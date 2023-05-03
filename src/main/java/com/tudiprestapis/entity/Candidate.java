package com.tudiprestapis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="candidates_details")

public class Candidate{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(name ="name", nullable=false, length=45)
        private String name;
        @Column(name ="phoneNumber", nullable=false, length=15, unique = true)
        private String phoneNumber;
        @Column(name ="email", nullable=false,  unique = true)
         private String email;

         private String address;

    }

