package plenum.peru.vacantes.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import plenum.peru.vacantes.model.Vacante;
import plenum.peru.vacantes.repository.VacantesRepository;
import plenum.peru.vacantes.service.IVacantesService;

@Service
@Primary
public class VacantesServiceImpl implements IVacantesService {

	@Autowired
	private VacantesRepository repoVacantes;
	
	@Override
	public void guardar(Vacante vacante) {
		repoVacantes.save(vacante);

	}

	@Override
	public void eliminar(Integer idVacante) {
		repoVacantes.deleteById(idVacante);
	}

	@Override
	public List<Vacante> buscarTodas() {
		
		return repoVacantes.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		
		Optional<Vacante> optional = repoVacantes.findById(idVacante);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		
		return repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		return repoVacantes.findAll(page);
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		return repoVacantes.findAll(example);
	}

}
