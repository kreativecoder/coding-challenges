package com.mhp.coding.challenges.mapping.mappers.blocks;

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock;
import org.springframework.stereotype.Component;

@Component
public class ImageBlockMapper implements BlockMapper<ImageBlock, com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock> {

    private final ImageMapper imageMapper;

    public ImageBlockMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Override
    public com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock map(ImageBlock imageBlock) {
        com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock imageBlockDto = new com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock();
        imageBlockDto.setImage(imageMapper.map(imageBlock.getImage()));

        return imageBlockDto;
    }

    @Override
    public boolean isMapper(ArticleBlock articleBlock) {
        return articleBlock instanceof ImageBlock;
    }
}
