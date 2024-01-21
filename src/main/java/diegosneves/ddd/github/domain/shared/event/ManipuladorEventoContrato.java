package diegosneves.ddd.github.domain.shared.event;

public interface ManipuladorEventoContrato <T extends Evento> {

    String eventName();
    void processarEvento(T evento);

}
