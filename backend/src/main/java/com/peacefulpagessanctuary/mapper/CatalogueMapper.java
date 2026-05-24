package com.peacefulpagessanctuary.mapper;
import com.peacefulpagessanctuary.dto.request.catalogue.CatalogueRequest;
import com.peacefulpagessanctuary.dto.response.catalogue.CatalogueResponse;
import com.peacefulpagessanctuary.model.Catalogue;
import org.springframework.stereotype.Component;

@Component
public class CatalogueMapper {

    public Catalogue toEntity(CatalogueRequest request) {

        return Catalogue.builder()
                .name(request.getName())
                .build();
    }

    public CatalogueResponse toResponse(Catalogue catalogue) {

        return CatalogueResponse.builder()
                .id(catalogue.getId())
                .name(catalogue.getName())
                .build();
    }

    public void updateEntity(
            Catalogue catalogue,
            CatalogueRequest request
    ) {

        catalogue.setName(request.getName());
    }
}