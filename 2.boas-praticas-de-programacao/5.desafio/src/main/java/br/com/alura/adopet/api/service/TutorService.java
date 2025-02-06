package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// A anotação @Service indica que a classe é um serviço, ou seja, uma classe que contém a lógica de negócio da aplicação.
@Service
public class TutorService {

    // A anotação @Autowired injeta uma instância de AbrigoRepository no atributo repository.
    @Autowired
    private TutorRepository tutorRepository; // O atributo tutorRepository é do tipo TutorRepository e será utilizado para acessar os métodos de persistência de dados.

    // O método cadastrar é responsável por cadastrar um tutor, recebendo um objeto do tipo Tutor como parâmetro.
    public void cadastrar(CadastroTutorDto dto) {
        boolean jaCadastrado = tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email()); // O método existsByTelefoneOrEmail do repositório é utilizado para verificar se o telefone ou e-mail do tutor já estão cadastrados.

        // Se o telefone ou e-mail do tutor já estiverem cadastrados, executa o bloco de código do if, caso contrário executa o bloco de código do else.
        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!"); // A resposta da requisição é uma mensagem de erro.
        }

        tutorRepository.save(new Tutor(dto)); // O método save do repositório é utilizado para salvar o tutor no banco de dados.
    }


    // O método atualizar é responsável por atualizar um tutor, recebendo um objeto do tipo Tutor como parâmetro.
    public void atualizar(AtualizacaoTutorDto dto) {
        Tutor tutor = tutorRepository.getReferenceById(dto.id()); // O método getReferenceById do repositório é utilizado para buscar o tutor pelo idPet.
        tutor.atualizarDados(dto); // O método atualizarDados do tutor é utilizado para atualizar os dados do tutor.
    }
}
