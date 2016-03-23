package com.chart.share.controller;

import com.chart.share.domain.Activity;
import com.chart.share.domain.DomainType;
import com.chart.share.domain.Note;
import com.chart.share.repository.ActivityRepository;
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

    @Autowired
    private ActivityRepository activityRepository;

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
        String activityDescription = "Updated Note " + note.getTitle();
        if(id ==0) {
            id = sequenceGenerator.invoke();
            note.setId(id);
            activityDescription = "Added Note " + note.getTitle() ;

        }
        note.setDateAdded(new Date());
        note = noteRepository.save(note);
        activityRepository.save(new Activity(DomainType.NOTE,
                note.getId(),
                new Date(),
                activityDescription,
                note.getGroupId()));
        return note;

    }

    @RequestMapping(value = "note/{id}", method = RequestMethod.DELETE)
    public Boolean deleteNote(@PathVariable long id){
        noteRepository.delete(id);
        return true;
    }

    @RequestMapping(value = "note/group/{groupId}", method = RequestMethod.GET)
    public List<Note> getNotesForGroup(@PathVariable long groupId){
       List noteList=  noteRepository.findByGroupIdOrderByDateAddedDesc(groupId);
        if(noteList.size() > 5){
            noteList = noteList.subList(0,4);
        }
        return  noteList;
    }


}
