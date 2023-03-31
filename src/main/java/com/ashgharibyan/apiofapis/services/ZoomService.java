package com.ashgharibyan.apiofapis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashgharibyan.apiofapis.models.Zoom;
import com.ashgharibyan.apiofapis.repositories.ZoomRepository;

@Service
public class ZoomService {
    // adding the object repository as a dependency
    private final ZoomRepository zoomRepository;

    public ZoomService(ZoomRepository zoomRepository) {
        this.zoomRepository = zoomRepository;
    }

    // returns all the entries
    public List<Zoom> allZooms() {
        return (List<Zoom>) zoomRepository.findAll();
    }

    // create
    public Zoom createZoom(Zoom b) {
        return zoomRepository.save(b);
    }

    // retrieves by id
    public Zoom findZoom(Long id) {
        Optional<Zoom> optionalZoom = zoomRepository.findById(id);
        if (optionalZoom.isPresent()) {
            return optionalZoom.get();
        } else {
            return null;
        }
    }
    
    // updates
    public Zoom updateZoom(Zoom zoom) {
        return zoomRepository.save(zoom);

    }
    
    // return true if deleted, returns false if there is not entry with that id
    public Boolean deleteZoom(Long id) {
        if (findZoom(id) == null) {
            return false;
        }
        zoomRepository.deleteById(id);
        return true;
    }
}
