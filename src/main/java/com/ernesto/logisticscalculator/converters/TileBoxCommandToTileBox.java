package com.ernesto.logisticscalculator.converters;

import com.ernesto.logisticscalculator.commands.TileBoxCommand;
import com.ernesto.logisticscalculator.model.TileBox;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TileBoxCommandToTileBox implements Converter<TileBoxCommand, TileBox> {

    @Transactional
    @Nullable
    @Override
    public TileBox convert(TileBoxCommand source) {
        if (source == null) return null;

        final TileBox tileBox = new TileBox();

        tileBox.setId(source.getId());
        tileBox.setCode(source.getCode());
        tileBox.setDescription(source.getDescription());
        tileBox.setM2PerBox(source.getM2PerBox());
        tileBox.setBoxesPerPallet(source.getBoxPerPallet());
        tileBox.setWeightPerBox(source.getWeightPerBox());

        return tileBox;
    }
}
