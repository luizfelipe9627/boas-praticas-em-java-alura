package br.com.alura.adopet.api.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

// A anotação @Service define que essa classe é um bean gerenciado pelo Spring.
@Service
// Anotação que define que essa classe só será carregada se o profile ativo for o default.
@Profile("default")
public class EmailServiceDev extends EmailService {

    // Método para enviar um email fake.
    public void enviarEmail(String to, String subject, String message) {
        System.out.println("Enviando email fake");
        System.out.println("Destinatario: " + to);
        System.out.println("Assunto: " + subject);
        System.out.println("Mensagem: " + message);
    }

}
