package XMLParseCarDealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import XMLParseCarDealer.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

	@Query(value = "select s.id, s.name, p.quantity \n" +
				   "from suppliers as s \n" +
				   "join parts as p \n" +
				   "on s.id = p.supplier_id \n" + 
				   "where s.is_importer = 1 \n" +
				   "group by s.name;", nativeQuery = true)
	public List<Object[]> getLocalSuppliersAndPartCount();
}
