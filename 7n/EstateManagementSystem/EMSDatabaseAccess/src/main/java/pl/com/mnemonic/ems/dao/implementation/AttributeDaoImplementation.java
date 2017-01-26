package pl.com.mnemonic.ems.dao.implementation;

import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.AttributeDaoInterface;
import pl.com.mnemonic.ems.entity.Attribute;

@Repository("attributeDao")
public class AttributeDaoImplementation extends HibernateGenericDaoImplementation<Attribute, Integer> implements AttributeDaoInterface  {

}
