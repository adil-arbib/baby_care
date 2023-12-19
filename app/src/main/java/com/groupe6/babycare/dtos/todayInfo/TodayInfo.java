package com.groupe6.babycare.dtos.todayInfo;

import com.groupe6.babycare.dtos.reminder.ReminderDTO;

import java.util.List;

public class TodayInfo {

    private List<ReminderDTO> todaysReminders;

    private int remindersCompleted;

    private int remindersNotCompleted;

    private String tip;

    public TodayInfo(List<ReminderDTO> todaysReminders, int remindersCompleted, int remindersNotCompleted, String tip) {
        this.todaysReminders = todaysReminders;
        this.remindersCompleted = remindersCompleted;
        this.remindersNotCompleted = remindersNotCompleted;
        this.tip = tip;
    }

    public List<ReminderDTO> getTodaysReminders() {
        return todaysReminders;
    }

    public void setTodaysReminders(List<ReminderDTO> todaysReminders) {
        this.todaysReminders = todaysReminders;
    }

    public int getRemindersCompleted() {
        return remindersCompleted;
    }

    public void setRemindersCompleted(int remindersCompleted) {
        this.remindersCompleted = remindersCompleted;
    }

    public int getRemindersNotCompleted() {
        return remindersNotCompleted;
    }

    public void setRemindersNotCompleted(int remindersNotCompleted) {
        this.remindersNotCompleted = remindersNotCompleted;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


}
