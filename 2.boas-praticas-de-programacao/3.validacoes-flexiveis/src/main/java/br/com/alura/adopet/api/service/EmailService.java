package br.com.alura.adopet.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// A anotação @Service indica que a classe é um serviço, ou seja, uma classe que contém a lógica de negócio da aplicação.
@Service
// A classe EmailService é responsável por implementar a lógica de envio de e-mails.
public class EmailService {

    // A anotação @Autowired injeta uma instância de JavaMailSender no atributo emailSender.
    @Autowired
    private JavaMailSender emailSender; // O atributo emailSender é do tipo JavaMailSender e será utilizado para enviar e-mails.

    // O método enviarEmail é responsável por enviar um e-mail, recebendo um destinatário e uma mensagem como parâmetros.
    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage(); // Cria um objeto do tipo SimpleMailMessage responsável por enviar e-mails.
        email.setFrom("adopet@email.com"); // Define o remetente do e-mail.
        email.setTo(destinatario); // Define o destinatário do e-mail.
        email.setSubject(assunto); // Define o assunto do e-mail.
        email.setText(mensagem); // Define o texto do e-mail.
        emailSender.send(email); // O método send do emailSender é utilizado para enviar o e-mail.
    }
}
