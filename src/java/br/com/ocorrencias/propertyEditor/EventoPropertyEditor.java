package br.com.ocorrencias.propertyEditor;

import java.beans.PropertyEditorSupport;

import br.com.ocorrencias.bean.Evento;

public class EventoPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		Evento evento = new Evento();
		evento.setId(Integer.parseInt(text));
		setValue(evento);
	}
}
