package breno.Fadesp.Challenge.Controller;

import breno.Fadesp.Challenge.DTO.FilterDTO;
import breno.Fadesp.Challenge.DTO.PaymentDTO;
import breno.Fadesp.Challenge.DTO.StatusDTO;
import breno.Fadesp.Challenge.PaymentsService.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PaymentsController {
	@Autowired
	private PaymentService paymentService;
	@PostMapping("")
	public ResponseEntity<?> receberPagamento  (@Valid  @RequestBody PaymentDTO body){
		
		try{
			return new ResponseEntity<>(paymentService.salvarPagamentos(body), HttpStatus.CREATED);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@PutMapping("/atualizarStatus")
	public ResponseEntity<?> atualizarStatusPagamento(@Valid  @RequestBody StatusDTO statusPagamento){
		
		try {
			return new ResponseEntity<>(paymentService.atualizarStatusPagamento(statusPagamento),HttpStatus.CREATED);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("")
	public ResponseEntity<?> listarPagamentos(FilterDTO filtro ){
		try {
			return new ResponseEntity<>(paymentService.listarPagamentos(filtro), HttpStatus.OK);
		}catch ( Exception exception ) {
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("")
	public ResponseEntity<?> excluirPagamentos(@Param("idPagamento") String idPagamento ){
		try {
			UUID id = UUID.fromString(idPagamento);
			return paymentService.exclusaoPagamento(id);
		} catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
