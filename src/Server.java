import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;



public class Server {
	private static List<Groupe> groupe;
	private static List<Etudiant> etudiants;
	private static List<Message> messages;
	public static void main(String[] args) {
		groupe=new ArrayList<Groupe>();
		etudiants=new ArrayList<Etudiant>();
		messages=new ArrayList<Message>();
		try {
			DatagramSocket socket=new DatagramSocket(3000);
			System.out.println("serveur listening on port number 3000");
			while(true) {
				
				DatagramPacket packet =new DatagramPacket(new byte[1024], 1024);
				socket.receive(packet);
				String msgRecu=new String(packet.getData(),0,packet.getLength());
				//System.out.println(msgRecu);
				//---------login-----------
				if (msgRecu.startsWith("##")) {
					String[] etd=  msgRecu.split("#");
					Server.etudiants.add(new Etudiant(etd[3], etd[2], etd[4], true , packet.getAddress(),packet.getPort()));	
				}
				//--------affichier list étudiants
				else if (msgRecu.equals("#LIST")) {
					String msg="la liste des (noms) des etudiants connectés est: ";
					for (Etudiant etd : Server.etudiants) {
						msg=msg+etd.getNom()+" et ";
					}
					DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),packet.getAddress(),packet.getPort());
					socket.send(packet2);
				}
				/******************il faut integrer le sender ********************/
				//----------Histo---------
				else if (msgRecu.equals("#HISTO")) {
					Etudiant sender=new Etudiant();
					for (Etudiant etudiant : Server.etudiants) {
						if(etudiant.getPort()==packet.getPort()) {
							sender=etudiant;
						}
					}
					String msg="les message Envoyé par "+sender.getNom() +" sont :\n";
					for (Message msg1 : Server.messages) {
						if (msg1.getSender().getPort()==packet.getPort()) {
							msg=msg+msg1.getMessage()+":à "+msg1.getReciver().getNom()+" \n";
						}
					}
					
					DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),packet.getAddress(),packet.getPort());
					socket.send(packet2);
				}
				else if (msgRecu.equals("#GRPS")) {
					String msg="la liste de groupe:\n";
					for (Groupe gp : Server.groupe) {
						msg=msg+gp.getTitre()+"\n";
					}
					
					DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),packet.getAddress(),packet.getPort());
					socket.send(packet2);
				}
				/*******************creation de groupe *****************************/
				else if (msgRecu.startsWith("#GRP#")) {
					String [] msg1= msgRecu.split("#");
					List<Etudiant> le=new ArrayList<Etudiant>();
					for (Etudiant etudiant : Server.etudiants) {
						if(etudiant.getPort()==packet.getPort()) {
							le.add(etudiant);
						}
					}
					List<Message> lm=new ArrayList<Message>();
					Server.groupe.add(new Groupe(msg1[2],le,lm));
				}
				else if (msgRecu.startsWith("@#")) {
					Etudiant sender=new Etudiant();
					for (Etudiant etudiant : Server.etudiants) {
						if(etudiant.getPort()==packet.getPort()) {
							sender=etudiant;
						}
					}
					String [] msg1= msgRecu.split("@#");
					for (Etudiant etd : Server.etudiants) {
						if (etd.getLogin().equals(msg1[1])) {
							String msg=sender.getNom()+":"+msg1[2]+"\n";
							DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),etd.getAdd(),etd.getPort());
							socket.send(packet2);
							Server.messages.add(new Message(sender,etd,msg));
						}
					}
				}
				
				else if(msgRecu.startsWith("#>")) {
					String nomGroupe= msgRecu.substring(2) ;
					for (Groupe gp : Server.groupe) {
						if(gp.getTitre().equals(nomGroupe)) {
							List<Etudiant> l=gp.getListEtudiant();
							for (Etudiant etudiant : Server.etudiants) {
								if(etudiant.getPort()==packet.getPort()) {
									l.add(etudiant);
								}
							}
							
							gp.getListEtudiant();
						}
					}
				}
				else if(msgRecu.startsWith("#ETDS#")) {
					String nomGroupe= msgRecu.substring(6) ;
					String msgAEnvoyer ="les etudiants du groupe "+nomGroupe+" sont :\n\n " ;
					for (Groupe gp : Server.groupe) {
						if(gp.getTitre().equals(nomGroupe)) {
							for (Etudiant etudiant : gp.getListEtudiant()) {
								msgAEnvoyer=msgAEnvoyer+etudiant.getNom()+" \n\n";
							}
					
						}
					}
					DatagramPacket packet2=new DatagramPacket(msgAEnvoyer.getBytes(),msgAEnvoyer.length(),packet.getAddress(),packet.getPort());
					socket.send(packet2);
					
				}
				else if (msgRecu.startsWith("@>")) {
					Etudiant sender=new Etudiant();
					for (Etudiant etudiant : Server.etudiants) {
						if(etudiant.getPort()==packet.getPort()) {
							sender=etudiant;
						}
					}
					String [] ms=msgRecu.split("@>");
					for (Groupe gp : Server.groupe) {
						if(gp.getTitre().equals(ms[1])) {
							String msgAEnvoyer=ms[2];
							for (Etudiant et : gp.getListEtudiant()) {
								if (!(et.getPort()==sender.getPort())) {
									DatagramPacket packet2=new DatagramPacket(msgAEnvoyer.getBytes(),msgAEnvoyer.length(),et.getAdd(),et.getPort());
									socket.send(packet2);
									Server.messages.add(new Message(sender,et,msgAEnvoyer));
								}
								
							}
						}
					}
					
				}
				
			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}

}