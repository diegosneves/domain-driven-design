package diegosneves.ddd.github.domain.event.contract;

public interface ManipuladorEventoContrato <T extends Evento> {

    String eventName();
    void processarEvento(T evento);

}
