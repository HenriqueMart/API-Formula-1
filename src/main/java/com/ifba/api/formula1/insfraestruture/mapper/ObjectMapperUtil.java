package com.ifba.api.formula1.usuario.mapper;

import com.ifba.api.formula1.usuario.dto.UsuarioGetResponseDto;
import com.ifba.api.formula1.usuario.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObjectMapperUtil {

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
    }

    public <Input, Output> Output map(final Input object, final Class<Output> clazz) {

        MODEL_MAPPER.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STRICT).setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        Output c = MODEL_MAPPER.map(object, clazz);

        return c;
    }

}
