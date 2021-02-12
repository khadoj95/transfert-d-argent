package Repository;
import Entite.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
public interface AdminRepository extends CrudRepository<Admin, Long> {
	 
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public Admin getUserByUsername(@Param("username") String username);
}


