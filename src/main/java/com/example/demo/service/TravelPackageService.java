package com.example.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.Services;
import com.example.demo.model.TravelPackage;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.TravelPackageRepository;

public class TravelPackageService {

	private TravelPackageRepository travelPackageRepository;
	private ServiceRepository servicesRepository;
	private ImageRepository imageRepository;

	public TravelPackageService(ServiceRepository serviceRepository, ImageRepository imageRepository,
			TravelPackageRepository travelPackageRepository) {
		super();
		this.servicesRepository = serviceRepository;
		this.imageRepository = imageRepository;
		this.travelPackageRepository = travelPackageRepository;
	}

	@Transactional
	public Iterable<TravelPackage> findAllTravelPackage() {
		return travelPackageRepository.findAll();
	}

	@Transactional
	public TravelPackage saveAllTravelPackage(TravelPackage travelPackage) {
		travelPackage = travelPackageRepository.save(travelPackage);
		for (Services allService : travelPackage.getAvailableServiceList()) {
			allService.setTravelPackage(travelPackage);
			saveService(allService);
		}
		for (Image allImageService : travelPackage.getImages()) {
			allImageService.setTravelPackage(travelPackage);
			saveAllImage(allImageService);
		}
		return travelPackage;
	}

	@Transactional
	public Services findService(int serviceId) {
		return servicesRepository.findById(serviceId).get();
	}

	@Transactional
	public Services saveService(Services service) {
		service = servicesRepository.save(service);
		for (Image allImage : service.getImages()) {
			allImage.setService(service);
			saveAllImage(allImage);
		}

		return service;
	}

	@Transactional
	public Image saveAllImage(Image image) {
		return imageRepository.save(image);
	}

	@Transactional
	public void deleteAllTravelPackage() {
		deleteAllService();
		travelPackageRepository.deleteAll();
	}

	@Transactional
	public void deleteAllService() {
		imageRepository.deleteAll();
		servicesRepository.deleteAll();
	}

	@Transactional
	public TravelPackage findTravelPackage(int travelPackageId) {
		return travelPackageRepository.findById(travelPackageId).get();
	}

	@Transactional
	public void deleteTravelPackage(int travelPackageId) {
		travelPackageRepository.deleteById(travelPackageId);
	}

	@Transactional
	public TravelPackage saveTravelPackage(TravelPackage travelPackage) {
		return travelPackageRepository.save(travelPackage);
	}

	@Transactional
	public void deleteTravelPackageService(List<Services> travelPackage) {
		servicesRepository.deleteAll(travelPackage);
	}
}
