package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Brand;
import com.oswelddev.parkingv1.models.repository.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public Brand updateBrand(Brand updateBrand,Long id) {
        return brandRepository.findById(id).map(brand -> {
            brand.setId(id);
            brand.setBrand(updateBrand.getBrand());
            return brandRepository.save(brand);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public Brand deleteBrandById(Long id) {
        return brandRepository.findById(id).map(brand ->{
            brandRepository.deleteById(brand.getId());
            return brand;
        }).orElseThrow();
    }
}
