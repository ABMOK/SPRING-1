package pl.com.mnemonic.ems.dao.implementation;

import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.PaymentDaoInterface;
import pl.com.mnemonic.ems.entity.Payment;

@Repository("paymentDao")
public class PaymentDaoImplementation extends HibernateGenericDaoImplementation<Payment, Integer> 
implements PaymentDaoInterface{

}
