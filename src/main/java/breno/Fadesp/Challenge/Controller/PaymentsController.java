package breno.Fadesp.Challenge.Controller;

import breno.Fadesp.Challenge.DTO.FilterDTO;
import breno.Fadesp.Challenge.DTO.PaymentDTO;
import breno.Fadesp.Challenge.DTO.StatusDTO;
import breno.Fadesp.Challenge.PaymentsService.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Operation(
			summary = "Rota de Criação de pagamentos",
			description = "Nessa Rota será enviado um Body para cadastro do pagamento, se caso houver um o campo de cartão preenchido e for outro método de pagamento o sistema irá deixar o valor do cartão como NULL." +
					          "O Valor de pagamento só pode ser maior ou igual a R$1 e ao enviar ele tem de estar no formato double 1.00"
	)
	public ResponseEntity<?> receivePayment (@Valid  @RequestBody PaymentDTO body){
		try{
			return new ResponseEntity<>(paymentService.savePayment(body), HttpStatus.CREATED);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@PutMapping("/atualizarStatus")
	@Operation(
			summary = "Rota de atualização de status de pagamento",
			description = "envio de ID(UUID) do pagamento e o seu novo status"
	)
	public ResponseEntity<?> updateStatusPayment(@Valid  @RequestBody StatusDTO statusPagamento){
		
		try {
			return new ResponseEntity<>(paymentService.updateStatusPayment(statusPagamento),HttpStatus.CREATED);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("")
	@Operation(
			summary ="Rota de Listagem de pagamentos",
			description= "Nessa Rota a operação pode ocorrer usando 3 filtros: CPF ou CNPJ, Código de debito" +
			                         " ou status de processamento. Quando não houver nenhum dos 3 filtros a API não retornará nenhum pagamento e avisará:  " +
			                         "'Nenhum pagamento encontrado!'" )
	public ResponseEntity<?> paymentList(FilterDTO filtro ){
		try {
			return new ResponseEntity<>(paymentService.paymentList(filtro), HttpStatus.OK);
		}catch ( Exception exception ) {
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("")
	@Operation(
			summary = "Rota de exclusão de pagamentos",
					  description = "só poderá ser excluido o pagamento que estiver com status de 'processando'."
					  
	)
	public ResponseEntity<?> deletePayemnt(@RequestBody UUID idPagamento ){
		try {
			
			return paymentService.paymentDelete(idPagamento);
		} catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
