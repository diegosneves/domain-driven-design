package diegosneves.ddd.github.mapper;

public interface MapperStrategy<T, E> {

    T mapper(E source);

}
