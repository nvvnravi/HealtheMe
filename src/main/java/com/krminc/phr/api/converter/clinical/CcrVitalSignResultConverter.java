/**
 * Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krminc.phr.api.converter.clinical;

import com.krminc.phr.api.converter.DateAdapter;
import com.krminc.phr.api.converter.UriResolver;
import com.krminc.phr.domain.clinical.CcrVitalSignResult;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "ccrVitalSignResult")
public class CcrVitalSignResultConverter extends GenericResultConverter {
    private CcrVitalSignResult entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrVitalSignResultConverter */
    public CcrVitalSignResultConverter() {
        entity = new CcrVitalSignResult();
    }

    /**
     * Creates a new instance of CcrVitalSignResultConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public CcrVitalSignResultConverter(CcrVitalSignResult entity, URI uri, int expandLevel, boolean isUriExtendable) {
        super(entity);
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of CcrVitalSignResultConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrVitalSignResultConverter(CcrVitalSignResult entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for id.
     *
     * @return value for id
     */
    @XmlElement
    public Long getId() {
        return (expandLevel > 0) ? entity.getId() : null;
    }

    /**
     * Getter for resultTests.
     *
     * @return value for resultTests
     */
    @XmlElement
    public CcrVitalSignTestsConverter getResultTests() {
        if (expandLevel > 0) {
            if (entity.getVitalSignTests() != null) {
                return new CcrVitalSignTestsConverter(entity.getVitalSignTests(), uri.resolve("resultTests/"), expandLevel - 1);
            }
        }
        return null;
    }

    /**
     * Returns the URI associated with this converter.
     *
     * @return the uri
     */
    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    /** Custom getters (not autogenerated) **/

    /**
     * Getter for dateAdded.
     *
     * @return value for dateAdded
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDateAdded() {
        return (expandLevel > 0) ? entity.getDateAdded() : null;
    }

    /**
     * Getter for source.
     *
     * @return value for source
     */
    @XmlElement
    public String getSource() {
        return (expandLevel > 0) ? entity.getSource() : null;
    }

    /**
     * Getter for exactDateTime.
     *
     * @return value for exactDateTime
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getExactDateTime() {
        return (expandLevel > 0) ? entity.getExactDateTime() : null;
    }

    /** End Custom getters **/


    /**
     * Returns the CcrVitalSignResult entity.
     *
     * @return an entity
     */
    @XmlTransient
    public CcrVitalSignResult getEntity() {
        if (entity.getId() == null) {
            CcrVitalSignResultConverter converter = UriResolver.getInstance().resolve(CcrVitalSignResultConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved CcrVitalSignResult entity.
     *
     * @return an resolved entity
     */
    public CcrVitalSignResult resolveEntity(EntityManager em) {
        return entity;
    }
}
