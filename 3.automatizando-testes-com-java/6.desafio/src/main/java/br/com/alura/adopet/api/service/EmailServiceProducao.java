package br.com.alura.adopet.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// A anotação @Service define que essa classe é um bean gerenciado pelo Spring.
@Service
// Anotação que define que essa classe só será carregada se o profile ativo for o de produção.
@Profile("producao")
public class EmailServiceProducao extends EmailService {

    // A anotação @Autowired injeta uma instância de JavaMailSender no atributo emailSender.
    @Autowired
    private JavaMailSender emailSender; // O atributo emailSender é do tipo JavaMailSender e será utilizado para enviar emails.

    // Método para enviar um email real.
    public void enviarEmail(String to, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        emailSender.send(email);
    }

}
