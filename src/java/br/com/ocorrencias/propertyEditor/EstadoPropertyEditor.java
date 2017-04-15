package br.com.ocorrencias.propertyEditor;

import java.beans.PropertyEditorSupport;

import br.com.ocorrencias.bean.Estado;

public class EstadoPropertyEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) {
		Estado estado = new Estado();
		estado.setId(Integer.parseInt(text));
		setValue(estado);
	}
}
