package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

// Criado o repositório de Pet que extende o JpaRepository responsável por realizar as operações de CRUD no banco de dados.
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByAbrigo(Abrigo abrigo); // O método findByAbrigo é responsável por buscar os pets de um abrigo.

    List<Pet> findAllByAdotadoFalse(); // O método findAllByAdotadoFalse é responsável por buscar todos os pets não adotados.
}
