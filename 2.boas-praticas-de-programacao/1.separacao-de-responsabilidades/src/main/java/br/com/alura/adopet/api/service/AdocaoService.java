package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// A anotação @Service indica que a classe é um serviço, ou seja, uma classe que contém a lógica de negócio da aplicação.
@Service
// A classe AdocaoService é responsável por implementar a lógica de negócio relacionada às adoções.
public class AdocaoService {

    // A anotação @Autowired injeta uma instância de AdocaoRepository no atributo repository.
    @Autowired
    private AdocaoRepository repository; // O atributo repository é do tipo AdocaoRepository e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @Autowired injeta uma instância de JavaMailSender no atributo emailSender.
    @Autowired
    private JavaMailSender emailSender; // O atributo emailSender é do tipo JavaMailSender e será utilizado para enviar e-mails.

    // A anotação @Autowired injeta uma instância de EmailService no atributo emailService.
    @Autowired
    private EmailService emailService;

    // O método solicitar é responsável por registrar uma solicitação de adoção no sistema, recebendo um objeto do tipo Adocao como parâmetro.
    public void solicitar(Adocao adocao) {
        // A condição verifica se o pet já foi adotado se sim executa o bloco de código do if, caso contrário executa o bloco de código do else.
        if (adocao.getPet().getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!"); // A exceção ValidacaoException é lançada com a mensagem "Pet já foi adotado!".
        } else {
            List<Adocao> adocoes = repository.findAll(); // O método findAll do repositório é utilizado para buscar todas as adoções registradas no sistema.

            // Executa um loop for para percorrer a lista de adoções.
            for (Adocao a : adocoes) {
                // Se o tutor da adoção for igual ao tutor de uma adoção já registrada e o status da adoção for AGUARDANDO_AVALIACAO, executa o bloco de código do if.
                if (a.getTutor() == adocao.getTutor() && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    throw new ValidacaoException("Tutor já possui uma solicitação de adoção em andamento!"); // A exceção ValidacaoException é lançada com a mensagem "Tutor já possui uma solicitação de adoção em andamento!".
                }
            }
            // Executa um loop for para percorrer a lista de adoções.
            for (Adocao a : adocoes) {
                // Se o pet da adoção for igual ao pet de uma adoção já registrada e o status da adoção for AGUARDANDO_AVALIACAO, executa o bloco de código do if.
                if (a.getPet() == adocao.getPet() && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    throw new ValidacaoException("Pet já possui uma solicitação de adoção em andamento!"); // A exceção ValidacaoException é lançada com a mensagem "Pet já possui uma solicitação de adoção em andamento!".
                }
            }
            // Executa um loop for para percorrer a lista de adoções.
            for (Adocao a : adocoes) {
                int contador = 0; // A variável contador é inicializada com o valor 0.

                // Se o tutor da adoção for igual ao tutor de uma adoção já registrada e o status da adoção for APROVADO, executa o bloco de código do if.
                if (a.getTutor() == adocao.getTutor() && a.getStatus() == StatusAdocao.APROVADO) {
                    contador = contador + 1; // O contador é incrementado em 1.
                }
                // Se o contador for igual a 5, executa o bloco de código do if.
                if (contador == 5) {
                    throw new ValidacaoException("Tutor já possui 5 adoções aprovadas!"); // A exceção ValidacaoException é lançada com a mensagem "Tutor já possui 5 adoções aprovadas!".
                }
            }
        }

        adocao.setData(LocalDateTime.now()); // Atribui a data e hora atuais ao atributo data da adoção.
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO); // Atribui o status AGUARDANDO_AVALIACAO ao atributo status da adoção.
        repository.save(adocao); // O método save do repositório é utilizado para salvar a adoção no banco de dados.

        // O método enviarEmail do emailService é utilizado para enviar um e-mail ao tutor informando que a solicitação de adoção foi registrada.
        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(), // Define o destinatário do e-mail.
                "Solicitação de adoção", // Define o assunto do e-mail.
                "Olá " + adocao.getPet().getNome() + "!\n\nUma solicitação de adoção foi registrada hoje para o pet: " + adocao.getPet().getNome() + ". \nFavor avaliar para aprovação ou reprovação." // Define o conteúdo do e-mail.
        );
    }

    // O método aprovar é responsável por aprovar uma solicitação de adoção, recebendo um objeto do tipo Adocao como parâmetro.
    public void aprovar(Adocao adocao) {
        adocao.setStatus(StatusAdocao.APROVADO); // Atribui o status APROVADO ao atributo status da adoção.
        repository.save(adocao); // O método save do repositório é utilizado para salvar a adoção no banco de dados.

        // O método enviarEmail do emailService é utilizado para enviar um e-mail ao tutor informando que a adoção foi aprovada.
        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(), // Define o destinatário do e-mail.
                "Adoção aprovada", // Define o assunto do e-mail.
                "Parabéns " + adocao.getTutor().getNome() + "!\n\nSua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi aprovada.\nFavor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome() + " para agendar a busca do seu pet." // Define o conteúdo do e-mail.
        );
    }

    // O método reprovar é responsável por reprovar uma solicitação de adoção, recebendo um objeto do tipo Adocao como parâmetro.
    public void reprovar(Adocao adocao) {
        adocao.setStatus(StatusAdocao.REPROVADO); // Atribui o status REPROVADO ao atributo status da adoção.
        repository.save(adocao); // O método save do repositório é utilizado para salvar a adoção no banco de dados.

        // O método enviarEmail do emailService é utilizado para enviar um e-mail ao tutor informando que a adoção foi reprovada.
        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(), // Define o destinatário do e-mail.
                "Adoção reprovada", // Define o assunto do e-mail.
                "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus() // Define o conteúdo do e-mail.
        );
    }
}
