package Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import Entite.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	
}

