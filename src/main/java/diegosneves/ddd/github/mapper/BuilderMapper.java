package diegosneves.ddd.github.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static java.util.Objects.isNull;

public interface BuilderMapper {

    static <T> T mapper(Class<T> destinationClass, Object sourceClass) {
        var destinationFields = destinationClass.getDeclaredFields();

        T mappedInstance = null;

        try {
            mappedInstance = destinationClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Field destinationField : destinationFields) {
            destinationField.setAccessible(true);
            try {
                Field sourceField = sourceClass.getClass().getDeclaredField(destinationField.getName());
                sourceField.setAccessible(true);
                destinationField.set(mappedInstance, sourceField.get(sourceClass));
            } catch (Exception ignored) {
            }
        }
        return mappedInstance;
    }

    static <T, E> T mapper(Class<T> destinationClass, E sourceClass, MapperStrategy<T, E> strategy) {
        if (isNull(strategy)) {
            return BuilderMapper.mapper(destinationClass, sourceClass);
        }
        return strategy.mapper(sourceClass);
    }

}
