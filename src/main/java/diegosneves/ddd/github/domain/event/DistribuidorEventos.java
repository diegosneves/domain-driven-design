package diegosneves.ddd.github.domain.event;

import diegosneves.ddd.github.domain.event.contract.Evento;
import diegosneves.ddd.github.domain.event.contract.ManipuladorEventoContrato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DistribuidorEventos {

    private static Map<String, List<ManipuladorEventoContrato<?>>> processos = new HashMap<>();

    private DistribuidorEventos() {}

    public static void notificar(Evento evento) {
        List<ManipuladorEventoContrato<?>> manipuladores = processos.get(evento.getClass().getSimpleName());
        if (nonNull(manipuladores)) {
            for (ManipuladorEventoContrato<?> manipulador : manipuladores) {
                manipulador.processarEvento(evento);
            }
        }
    }

    public static void registrar(ManipuladorEventoContrato<?> manipuladorEvento) {
        List<ManipuladorEventoContrato<?>> manipuladores = processos.get(manipuladorEvento.eventName());

        if (isNull(manipuladores)) {
            manipuladores = new ArrayList<>();
            manipuladores.add(manipuladorEvento);
            processos.put(manipuladorEvento.eventName(), manipuladores);
        } else {
            manipuladores.add(manipuladorEvento);
        }
    }

    public static void remover(ManipuladorEventoContrato<?> manipuladorEvento) {
        List<ManipuladorEventoContrato<?>> manipuladores = processos.get(manipuladorEvento.eventName());
        if (nonNull(manipuladores)) {
            manipuladores.remove(manipuladorEvento);
        }
    }

    public static void removerTodos() {
        processos = new HashMap<>();
    }
}
