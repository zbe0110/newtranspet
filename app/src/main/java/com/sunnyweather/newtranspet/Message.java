package com.sunnyweather.newtranspet;
//消息实体
public class Message {
    private String message;
    //消息id
    private String messageId;
    private long time;
    //true：用户 false：客服
    private Boolean isUser;
    //0：已发送 1：已读
    private int type;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Boolean getUser() {
        return isUser;
    }

    public void setUser(Boolean user) {
        isUser = user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
