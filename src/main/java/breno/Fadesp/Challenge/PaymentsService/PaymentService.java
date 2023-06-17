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
	
	public ResponseEntity<?> salvarPagamentos( PaymentDTO pagamento){
		Payments responseId;
		Payments teste = new Payments();
		
		if(pagamento.getNumeroCartao().isEmpty()){
			teste.setStatus(EnumPaymentStatus.processando);
			
			if ( pagamento.getMetodosDePagamentos().equals(EnumPaymentMethods.boleto) ||
					     pagamento.getMetodosDePagamentos().equals(EnumPaymentMethods.pix) ){
				responseId = paymentsRepository.save(teste);
				return new ResponseEntity<>(responseId.getId(), HttpStatus.CREATED);
			}
			
			
		}
		if(pagamento.getNumeroCartao().isPresent()) {
			if ( pagamento.getMetodosDePagamentos().equals(EnumPaymentMethods.cartao_credito) ||
					     pagamento.getMetodosDePagamentos().equals(EnumPaymentMethods.cartao_debito) ) {
				
				responseId = paymentsRepository.save(new Payments());
				return new ResponseEntity<>(responseId.getId(), HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("Não foi possivel criar um pagamento", HttpStatus.FORBIDDEN);
	}
	
	public ResponseEntity<?> atualizarStatusPagamento( StatusDTO statusPagamento ) {
		UUID id = statusPagamento.getId();
		EnumPaymentStatus status = statusPagamento.getStatusPagamento();
		try{
			Optional<Payments> verificarStatus = paymentsRepository.findById(id);
			
			if(verificarStatus.get().getStatus().equals(status)){
				return new ResponseEntity<>("não podemos concluir essa operação!",HttpStatus.FORBIDDEN);
			}
			if ( verificarStatus.get().getStatus().equals(EnumPaymentStatus.sucesso)){
				return new ResponseEntity<>("Não podemos alterar o status dessa operação!",HttpStatus.FORBIDDEN);
			}
			if ( verificarStatus.get().getStatus().equals(EnumPaymentStatus.falha)) {
				if(!status.equals(EnumPaymentStatus.processando)){
					return new ResponseEntity<>("Não podemos realizar essa operação!",HttpStatus.FORBIDDEN);
				}
			}
			
			return new ResponseEntity<>(paymentsRepository.updateStatus(status,id), HttpStatus.OK);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	public ResponseEntity<?> listarPagamentos ( FilterDTO filtro){
		
		try{
			if( filtro.getCodigoDebito() != null){
				List<Payments> response = paymentsRepository.findByDebitCode(Integer.parseInt(filtro.getCodigoDebito().trim()));
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			
			if ( filtro.getCpfCnpj().isPresent() ){
				List<Payments> response =  paymentsRepository.findAllByCpfCnpj(filtro.getCpfCnpj().get());
				
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			if(filtro.getStatusPagamento().isPresent()){
				List<Payments> response = paymentsRepository.findAllByStatus(filtro.getStatusPagamento().get());
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			return new ResponseEntity<>(paymentsRepository.findAll(),HttpStatus.OK);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.OK);
		}
		
	}
	
	public ResponseEntity<?> exclusaoPagamento(UUID idPagamento){
		try{
			
			Optional<Payments> processandoPagamento = paymentsRepository.findById(idPagamento);
			if(processandoPagamento.get().getStatus() == EnumPaymentStatus.processando){
				paymentsRepository.deleteById(idPagamento);
				return new ResponseEntity<>("Ação concluida com sucesso!",HttpStatus.OK);
			}
			return new ResponseEntity<>("Não foi possivel concluir essa operação",HttpStatus.OK);
			
		} catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
}
