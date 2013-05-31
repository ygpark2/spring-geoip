package com.ol.mvc.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate3.Hibernate3Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper() {
    	Hibernate3Module hm = new Hibernate3Module();
        hm.configure(Hibernate3Module.Feature.FORCE_LAZY_LOADING, false);
        registerModule(hm);
        configure(SerializationFeature.INDENT_OUTPUT, true);
    }

}