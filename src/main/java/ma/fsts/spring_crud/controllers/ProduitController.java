package ma.fsts.spring_crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.fsts.spring_crud.models.Produit;
import ma.fsts.spring_crud.services.ProduitService;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Liste de tous les produits
    @GetMapping({ "", "/" })
    public String listProduits(Model model) {
        model.addAttribute("produits", produitService.findAll());
        return "produits";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Produit> ResponseEntity() {
        return produitService.findAll();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Produit>> getAllProduits() {
        return ResponseEntity.ok(produitService.findAll());
    }

    // Get single produit as JSON (for AJAX/modal)
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Produit> getProduitJson(@PathVariable("id") Integer id) {
        Produit p = produitService.findById(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    // Formulaire pour créer un nouveau produit
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("produit", new Produit());
        return "produit_form"; // src/main/resources/templates/produit_form.html
    }

    // Enregistrer un produit (create ou update)
    @PostMapping("/save")
    public String saveProduit(@ModelAttribute("produit") Produit produit, Model model) {

        // si id == 0 -> nouveau, sinon update
        if (produit.getId() == 0) {
            produitService.save(produit);
        } else {
            produitService.update(produit);
        }

        return "redirect:/produits";
    }

    // Formulaire pour éditer
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Produit p = produitService.findById(id);
        model.addAttribute("produit", p);
        return "produit_form";
    }

    // Supprimer
    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable("id") Integer id) {
        produitService.delete(id);
        return "redirect:/produits";
    }

    // Détail
    @GetMapping("/view/{id}")
    public String viewProduit(@PathVariable("id") Integer id, Model model) {
        Produit p = produitService.findById(id);
        model.addAttribute("produit", p);
        return "produit_view";
    }

}
