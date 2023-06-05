package com.ernesto.logisticscalculator.converters;

import com.ernesto.logisticscalculator.commands.TileBoxCommand;
import com.ernesto.logisticscalculator.model.TileBox;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TileBoxToTileBoxCommand implements Converter<TileBox, TileBoxCommand> {

    @Transactional
    @Nullable
    @Override
    public TileBoxCommand convert(TileBox source) {
        if (source  == null) return null;

        final TileBoxCommand tileBoxCommand = new TileBoxCommand();

        tileBoxCommand.setId(source.getId());
        tileBoxCommand.setCode(source.getCode());
        tileBoxCommand.setDescription(source.getDescription());
        tileBoxCommand.setM2PerBox(source.getM2PerBox());
        tileBoxCommand.setBoxPerPallet(source.getBoxesPerPallet());
        tileBoxCommand.setWeightPerBox(source.getWeightPerBox());

        return tileBoxCommand;
    }
}
