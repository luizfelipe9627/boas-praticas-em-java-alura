package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
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
    private AdocaoRepository adocaoRepository; // O atributo repository é do tipo AdocaoRepository e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @Autowired injeta uma instância de JavaMailSender no atributo emailSender.
    @Autowired
    private PetRepository petRepository; // O atributo petRepository é do tipo PetRepository e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @Autowired injeta uma instância de EmailService no atributo emailService.
    @Autowired
    private TutorRepository tutorRepository; // O atributo tutorRepository é do tipo TutorRepository e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @Autowired injeta uma instância de JavaMailSender no atributo emailSender.
    @Autowired
    private JavaMailSender emailSender; // O atributo emailSender é do tipo JavaMailSender e será utilizado para enviar e-mails.

    // A anotação @Autowired injeta uma instância de EmailService no atributo emailService.
    @Autowired
    private EmailService emailService;

    // A anotação @Autowired injeta uma instância de ValidacaoSolicitacaoAdocao no atributo validacaoSolicitacaoAdocao.
    @Autowired
    private List<ValidacaoSolicitacaoAdocao> validacoes; // O atributo validacoes é uma lista de ValidacaoSolicitacaoAdocao e será utilizado para armazenar as validações de solicitação de adoção.


    // O método solicitar é responsável por registrar uma solicitação de adoção no sistema, recebendo um objeto do tipo SolicitacaoAdocaoDto como parâmetro.
    public void solicitar(SolicitacaoAdocaoDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet()); // O método findById do repositório é utilizado para buscar um pet pelo id.
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor()); // O método findById do repositório é utilizado para buscar um tutor pelo id.

        validacoes.forEach(validacao -> validacao.validar(dto)); // O método forEach é utilizado para percorrer a lista de validações e chamar o método validar de cada validação.

        Adocao adocao = new Adocao(); // É criado um novo objeto do tipo Adocao.
        adocao.setData(LocalDateTime.now()); // Atribui a data e hora atuais ao atributo data da adoção.
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO); // Atribui o status AGUARDANDO_AVALIACAO ao atributo status da adoção.
        adocao.setPet(pet); // Atribui o pet ao atributo pet da adoção.
        adocao.setTutor(tutor); // Atribui o tutor ao atributo tutor da adoção.
        adocao.setMotivo(dto.motivo()); // Atribui o motivo ao atributo motivo da adoção.
        adocaoRepository.save(adocao); // O método save do repositório é utilizado para salvar a adoção no banco de dados.

        // O método enviarEmail do emailService é utilizado para enviar um e-mail ao tutor informando que a solicitação de adoção foi registrada.
        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(), // Define o destinatário do e-mail.
                "Solicitação de adoção", // Define o assunto do e-mail.
                "Olá " + adocao.getPet().getNome() + "!\n\nUma solicitação de adoção foi registrada hoje para o pet: " + adocao.getPet().getNome() + ". \nFavor avaliar para aprovação ou reprovação." // Define o conteúdo do e-mail.
        );
    }

    // O método aprovar é responsável por aprovar uma solicitação de adoção, recebendo um objeto do tipo AprovacaoAdocaoDto como parâmetro.
    public void aprovar(AprovacaoAdocaoDto dto) {
        Adocao adocao = adocaoRepository.getReferenceById(dto.idAdocao()); // O método findById do repositório é utilizado para buscar uma adoção pelo id.
        adocao.setStatus(StatusAdocao.APROVADO); // Atribui o status APROVADO ao atributo status da adoção.

        // O método enviarEmail do emailService é utilizado para enviar um e-mail ao tutor informando que a adoção foi aprovada.
        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(), // Define o destinatário do e-mail.
                "Adoção aprovada", // Define o assunto do e-mail.
                "Parabéns " + adocao.getTutor().getNome() + "!\n\nSua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi aprovada.\nFavor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome() + " para agendar a busca do seu pet." // Define o conteúdo do e-mail.
        );
    }

    // O método reprovar é responsável por reprovar uma solicitação de adoção, recebendo um objeto do tipo ReprovacaoAdocaoDto como parâmetro.
    public void reprovar(ReprovacaoAdocaoDto dto) {
        Adocao adocao = adocaoRepository.getReferenceById(dto.idAdocao()); // O método findById do repositório é utilizado para buscar uma adoção pelo id.
        adocao.setStatus(StatusAdocao.REPROVADO); // Atribui o status REPROVADO ao atributo status da adoção.
        adocao.setJustificativaStatus(dto.justificativa()); // Atribui a justificativa ao atributo justificativaStatus da adoção.

        // O método enviarEmail do emailService é utilizado para enviar um e-mail ao tutor informando que a adoção foi reprovada.
        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(), // Define o destinatário do e-mail.
                "Adoção reprovada", // Define o assunto do e-mail.
                "Olá " + adocao.getTutor().getNome() + "!\n\nInfelizmente sua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi reprovada pelo abrigo " + adocao.getPet().getAbrigo().getNome() + " com a seguinte justificativa: " + adocao.getJustificativaStatus() // Define o conteúdo do e-mail.
        );
    }
}
