package breno.Fadesp.Challenge.Model;


import breno.Fadesp.Challenge.DTO.PaymentDTO;
import breno.Fadesp.Challenge.Utils.EnumPaymentMethods;
import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.UUID;

@Entity
@Table(name = "pagamentos")
public class Payments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	@Column(name = "Codigo_debito",nullable = false,updatable = false)
	String debitCode;
	@Column(name = "cpf_cnpj",nullable = false,updatable = false,length = 18)
	String cpfCnpj;
	@Enumerated(EnumType.STRING)
	EnumPaymentMethods paymentMethods;
	@Column(name = "numero_cartao",updatable = false,length = 16)
	@CreditCardNumber
	String cardNumber = null;
	@Column(name = "valor_pagamento",nullable = false,updatable = false)
	Double paymentValue;
	@Column(name = "status",nullable = false)
	@Enumerated(EnumType.STRING)
	EnumPaymentStatus status ;
	
	public Payments( PaymentDTO paymentDTO ) {
		this.debitCode = paymentDTO.getDebitCode();
		this.cpfCnpj = paymentDTO.getCpfCnpj();
		this.paymentMethods = paymentDTO.getPaymentMethods();
		this.cardNumber = paymentDTO.getCardNumber().orElse("");
		this.paymentValue = paymentDTO.getPaymentValue();
	}
	
	public Payments() {
	
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId( UUID id ) {
		this.id = id;
	}
	
	public String getDebitCode() {
		return debitCode;
	}
	
	public void setDebitCode( String debitCode ) {
		this.debitCode = debitCode;
	}
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	
	public void setCpfCnpj( String cpfCnpj ) {
		this.cpfCnpj = cpfCnpj;
	}
	
	public EnumPaymentMethods getPaymentMethods() {
		return paymentMethods;
	}
	
	public void setPaymentMethods( EnumPaymentMethods paymentMethods ) {
		this.paymentMethods = paymentMethods;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber( String cardNumber ) {
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
