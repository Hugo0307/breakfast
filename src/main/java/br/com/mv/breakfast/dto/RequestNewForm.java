package br.com.mv.breakfast.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import br.com.mv.breakfast.model.Breakfast;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestNewForm {
	
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@CPF
	private String cpf;
	
	@NotBlank
	private String item;

	public Breakfast toBreakfast() {
		Breakfast breakfast = new Breakfast();
		breakfast.setId(id);
		breakfast.setNome(nome);
		breakfast.setCpf(cpf);
		breakfast.setItem(item);
		
		return breakfast;
	}


}
