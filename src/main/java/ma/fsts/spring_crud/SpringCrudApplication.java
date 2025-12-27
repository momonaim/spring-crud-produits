package ma.fsts.spring_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.fsts.spring_crud.models.Produit;
import ma.fsts.spring_crud.repositories.ProduitRepository;

@SpringBootApplication
public class SpringCrudApplication implements CommandLineRunner {

	@Autowired
	ProduitRepository produitRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Produit p1 = new Produit();
		p1.setDesignation("Ordinateur");
		p1.setPrix(6500);
		p1.setQuantite(5);

		Produit p2 = new Produit();
		p2.setDesignation("Imprimante");
		p2.setPrix(1200);
		p2.setQuantite(8);

		Produit p3 = new Produit("Smartphone", 3200, 15);

		produitRepository.save(p1);
		produitRepository.save(p2);
		produitRepository.save(p3);

		// produitRepository.findAll().stream().forEach(p -> {
		// System.out.println(p.toString());
		// });

		produitRepository.findAll().forEach(System.out::println);
	}

}
