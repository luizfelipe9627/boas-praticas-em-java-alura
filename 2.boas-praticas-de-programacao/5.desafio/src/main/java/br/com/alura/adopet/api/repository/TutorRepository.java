package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

// Criado o repositório de Tutor que extende o JpaRepository responsável por realizar as operações de CRUD no banco de dados.
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByTelefoneOrEmail(String telefone, String email); // Método que verifica se um tutor já existe no banco de dados através do telefone ou email.

}
