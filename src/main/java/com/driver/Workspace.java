package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public ArrayList<Meeting> getCalendar() {
        return calendar;
    }

    public void setCalendar(ArrayList<Meeting> calendar) {
        this.calendar = calendar;
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        List<Meeting> list = new ArrayList<>(calendar);


        list.sort(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting m1, Meeting m2) {
                if (m1.getEndTime().isBefore(m2.getEndTime())) return -1;
                if (m1.getEndTime().equals(m2.getEndTime())) return -1;
                return 1;
            }
        });

        int count = 1;
        LocalTime endOfMeeting = list.get(0).getEndTime();
        for (int i = 1; i < list.size()-1; i++) {
           Meeting meeting = list.get(i);
           if(endOfMeeting.isBefore(meeting.getStartTime())){
               count++;
               endOfMeeting = meeting.getEndTime();
           }
        }
        return count;
    }
}
