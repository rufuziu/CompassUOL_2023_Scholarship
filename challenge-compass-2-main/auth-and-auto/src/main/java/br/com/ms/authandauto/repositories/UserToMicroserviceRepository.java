package br.com.ms.authandauto.repositories;

import br.com.ms.authandauto.entities.UserToMicroservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserToMicroserviceRepository extends JpaRepository<UserToMicroservice, Long> {
  Boolean existsByUserIdAndMicroserviceId(
          Long idUser,
          Long idMicroservice);

  Optional<UserToMicroservice> findByUserIdAndMicroserviceId
          (Long idUser,
           Long idMicroservice);
}
