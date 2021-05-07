package cscix370.termproject.repository;

import cscix370.termproject.entity.Tags;
import cscix370.termproject.interfaces.TagsTopTags; // needed for top tag search; query results different column amount from the entity

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagsRepository extends CrudRepository<Tags, Integer>{

    @Query(
        value = "SELECT tag_name AS name "
                + "FROM tags "
                + "INNER JOIN "
                + "(SELECT tag_id FROM book_tags "
                + "GROUP BY tag_id "
                + "ORDER BY COUNT(tag_id) DESC "
                + "LIMIT 20) AS ids "
                + "ON tags.tag_id = ids.tag_id",
        nativeQuery = true
    )
    public List<TagsTopTags> findTop100();

}