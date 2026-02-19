package com.tde.motorDBInelibus.persistence.destino.repository;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tde.motorDBInelibus.persistence.destino.entity.DescargasAVLCardD;
import com.tde.motorDBInelibus.persistence.destino.entity.DescargasMiniSigoD;


@Repository
public interface DescargasAVLCardRepoD extends CrudRepository<DescargasAVLCardD,Long>{
	
	Optional<DescargasAVLCardD> findById(Long id);
	
	Optional<DescargasAVLCardD> findTopByOrderByIdDesc();

}
