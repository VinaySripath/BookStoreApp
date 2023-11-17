package com.sprint.bspro.entity;

public class MailStructure {
	private String recipient;
    private String msgBody;
    private String subject;
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public MailStructure(String recipient, String msgBody, String subject) {
		super();
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
	}
	public MailStructure() {
		super();
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
    
}
