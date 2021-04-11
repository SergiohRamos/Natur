package Email;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

public class EmailComAnexo {
	
	public static void main(String[] args) {
		
		//Enviando E-mail com anexo
		
		String meuEmail = "natur.suporte@gmail.com"; 																							 //Email que receber� a mensagem
		String minhaSenha = "";																												 //Senha do email
		
		MultiPartEmail email = new MultiPartEmail(); 																									//Objeto 
		email.setHostName("smtp.gmail.com"); 																											  //Configurando o Host, no caso o gmail
		email.setSmtpPort(465); 																																		 //Configurando a porta desse Host
		email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));											 //Vai autenticar a conex�o
	    email.setSSLOnConnect(true); 																															//Vai ativar a conex�o de forma segura
	    
	    try {
	    	email.setFrom(meuEmail);   																																//Configura de quem � o e-mail; de onde ele est� vindo
	    	email.setSubject("E-mail com anexo"); 																											//Titulo / Assunto
	    	email.setMsg("Enviando email com anexo, segue os anexos abaixo......");													 //Conteudo do email
	    	email.addTo(meuEmail); 																																	//Para onde ser� enviado a mensagem
	    	
	    	EmailAttachment anexo = new EmailAttachment();																				       //Objeto que envia arquivos em anexo
	    	anexo.setPath("C:/Users/Igor Assis/Pictures/Imagens/foto p linkedin.jpg");																							//Adiciona o caminho dos arquivos
	    	anexo.setName("Minha foto.jpg");																													//Adiciona o nome do arquivo e seu formato
	    	email.attach(anexo);																																				//attach (anexar) passa o objeto criado para o obj e-mail
	    	
	    	email.send(); 																																							//Envia a mensagem
	    	System.out.println("E-mail foi enviado com sucesso!");
	    	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

}
