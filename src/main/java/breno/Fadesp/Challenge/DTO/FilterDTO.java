package breno.Fadesp.Challenge.DTO;

import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;

import java.util.Optional;

public class FilterDTO {
	private Optional<String> CpfCnpj;
	private String codigoDebito;
	private Optional<EnumPaymentStatus> statusPagamento;
	
	public FilterDTO( Optional<String> CpfCnpj, String codigoDebito, Optional<EnumPaymentStatus> statusPagamento) {
		this.CpfCnpj = CpfCnpj;
		this.codigoDebito = codigoDebito;
		this.statusPagamento = statusPagamento;
	}
	
	// Getters
	
	public Optional<String> getCpfCnpj() {
		return CpfCnpj;
	}
	
	public String getCodigoDebito() {
		return codigoDebito;
	}
	
	public Optional<EnumPaymentStatus> getStatusPagamento() {
		return statusPagamento;
	}
}
