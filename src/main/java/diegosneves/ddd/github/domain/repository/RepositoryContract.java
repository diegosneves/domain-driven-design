package diegosneves.ddd.github.domain.repository;

import java.util.List;

public interface RepositoryContract <T> {

    void guardar(T entidade);

    void atualizar(T entidade);

    T buscarPorId(String id);

    List<T> buscarTodos();

}
