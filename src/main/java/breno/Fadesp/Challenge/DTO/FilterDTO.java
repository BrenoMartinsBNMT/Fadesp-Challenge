package breno.Fadesp.Challenge.DTO;

import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;

import java.util.Optional;

public class FilterDTO {
	private Optional<String> CpfCnpj;
	private Optional<String> debitCode;
	private Optional<EnumPaymentStatus> paymentStatus;
	
	public FilterDTO( Optional<String> CpfCnpj, Optional<String> debitCode, Optional<EnumPaymentStatus> paymentStatus) {
		this.CpfCnpj = CpfCnpj;
		this.debitCode = debitCode;
		this.paymentStatus = paymentStatus;
	}
	
	// Getters
	
	public Optional<String> getCpfCnpj() {
		return CpfCnpj;
	}
	
	public Optional<String> getCodigoDebito() {
		return debitCode;
	}
	
	public Optional<EnumPaymentStatus> getStatusPagamento() {
		return paymentStatus;
	}
}
