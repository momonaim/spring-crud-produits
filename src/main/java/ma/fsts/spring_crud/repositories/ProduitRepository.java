package ma.fsts.spring_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.spring_crud.models.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

}
