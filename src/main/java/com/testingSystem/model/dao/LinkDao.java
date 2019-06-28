package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Link;

public interface LinkDao {
    /**
     *
     *
     * @param literatureId aaa
     * @return
     */
    Link getLinkByLiteratureId(int literatureId);
}
