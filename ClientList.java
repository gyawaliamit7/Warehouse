import java.util.*;
import java.io.*;

public class ClientList implements Serializable {
	private static final long serialVersionUID = 1L;
	public LinkedList<Client> clients= new LinkedList<>();
	private static ClientList clientList;

	//constructor
	private ClientList(){

	}

	public static ClientList instance() {
		if(clientList == null) {
			return (clientList =new ClientList());
	  } else {
		  return clientList;
    }
  }

	public boolean insertClient (Client client){
		clients.add(client);
		return true;
	}

  public Client searchClient(String id)
  {
    Iterator allClients = clientList.getClients();
     while (allClients.hasNext())
     {
      Client newclient = (Client)(allClients.next());
      String checkId =newclient.getId();
      if (checkId.equals(id))
      {
        return newclient;
      }
     }    
     return null;
  }

  public boolean contains(String id)
  {
    Iterator clients = clientList.getClients();
     while (clients.hasNext())
     {
      Client newclient = (Client)(clients.next());
      String checkId = newclient.getId();
      if (checkId == id) {
        return true;
      }
     }    
     return false;
  }

	public Iterator getClients(){
		return clients.iterator();
	}

	private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(clientList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

  	private void readObject(java.io.ObjectInputStream input) {
    try {
      if (clientList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (clientList == null) {
          clientList = (ClientList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return clients.toString();
  }


}