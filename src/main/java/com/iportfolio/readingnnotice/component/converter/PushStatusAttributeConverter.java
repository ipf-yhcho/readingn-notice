package com.iportfolio.readingnnotice.component.converter;

import com.iportfolio.readingnnotice.domain.consts.PushStatus;
import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PushStatusAttributeConverter implements AttributeConverter<PushStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final PushStatus status) {
        if (status == null) {
            return null;
        }

        return status.getCode();
    }

    @Override
    public PushStatus convertToEntityAttribute(final Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(PushStatus.values())
            .filter(status -> status.compareCode(code))
            .findFirst()
            .orElse(null);
    }
}
