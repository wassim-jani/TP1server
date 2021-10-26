import java.net.InetAddress;



public class Etudiant {
	
	
	private String nom,login,niveau;
	private int port;
	private boolean connected;
	private InetAddress Add;
	private boolean etat;
	private InetAddress adress;
	
	
	
	public Etudiant(String nom, String login, String niveau, boolean etat, InetAddress adress, int port) {
		super();
		this.nom = nom;
		this.login = login;
		this.niveau = niveau;
		this.etat = etat;
		this.adress = adress;
		this.port = port;
	}

	public InetAddress getAdd() {
		return Add;
	}

	public void setAdd(InetAddress add) {
		Add = add;
	}

	public Etudiant() {
		super();
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}



	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

}
