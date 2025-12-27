package ma.fsts.spring_crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fsts.spring_crud.models.Produit;
import ma.fsts.spring_crud.repositories.ProduitRepository;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    public Produit findById(Integer id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit avec ID " + id + " non trouvé"));
    }

    public Produit save(Produit p) {
        return produitRepository.save(p);
    }

    public void update(Produit p) {
        final Produit existingProduit = findById(p.getId());
        existingProduit.setDesignation(p.getDesignation());
        existingProduit.setPrix(p.getPrix());
        existingProduit.setQuantite(p.getQuantite());

        produitRepository.save(existingProduit);
    }

    public void delete(Integer id) {
        produitRepository.deleteById(id);
    }

}
