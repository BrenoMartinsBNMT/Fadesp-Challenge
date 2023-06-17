package breno.Fadesp.Challenge.Repository;

import breno.Fadesp.Challenge.Model.Payments;
import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, UUID> {
	List<Payments> findByDebitCode(String debitCode);
	List<Payments> findAllByStatus( EnumPaymentStatus paymentStatus );
	List<Payments> findAllByCpfCnpj(String cpfCnpj);
	
	@Transactional
	@Modifying
	@Query("update Payments set status = ?1 where id= ?2")
	int updateStatus ( EnumPaymentStatus paymentStatus, UUID id);
}
