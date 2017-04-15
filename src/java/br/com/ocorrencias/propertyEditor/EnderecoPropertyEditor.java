package br.com.ocorrencias.propertyEditor;

import java.beans.PropertyEditorSupport;

import br.com.ocorrencias.bean.Endereco;


public class EnderecoPropertyEditor  extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) {
		Endereco endereco = new Endereco();
		setValue(endereco);
	}
}
