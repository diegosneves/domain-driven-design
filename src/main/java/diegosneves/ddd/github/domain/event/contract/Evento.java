package diegosneves.ddd.github.domain.event.contract;

import java.time.LocalDateTime;

public abstract class Evento {

    protected LocalDateTime data;
    protected Object dadosDoEvento;

    protected Evento(Object dadosDoEvento) {
        this.data = LocalDateTime.now();
        this.dadosDoEvento = dadosDoEvento;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Object getDadosDoEvento() {
        return dadosDoEvento;
    }
}
