package com.iportfolio.readingnnotice.component.converter;

import com.iportfolio.readingnnotice.domain.consts.Activate;
import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ActivateAttributeConverter implements AttributeConverter<Activate, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Activate activate) {
        if (activate == null) {
            return null;
        }

        return activate.getCode();
    }

    @Override
    public Activate convertToEntityAttribute(final Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(Activate.values())
            .filter(activate -> activate.compareCode(code))
            .findFirst()
            .orElse(null);
    }
}
