package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

// Criado o repositório de Adocao que extende o JpaRepository responsável por realizar as operações de CRUD no banco de dados.
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

}
