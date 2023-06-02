package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrands();
    Brand getBrandById(Long id);
    Brand saveBrand(Brand brand);
    Brand updateBrand(Brand updateBrand,Long id);
    Brand deleteBrandById(Long id);
}
