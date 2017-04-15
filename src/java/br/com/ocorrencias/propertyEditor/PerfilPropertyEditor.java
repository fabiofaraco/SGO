package br.com.ocorrencias.propertyEditor;

import java.beans.PropertyEditorSupport;

import br.com.ocorrencias.bean.Perfil;

public class PerfilPropertyEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) {
		Perfil perfil = new Perfil();
		perfil.setId(Integer.parseInt(text));
		setValue(perfil);
	}
}
