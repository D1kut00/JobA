package data.model;

import java.io.Serializable;

public class BotUser implements Serializable {
    private Long userId;
    private Long chatId;
    private String inputState;
    private String region;
    private String experience;
    private Integer salary;
    private String keyword;
    private Integer notifyHour;

    public BotUser() {} // Needed for Jackson

    public BotUser(Long userId, Long chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getNotifyHour() {
        return notifyHour;
    }

    public void setNotifyHour(Integer notifyHour) {
        this.notifyHour = notifyHour;
    }

    public String getInputState() {
        return inputState;
    }

    public void setInputState(String inputState) {
        this.inputState = inputState;
    }



}