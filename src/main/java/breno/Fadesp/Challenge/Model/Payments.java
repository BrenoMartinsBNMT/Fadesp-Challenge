package breno.Fadesp.Challenge.Model;

import breno.Fadesp.Challenge.Utils.EnumPaymentMethods;
import breno.Fadesp.Challenge.Utils.EnumPaymentStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pagamentos")
public class Payments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	@Column(name = "Codigo_debito",nullable = false,updatable = false)
	int debitCode;
	@Column(name = "cpf_cnpj",nullable = false,updatable = false,length = 18)
	String cpfCnpj;
	@Enumerated(EnumType.STRING)
	EnumPaymentMethods paymentMethods;
	@Column(name = "numero_cartao",updatable = false,length = 16)
	String cardNumber;
	@Column(name = "valor_pagamento",nullable = false,updatable = false)
	Double paymentValue;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	EnumPaymentStatus status ;
	
	public UUID getId() {
		return id;
	}
	
	public void setId( UUID id ) {
		this.id = id;
	}
	
	public int getDebitCode() {
		return debitCode;
	}
	
	public void setDebitCode( int debitCode ) {
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
