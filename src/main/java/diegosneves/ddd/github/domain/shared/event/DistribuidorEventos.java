package diegosneves.ddd.github.domain.shared.event;

import diegosneves.ddd.github.exceptions.EventosException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DistribuidorEventos {

    private static Map<String, List<ManipuladorEventoContrato<? extends Evento>>> processos = new HashMap<>();

    private DistribuidorEventos() {}

    public static <T extends Evento> void notificar(T evento) {
        List<ManipuladorEventoContrato<? extends Evento>> manipuladores = processos.get(evento.getClass().getSimpleName());
        if (nonNull(manipuladores)) {
            for (ManipuladorEventoContrato<? extends Evento> manipulador : manipuladores) {
                try {
                    ((ManipuladorEventoContrato<T>) manipulador).processarEvento(evento);
                } catch (Exception e) {
                    throw new EventosException(evento.getClass().getSimpleName(), e);
                }
            }
        }
    }

    public static void registrar(ManipuladorEventoContrato<? extends Evento> manipuladorEvento) {
        List<ManipuladorEventoContrato<? extends Evento>> manipuladores = processos.get(manipuladorEvento.eventName());

        if (isNull(manipuladores)) {
            manipuladores = new ArrayList<>();
            manipuladores.add(manipuladorEvento);
            processos.put(manipuladorEvento.eventName(), manipuladores);
        } else {
            manipuladores.add(manipuladorEvento);
        }
    }

    public static void remover(ManipuladorEventoContrato<?> manipuladorEvento) {
        List<ManipuladorEventoContrato<? extends Evento>> manipuladores = processos.get(manipuladorEvento.eventName());
        if (nonNull(manipuladores)) {
            manipuladores.remove(manipuladorEvento);
        }
    }

    public static void removerTodos() {
        processos = new HashMap<>();
    }
}
