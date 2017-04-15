package br.com.ocorrencias.propertyEditor;

import java.beans.PropertyEditorSupport;

import br.com.ocorrencias.bean.Cidade;

public class CidadePropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		Cidade cidade = new Cidade();
		cidade.setId(Integer.parseInt(text));
		setValue(cidade);
	}
}
