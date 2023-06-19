package breno.Fadesp.Challenge.PaymentsService;

import breno.Fadesp.Challenge.DTO.FilterDTO;
import breno.Fadesp.Challenge.DTO.PaymentDTO;
import breno.Fadesp.Challenge.DTO.StatusDTO;
import breno.Fadesp.Challenge.Model.Payments;
import breno.Fadesp.Challenge.Repository.PaymentsRepository;
import breno.Fadesp.Challenge.Utils.EnumPaymentMethods;
import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
	@Autowired
	private PaymentsRepository paymentsRepository;
	
	public ResponseEntity<?> savePayment( PaymentDTO payment){
		
		try{
			Payments responseId;
			Payments paymentBody = new Payments(payment);
				if ( payment.getPaymentMethods().equals(EnumPaymentMethods.boleto) ||
						     payment.getPaymentMethods().equals(EnumPaymentMethods.pix) ) {
					paymentBody.setStatus(EnumPaymentStatus.processando);
					paymentBody.setCardNumber(null);
					responseId = paymentsRepository.save(paymentBody);
					return new ResponseEntity<>("id: " + responseId.getId(), HttpStatus.CREATED);
				}
			
			paymentBody.setStatus(EnumPaymentStatus.processando);
			responseId = paymentsRepository.save(paymentBody);
			
			return new ResponseEntity<>("id: " + responseId.getId(), HttpStatus.CREATED);
		}catch ( Exception exception ){
			return new ResponseEntity<>("Não foi possivel criar um pagamento"   , HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	public ResponseEntity<?> updateStatusPayment( StatusDTO paymentStatus ) {
		UUID id = paymentStatus.getId();
		EnumPaymentStatus status = paymentStatus.getStatusPagamento();
		
		try{
			Optional<Payments> verifyStatus = paymentsRepository.findById(id);
			if(verifyStatus.get().getStatus().equals(status)){
				return new ResponseEntity<>("não podemos concluir essa operação!",HttpStatus.FORBIDDEN);
			}
			if ( verifyStatus.get().getStatus().equals(EnumPaymentStatus.sucesso)){
				return new ResponseEntity<>("Não podemos alterar o status dessa operação!",HttpStatus.FORBIDDEN);
			}
			if ( verifyStatus.get().getStatus().equals(EnumPaymentStatus.falha)) {
				if(status.equals(EnumPaymentStatus.sucesso)){
					return new ResponseEntity<>("Não podemos realizar essa operação!",HttpStatus.FORBIDDEN);
				}
			}
			
			return new ResponseEntity<>(paymentsRepository.updateStatus(status,id), HttpStatus.OK);
		}catch ( Exception exception ){
			
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	public ResponseEntity<?> paymentList ( FilterDTO filter){
		
		try{
			if( filter.getCodigoDebito().isPresent()){
				List<Payments> response = paymentsRepository.findByDebitCode(filter.getCodigoDebito().get());
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			
			if ( filter.getCpfCnpj().isPresent() ){
				List<Payments> response =  paymentsRepository.findAllByCpfCnpj(filter.getCpfCnpj().get());
				
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			if(filter.getStatusPagamento().isPresent()){
				List<Payments> response = paymentsRepository.findAllByStatus(filter.getStatusPagamento().get());
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			
				return new ResponseEntity<>("Nemhum pagamento encontrado!",HttpStatus.NOT_FOUND);
			
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	public ResponseEntity<?> paymentDelete(UUID paymentId){
		try{
			
			Optional<Payments> responsePaymentStatus = paymentsRepository.findById(paymentId);
			if(responsePaymentStatus.get().getStatus() == EnumPaymentStatus.processando){
				paymentsRepository.deleteById(paymentId);
				return new ResponseEntity<>("Ação concluida com sucesso!",HttpStatus.OK);
			}
			return new ResponseEntity<>("Não foi possivel concluir essa operação",HttpStatus.OK);
			
		} catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
}
