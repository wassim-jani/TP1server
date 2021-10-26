

public class Message {
	
	private Etudiant Sender,Reciver;
	private String message;
	
	
	public Message(Etudiant sender, Etudiant reciver, String message) {
		super();
		Sender = sender;
		Reciver = reciver;
		this.message = message;
	}
	public Etudiant getSender() {
		return Sender;
	}
	public void setSender(Etudiant sender) {
		Sender = sender;
	}
	public Etudiant getReciver() {
		return Reciver;
	}
	public void setReciver(Etudiant reciver) {
		Reciver = reciver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
