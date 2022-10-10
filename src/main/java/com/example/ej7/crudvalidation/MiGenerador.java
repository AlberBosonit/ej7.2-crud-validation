package com.example.ej7.crudvalidation;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.exception.spi.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

public class MiGenerador implements IdentifierGenerator, Configurable {

    private String prefijo;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(), //id_persona en el caso de Persona.
                obj.getClass().getSimpleName()); //En este punto sería "select id_persona from persona"

        Stream<String> ids = session.createQuery(query).stream();

        Long max = ids.map(o -> o.replace(prefijo + "-", ""))

                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);

        return prefijo + "-" + (max + 1);
    }

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
        prefijo = properties.getProperty("prefijo");
    }


    @Override
    public void configure(Properties properties) throws HibernateException {

    }
}