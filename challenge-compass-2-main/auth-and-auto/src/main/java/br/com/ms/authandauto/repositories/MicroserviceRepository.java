package br.com.ms.authandauto.repositories;
import br.com.ms.authandauto.entities.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MicroserviceRepository extends JpaRepository<Microservice, Long> {}