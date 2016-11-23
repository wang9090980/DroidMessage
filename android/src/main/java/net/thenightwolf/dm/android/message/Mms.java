package net.thenightwolf.dm.android.message;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;

public class Mms implements Comparable<Mms> {
    private String subject;
    private String sender;
    private String senderNumber;
    private final ArrayList<String> recipients = new ArrayList<String>();
    private final ArrayList<String> recipientsNumber = new ArrayList<String>();
    private String message = "";
    private Bitmap bitmap;
    private final String id;
    private final Date date;
    
    public Mms(String subject, Date date, String id) {
        this.setSubject(subject);
        this.date = date;
        this.id = id;
    }

    @Override
    public int compareTo(Mms another) {
        return date.compareTo(another.date);
    }
    
    public void appendMessage(String message) {
        this.message += message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String senderNumber, String sender) {
        this.senderNumber = senderNumber;
        this.sender = sender;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void addRecipient(String recipientNumber, String name) {
        recipientsNumber.add(recipientNumber);
        recipients.add(name);
    }

    public String getRecipientNumbers() {
        return Tools.join(recipientsNumber, ", ");
    }

    public String getRecipients() {
        return Tools.join(recipients, ", ");
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getId() {
        return id;
    }    
    
    public Date getCreatedDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Mms{" +
                "subject='" + subject + '\'' +
                ", sender='" + sender + '\'' +
                ", senderNumber='" + senderNumber + '\'' +
                ", recipients=" + recipients +
                ", recipientsNumber=" + recipientsNumber +
                ", message='" + message + '\'' +
                ", bitmap=" + bitmap +
                ", id='" + id + '\'' +
                ", date=" + date +
                '}';
    }
}