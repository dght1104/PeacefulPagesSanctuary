package com.peacefulpagessanctuary.service.impl;

import com.peacefulpagessanctuary.dto.request.catalogue.CatalogueRequest;
import com.peacefulpagessanctuary.dto.response.catalogue.CatalogueResponse;

import com.peacefulpagessanctuary.exception.ResourceNotFoundException;

import com.peacefulpagessanctuary.mapper.CatalogueMapper;

import com.peacefulpagessanctuary.model.Catalogue;

import com.peacefulpagessanctuary.repository.CatalogueRepository;

import com.peacefulpagessanctuary.service.CatalogueService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogueServiceImpl implements CatalogueService {

    private final CatalogueRepository catalogueRepository;
    private final CatalogueMapper catalogueMapper;

    @Override
    public List<CatalogueResponse> getAllCatalogues() {

        return catalogueRepository.findAll()
                .stream()
                .map(catalogueMapper::toResponse)
                .toList();
    }

    @Override
    public CatalogueResponse getCatalogueById(Long id) {

        Catalogue catalogue = catalogueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Catalogue not found"
                        ));

        return catalogueMapper.toResponse(catalogue);
    }

    @Override
    public CatalogueResponse createCatalogue(
            CatalogueRequest request
    ) {

        Catalogue catalogue =
                catalogueMapper.toEntity(request);

        Catalogue savedCatalogue =
                catalogueRepository.save(catalogue);

        return catalogueMapper.toResponse(savedCatalogue);
    }

    @Override
    public CatalogueResponse updateCatalogue(
            Long id,
            CatalogueRequest request
    ) {

        Catalogue catalogue = catalogueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Catalogue not found"
                        ));

        catalogueMapper.updateEntity(catalogue, request);

        Catalogue updatedCatalogue =
                catalogueRepository.save(catalogue);

        return catalogueMapper.toResponse(updatedCatalogue);
    }

    @Override
    public void deleteCatalogue(Long id) {

        Catalogue catalogue = catalogueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Catalogue not found"
                        ));

        catalogueRepository.delete(catalogue);
    }
}