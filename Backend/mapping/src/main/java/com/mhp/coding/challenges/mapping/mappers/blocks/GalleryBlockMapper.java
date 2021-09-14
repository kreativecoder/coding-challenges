package com.mhp.coding.challenges.mapping.mappers.blocks;

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.mhp.coding.challenges.mapping.models.dto.ImageDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GalleryBlockMapper implements BlockMapper<GalleryBlock, GalleryBlockDto> {

    private final ImageMapper imageMapper;

    public GalleryBlockMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Override
    public GalleryBlockDto map(GalleryBlock galleryBlock) {
        List<ImageDto> imageDtos = galleryBlock.getImages()
                .stream()
                .map(imageMapper::map)
                .collect(Collectors.toList());

        GalleryBlockDto galleryBlockDto = new GalleryBlockDto();
        galleryBlockDto.setImages(imageDtos);
        galleryBlockDto.setSortIndex(galleryBlock.getSortIndex());

        return galleryBlockDto;
    }

    @Override
    public boolean isMapper(ArticleBlock articleBlock) {
        return articleBlock instanceof GalleryBlock;
    }
}
