package Email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class Email {

	public static boolean validarEmail(String Email) {

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

		boolean emailStatus = false;
		Matcher matcher = pattern.matcher(Email);

		if (matcher.matches()) {
			emailStatus = true;
		} else {
			emailStatus = false;
		}
		return emailStatus;

	}

	public static boolean RecuperarConta(int chave, String Email) {

		String meuEmail = "natur.suporte@gmail.com"; // Email que receber� a
														// mensagem
		String minhaSenha = "entra21naturnovasenha"; // Senha do email

		boolean opera��o = false;
		/*
		 * String meuEmail = "igorsemphoski@gmail.com"; // Email que receber� a
		 * mensagem String minhaSenha = "IgoR22554613"; // Senha do email
		 */
		SimpleEmail email = new SimpleEmail(); // Objeto
		email.setHostName("smtp.gmail.com"); // Configurando o Host, no caso o
												// gmail
		email.setSmtpPort(465); // Configurando a porta desse Host
		email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha)); // Vai
																				// autenticar
																				// a
																				// conex�o
		email.setSSLOnConnect(true); // Vai ativar a conex�o de forma segura
		String msg = "Ol�, Ficamos sabendo que voc� perdeu sua senha! N�o se preocupe, utilize o c�digo abaixo para recuperar sua senha! \n \nChave :"
				+ chave
				+ "\n \nTem alguma pergunta? Nos envie um email em natur.suporte@gmail.com ficaremos felizes em responder suas d�vidas! \nA equipe Natur agradece!";

		try {
			email.setFrom(meuEmail); // Configura de quem � o e-mail; de onde
										// ele est� vindo
			email.setSubject("Recupera��o de conta."); // Titulo / Assunto
			email.setMsg(msg); // Conteudo
								// do
								// email
			email.addTo(Email); // Para onde ser� enviado a mensagem
			email.send(); // Envia a mensagem
			System.out.println("E-mail foi enviado com sucesso!");
			opera��o = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			opera��o = false;
		}
		return opera��o;
	}
}
