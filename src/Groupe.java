import java.util.List;

public class Groupe {
	
	private String titre;
	private List<Etudiant> listEtudiant;
	private List<Message> listMessage;
	
	
	
	public Groupe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Groupe(String titre, List<Etudiant> listEtudiant, List<Message> listMessage) {
		super();
		this.titre = titre;
		this.listEtudiant = listEtudiant;
		this.listMessage = listMessage;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public List<Etudiant> getListEtudiant() {
		return listEtudiant;
	}
	public void setListEtudiant(List<Etudiant> listEtudiant) {
		this.listEtudiant = listEtudiant;
	}
	public List<Message> getListMessage() {
		return listMessage;
	}
	public void setListMessage(List<Message> listMessage) {
		this.listMessage = listMessage;
	}

}
