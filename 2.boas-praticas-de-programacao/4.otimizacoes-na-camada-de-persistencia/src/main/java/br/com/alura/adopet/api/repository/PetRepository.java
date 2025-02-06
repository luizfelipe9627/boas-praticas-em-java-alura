package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

// Criado o repositório de Pet que extende o JpaRepository responsável por realizar as operações de CRUD no banco de dados.
public interface PetRepository extends JpaRepository<Pet, Long> {

}
