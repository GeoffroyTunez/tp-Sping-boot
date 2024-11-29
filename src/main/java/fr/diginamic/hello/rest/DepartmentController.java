package fr.diginamic.hello.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.nio.file.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.exceptionHandler.FunctionalException;
import fr.diginamic.hello.model.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.model.Department;
import fr.diginamic.hello.service.DepartmentService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	private void validateDepartment(Department department) throws FunctionalException {
		if(department.getName().length() > 3){
			if(department.getCode().length() < 2 && department.getCode().length() > 3){
				if(departmentService.findById(department.getId()).isPresent()){

				}else{
					throw new FunctionalException("Département avec cette id existe déjà");
				}
			}else{
				throw new FunctionalException("Code de département trop long");
			}
		}else{
			throw new FunctionalException("Nom de département trop court");
		}
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody Department department) throws FunctionalException {
		validateDepartment(department);
		if(departmentService.create(department)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Impossible de créer le département envoyé : "+department.toString(),HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping
	public List<Department> findAll(){
		return departmentService.findAll();
	}
	@GetMapping("/{code}")
	public Department findByCode(@PathVariable String code){
		return departmentService.findByCode(code);
	}


	@GetMapping("/{code}/pdf")
	public ResponseEntity<String> findByCodeToPdf(@PathVariable String code) {
		// Récupération du département en interne (par code)
		Department department = departmentService.findByCode(code);

		if (department == null) {
			return new ResponseEntity<>("Département non trouvé", HttpStatus.NOT_FOUND);
		}

		// Appel à l'API pour récupérer les informations externes (nom du département)

		try {
			RestTemplate restTemplate = new RestTemplate(); // Instanciation directe
			String apiUrl = "https://geo.api.gouv.fr/departements/" + code + "?fields=nom,code,codeRegion";
			ResponseEntity<DepartementDto> response = restTemplate.getForEntity(apiUrl, DepartementDto.class);
			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				DepartementDto departementDto = response.getBody();
				// Création du chemin du fichier PDF
				String dest = "src/main/resources/pdf/" + departementDto.getNom() + ".pdf";
				File file = new File(dest);
				File parentDir = file.getParentFile();

				if (!parentDir.exists()) {
					parentDir.mkdirs(); // Crée les répertoires nécessaires
				}

				// Création du document PDF
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(dest));

				document.open();
				// Ajout d'informations au PDF
				document.add(new Paragraph("Nom du département (API) : " + departementDto.getNom()));
				document.add(new Paragraph("Code Département : " + department.getCode()));
				document.add(new Paragraph("Liste des villes :"));
				for (Town ville : department.getTowns()) {
					document.add(new Paragraph("    Nom : " + ville.getName() + " | Population : " + ville.getNbInhabitants()));
				}

				document.close();

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erreur lors de la création du fichier PDF", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erreur inconnue", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("PDF généré avec succès pour le département " + department.getName(), HttpStatus.OK);
	}




	@PutMapping
	public ResponseEntity<String> update(@RequestBody Department department){
		if (!departmentService.update(department)) {
			return new ResponseEntity<String>("Impossible de mettre à jour le département envoyé, il n'est pas trouvé ou n'a pas d'id : "+department.toString(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Mise à jour réussie !",HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		if(departmentService.delete(id)) {
			return new ResponseEntity<String>("Suppression réussie !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Impossible de supprimer le département avec l'id envoyé : "+id,HttpStatus.BAD_REQUEST);
		}
	}
}
