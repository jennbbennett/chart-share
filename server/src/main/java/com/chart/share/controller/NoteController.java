package com.chart.share.controller;

import com.chart.share.domain.Note;
import com.chart.share.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            note.set(id);
        }
        return noteRepository.save(note);
    }

    @RequestMapping(value = "note/{id}", method = RequestMethod.DELETE)
    public Boolean deleteNote(@PathVariable long id){
        noteRepository.delete(id);
        return true;
    }


}
