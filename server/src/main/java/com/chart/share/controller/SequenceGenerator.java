package com.chart.share.controller;


import com.chart.share.domain.Sequence;
import com.chart.share.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SequenceGenerator {

    @Autowired
    private  SequenceRepository sequenceRepository;
    public  long invoke() {
        long id = sequenceRepository.count() + 1;
        sequenceRepository.save(new Sequence(id));
        return id;
    }


}