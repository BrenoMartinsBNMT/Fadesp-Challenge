package breno.Fadesp.Challenge.DTO;

import breno.Fadesp.Challenge.Utils.EnumPaymentMethods;
import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.Optional;

public class PaymentDTO {
	@NotNull(message = "o campo não pode ser nulo (código de débito)")
	@Min(value = 1, message = "valor não pode ser inferior a 1")
	private int codigoDebito;
	
	@NotNull(message = "o campo não pode ser nulo (CPF ou CNPJ)")
	@NotBlank(message = "o campo não pode ser vazio (CPF ou CNPJ)")
	@Length(min = 11, max = 12)
	private String CpfCnpj;
	
	@NotNull(message = "o campo não pode ser nulo (Metodos de pagamento)")
	private EnumPaymentMethods paymentMethods;
	
	@Pattern(regexp = "[0-9]*")
	@Size(max = 19)
	private Optional<String> numeroCartao;
	
	@NotNull(message = "o campo não pode ser nulo (valor do pagamento)")
	@Positive(message = "valor mínimo para pagamento é R$1")
	private Double valorPagamento;
	
	private EnumPaymentStatus status;
	
	public PaymentDTO( int codigoDebito, String CpfCnpj, EnumPaymentMethods metodosDePagamentos,
	                   Optional<String> numeroCartao, Double valorPagamento, EnumPaymentStatus status) {
		this.codigoDebito = codigoDebito;
		this.CpfCnpj = CpfCnpj;
		this.paymentMethods = metodosDePagamentos;
		this.numeroCartao = numeroCartao;
		this.valorPagamento = valorPagamento;
		this.status = status;
	}
	
	// Getters and Setters
	
	public int getCodigoDebito() {
		return codigoDebito;
	}
	
	public void setCodigoDebito(int codigoDebito) {
		this.codigoDebito = codigoDebito;
	}
	
	public String getCpfCnpj() {
		return CpfCnpj;
	}
	
	public void setCpfCnpj(String CpfCnpj) {
		this.CpfCnpj = CpfCnpj;
	}
	
	public EnumPaymentMethods getMetodosDePagamentos() {
		return paymentMethods;
	}
	
	public void setMetodosDePagamentos(EnumPaymentMethods metodosDePagamentos) {
		this.paymentMethods = metodosDePagamentos;
	}
	
	public Optional<String> getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(Optional<String> numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public Double getValorPagamento() {
		return valorPagamento;
	}
	
	public void setValorPagamento(Double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	public EnumPaymentStatus getStatus() {
		return status;
	}
	
	public void setStatus(EnumPaymentStatus status) {
		this.status = status;
	}
}
