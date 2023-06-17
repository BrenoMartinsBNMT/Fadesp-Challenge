package breno.Fadesp.Challenge.DTO;

import breno.Fadesp.Challenge.Utils.EnumPaymentMethods;
import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.Optional;

public class PaymentDTO {
	@NotNull(message = "o campo não pode ser nulo (código de débito)")
	private String debitCode;
	
	@NotNull(message = "o campo não pode ser nulo (CPF ou CNPJ)")
	@NotBlank(message = "o campo não pode ser vazio (CPF ou CNPJ)")
	@Length(min = 11, max = 12)
	private String CpfCnpj;
	
	@NotNull(message = "o campo não pode ser nulo (Metodos de pagamento)")
	private EnumPaymentMethods paymentMethods;
	
	private Optional<String> cardNumber;
	
	@NotNull(message = "o campo não pode ser nulo (valor do pagamento)")
	@Positive(message = "valor mínimo para pagamento é R$1")
	private Double paymentValue;
	
	private EnumPaymentStatus status;
	
	public PaymentDTO( String debitCode, String CpfCnpj, EnumPaymentMethods paymentMethods,
	                   Optional<String> cardNumber, Double paymentValue, EnumPaymentStatus status) {
		this.debitCode = debitCode;
		this.CpfCnpj = CpfCnpj;
		this.paymentMethods = paymentMethods;
		this.cardNumber = cardNumber;
		this.paymentValue = paymentValue;
		this.status = status;
	}
	
	public String getDebitCode() {
		return debitCode;
	}
	
	public void setDebitCode( String debitCode ) {
		this.debitCode = debitCode;
	}
	
	public String getCpfCnpj() {
		return CpfCnpj;
	}
	
	public void setCpfCnpj( String cpfCnpj ) {
		CpfCnpj = cpfCnpj;
	}
	
	public EnumPaymentMethods getPaymentMethods() {
		return paymentMethods;
	}
	
	public void setPaymentMethods( EnumPaymentMethods paymentMethods ) {
		this.paymentMethods = paymentMethods;
	}
	
	public Optional<String> getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber( Optional<String> cardNumber ) {
		this.cardNumber = cardNumber;
	}
	
	public Double getPaymentValue() {
		return paymentValue;
	}
	
	public void setPaymentValue( Double paymentValue ) {
		this.paymentValue = paymentValue;
	}
	
	public EnumPaymentStatus getStatus() {
		return status;
	}
	
	public void setStatus( EnumPaymentStatus status ) {
		this.status = status;
	}
}
