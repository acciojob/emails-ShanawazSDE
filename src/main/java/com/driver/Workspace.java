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
        List<LocalTime> list = new ArrayList<>();
        for(Meeting meeting : calendar)list.add(meeting.getEndTime());

        Collections.sort(list, new Comparator<LocalTime>() {
            @Override
            public int compare(LocalTime o1, LocalTime o2) {
                return o1.compareTo(o2);
            }
        });

        int count = 0;

        for (int i = 0; i < list.size()-1; i++) {
            if(!(list.get(i).equals(list.get(i+1))))count++;
        }
        return count;
    }
}
