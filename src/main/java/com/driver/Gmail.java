package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Gmail extends Email {
    Queue<Mail> inboxQueue ;
    Queue<Mail> trashQueue;
    int inboxCapacity; //maximum number of mails inbox can store



    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inboxQueue = new LinkedList<>();
        trashQueue = new LinkedList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inboxCapacity < 0) return;
        if(inboxQueue.size() == inboxCapacity)trashQueue.offer(inboxQueue.poll());
        inboxQueue.offer(new Mail(date,sender,message));

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int size = inboxQueue.size();

        while (size > 0){
            Mail mail = inboxQueue.poll();
            assert mail != null;
            if(mail.message.equals(message))trashQueue.offer(mail);
            else inboxQueue.offer(mail);
            size--;
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        int size = inboxQueue.size();
        if(size == 0)return null;
        String msg = "";
        while (size > 0){
            Mail mail = inboxQueue.peek();
            assert mail != null;
            if(size == 1)msg = mail.message;
            size--;
        }
        return msg;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        int size = inboxQueue.size();
        if(size == 0)return null;
        return inboxQueue.peek().message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        int size = inboxQueue.size();
        if(size == 0)return count;
        while (size > 0){
            Mail mail = inboxQueue.poll();
           if(mail.date.compareTo(start) >= 0 && mail.date.compareTo(end) <= 0)count++;
            inboxQueue.offer(mail);
            size--;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxQueue.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashQueue.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashQueue.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
    public Queue<Mail> getInboxQueue() {
        return inboxQueue;
    }

    public void setInboxQueue(Queue<Mail> inboxQueue) {
        this.inboxQueue = inboxQueue;
    }

    public Queue<Mail> getTrashQueue() {
        return trashQueue;
    }

    public void setTrashQueue(Queue<Mail> trashQueue) {
        this.trashQueue = trashQueue;
    }

    public void setInboxCapacity(int inboxCapacity) {
        this.inboxCapacity = inboxCapacity;
    }
    private class Mail {
        Date date;
        String sender;
        String message;
        Mail(Date date,String sender,String message){
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }
}
