package pl.com.mnemonic.dao.interfaces;

import java.util.List;

public interface GenericDaoInterface<E, K> {

	void add(E entity);

	void update(E entity);

	void remove(E entity);

	E find(K key);

	List<E> list();

}
