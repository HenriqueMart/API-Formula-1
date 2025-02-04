package com.ifba.api.formula1.insfraestruture.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // Marca a classe como um componente gerenciado pelo Spring
public class ObjectMapperUtil {

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true) // Ignora ambiguidades no mapeamento
                .setMatchingStrategy(MatchingStrategies.STRICT) // Usa estratégia estrita para mapeamento
                .setFieldMatchingEnabled(true) // Habilita correspondência de campos automaticamente
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE); // Permite acessar campos privados
    }

    // Método genérico para mapear um objeto de um tipo para outro
    public static <D, T> D map(final T source, final Class<D> destinationType) {
        return MODEL_MAPPER.map(source, destinationType);
    }

    // Método genérico para mapear uma lista de objetos de um tipo para outro
    public static <D, T> List<D> mapList(final List<T> sourceList, final Class<D> destinationType) {
        return sourceList.stream()
                .map(entity -> MODEL_MAPPER.map(entity, destinationType))
                .collect(Collectors.toList());
    }
}
