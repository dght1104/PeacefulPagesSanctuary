package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.catalogue.CatalogueRequest;
import com.peacefulpagessanctuary.dto.response.catalogue.CatalogueResponse;

import java.util.List;

public interface CatalogueService {

    List<CatalogueResponse> getAllCatalogues();

    CatalogueResponse getCatalogueById(Long id);

    CatalogueResponse createCatalogue(CatalogueRequest request);

    CatalogueResponse updateCatalogue(Long id, CatalogueRequest request);

    void deleteCatalogue(Long id);
}