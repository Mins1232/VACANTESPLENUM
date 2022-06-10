package plenum.peru.vacantes.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import plenum.peru.vacantes.model.Vacante;
import plenum.peru.vacantes.service.ICategoriasService;
import plenum.peru.vacantes.service.IVacantesService;
import plenum.peru.vacantes.util.Utileria;

@Controller
@RequestMapping(value="/vacantes")
public class VacantesController {
	
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	
    @Autowired
	private IVacantesService serviceVacantes;
    
    @Autowired
   	private ICategoriasService serviceCategorias;
	  
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<Vacante> lista = serviceVacantes.buscarTodas();
    	model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
    
   
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Vacante> lista = serviceVacantes.buscarTodas(page);
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
    
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Vacante vacante) {		
		return "vacantes/formVacante";
	}
	
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Vacante vacante, BindingResult result, Model model,
			@RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes attributes ) {	
		
		if (result.hasErrors()){
			
			System.out.println("Existieron errores");
			return "vacantes/formVacante";
		}	
		
		if (!multiPart.isEmpty()) {

			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen!=null){		
				vacante.setImagen(nombreImagen); 
			}	
		}
				
		
		serviceVacantes.guardar(vacante);
		attributes.addFlashAttribute("msg", "Los datos de la vacante fueron guardados!");
			
		
		return "redirect:/vacantes/indexPaginate";		
	}
	
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {		
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);			
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVacante, Model model) {		
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);			
		model.addAttribute("vacante", vacante);
		return "vacantes/formVacante";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes) {
		
		
		serviceVacantes.eliminar(idVacante);
			
		attributes.addFlashAttribute("msg", "La vacante fue eliminada!.");
		
		return "redirect:/vacantes/indexPaginate";
	}
	
	
	@ModelAttribute
	public void setGenericos(Model model){
		model.addAttribute("categorias", serviceCategorias.buscarTodas());	
	}
	
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
