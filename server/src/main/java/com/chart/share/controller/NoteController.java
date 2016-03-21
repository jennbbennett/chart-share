package com.chart.share.controller;

import com.chart.share.domain.DomainType;
import com.chart.share.domain.Note;
import com.chart.share.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by jenn on 3/20/16.
 */

@RestController
@RequestMapping("/service")
public class NoteController {


    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(value = "/note/{targetType}/{targetId}", method = RequestMethod.GET)
    public List<Note> getNotesForTarget(@PathVariable DomainType targetType, @PathVariable Long targetId){
        return noteRepository.findByTargetTypeAndTargetId(targetType, targetId);
    }

    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable long id){
        Note returnNote;
        returnNote = noteRepository.findOne(id);
        return returnNote;
    }

    @RequestMapping(value = "/note", method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note){
        long id = note.getId();
        if(id ==0) {
            id = sequenceGenerator.invoke();
            note.setId(id);
        }
        note.setDateAdded(new Date());
        return noteRepository.save(note);
    }

    @RequestMapping(value = "note/{id}", method = RequestMethod.DELETE)
    public Boolean deleteNote(@PathVariable long id){
        noteRepository.delete(id);
        return true;
    }


}
