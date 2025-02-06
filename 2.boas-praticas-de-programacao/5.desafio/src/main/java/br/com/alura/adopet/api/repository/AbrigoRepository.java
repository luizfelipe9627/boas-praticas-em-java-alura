package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

// Criado o repositório de Abrigo que extende o JpaRepository responsável por realizar as operações de CRUD no banco de dados.
public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    Abrigo findByNome(String nome); // Método que busca um abrigo pelo nome.

    boolean existsByNomeOrTelefoneOrEmail(String nome, String telefone, String email); // Método que verifica se já existe um abrigo com o nome, telefone ou email informado.
}
