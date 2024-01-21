package diegosneves.ddd.github.mapper.shared;

public interface MapperStrategy<T, E> {

    T mapper(E source);

}
