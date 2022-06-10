package plenum.peru.vacantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plenum.peru.vacantes.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
