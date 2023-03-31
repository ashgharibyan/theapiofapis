package com.ashgharibyan.apiofapis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashgharibyan.apiofapis.models.Template;
import com.ashgharibyan.apiofapis.repositories.TemplateRepository;

@Service
public class TemplateService {
	private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    // returns all the entries
    public List<Template> allTemplates() {
        return (List<Template>) templateRepository.findAll();
    }

    // create
    public Template createTemplate(Template b) {
        return templateRepository.save(b);
    }

    // retrieves by id
    public Template findTemplate(Long id) {
        Optional<Template> optionalTemplate = templateRepository.findById(id);
        if (optionalTemplate.isPresent()) {
            return optionalTemplate.get();
        } else {
            return null;
        }
    }
    
    // updates
    public Template updateTemplate(Template template) {
        return templateRepository.save(template);

    }
    
    // return true if deleted, returns false if there is not entry with that id
    public Boolean deleteTemplate(Long id) {
        if (findTemplate(id) == null) {
            return false;
        }
        templateRepository.deleteById(id);
        return true;
    }
}
