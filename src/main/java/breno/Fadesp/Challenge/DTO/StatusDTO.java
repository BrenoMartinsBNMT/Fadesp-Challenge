package breno.Fadesp.Challenge.DTO;

import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class StatusDTO {
	@NotNull(message = "o campo não pode ser nulo")
	private UUID id;
	
	@NotNull(message = "o campo não pode ser nulo")
	private EnumPaymentStatus statusPagamento;
	
	public StatusDTO(UUID id, EnumPaymentStatus statusPagamento) {
		this.id = id;
		this.statusPagamento = statusPagamento;
	}
	
	// Getters
	
	public UUID getId() {
		return id;
	}
	
	public EnumPaymentStatus getStatusPagamento() {
		return statusPagamento;
	}
}
