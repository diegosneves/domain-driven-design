package diegosneves.ddd.github.domain.shared.repository;

import java.util.List;

public interface RepositoryContract <T> {

    void guardar(T entidade);

    void atualizar(T entidade);

    void deletar(T entidade);

    T buscarPorId(String id);

    List<T> buscarTodos();

}
