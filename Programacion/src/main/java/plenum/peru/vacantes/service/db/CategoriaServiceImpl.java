package plenum.peru.vacantes.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import plenum.peru.vacantes.model.Categoria;
import plenum.peru.vacantes.repository.CategoriasRepository;
import plenum.peru.vacantes.service.ICategoriasService;

@Service
@Primary
public class CategoriaServiceImpl implements ICategoriasService {
	
	@Autowired
	private CategoriasRepository  repoCategorias;

	@Override
	public void guardar(Categoria categoria) {
		repoCategorias.save(categoria);

	}

	@Override
	public void eliminar(Integer idCategoria) {
		repoCategorias.deleteById(idCategoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		
		return repoCategorias.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		
		return repoCategorias.findAll(page);
	}

}
