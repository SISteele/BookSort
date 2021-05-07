package cscix370.termproject.service;

import cscix370.termproject.entity.Tags;
import cscix370.termproject.repository.TagsRepository;
import cscix370.termproject.interfaces.TagsTopTags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service("tagsService")
public class TagsService {

    private TagsRepository tagsRepository;

    @Autowired
    public TagsService(TagsRepository tagsRepository) { 
      this.tagsRepository = tagsRepository;
    }

    // gets (20) most popular tag names
    public List<TagsTopTags> findTop100(){
        return tagsRepository.findTop100();
    }




}