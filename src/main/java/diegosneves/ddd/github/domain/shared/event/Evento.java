package diegosneves.ddd.github.domain.shared.event;

import java.time.LocalDateTime;

public abstract class Evento <T> {

    protected LocalDateTime data;
    protected String objectName;
    protected T dadosDoEvento;

    protected Evento(T dadosDoEvento) {
        this.data = LocalDateTime.now();
        this.dadosDoEvento = dadosDoEvento;
        this.objectName = dadosDoEvento.getClass().getSimpleName();
    }

    public LocalDateTime getData() {
        return data;
    }

    public T getDadosDoEvento() {
        return dadosDoEvento;
    }

    public String getObjectName() {
        return objectName;
    }

}
