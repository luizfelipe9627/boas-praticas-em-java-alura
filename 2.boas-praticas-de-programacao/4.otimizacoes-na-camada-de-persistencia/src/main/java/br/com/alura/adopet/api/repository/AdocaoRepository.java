package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

// Criado o repositório de Adocao que extende o JpaRepository responsável por realizar as operações de CRUD no banco de dados.
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    boolean existsByPetIdAndStatus(Long petId, StatusAdocao status); // O método existsByPetIdAndStatus recebe um Long petId e um String status como parâmetros e é responsável por verificar se existe uma adoção com o petId e status informados retornando um boolean.


}
