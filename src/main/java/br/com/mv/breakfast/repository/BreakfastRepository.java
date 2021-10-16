package br.com.mv.breakfast.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.mv.breakfast.model.Breakfast;

@Repository
public class BreakfastRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public List<Breakfast> breakfastList() {
		Query query = entityManager.
				createNativeQuery("select * from breakfast", Breakfast.class);
		List<Breakfast> list = query.getResultList();
		return list;
	}
	
	@Transactional
	public Breakfast breakfastById(Long id) {
		Query query = entityManager.
				createNativeQuery("select * from breakfast where id = "+id+" ", Breakfast.class);
		Breakfast breakfastById = (Breakfast) query.getSingleResult();
		return breakfastById;
	}
	
	@Transactional
	public Boolean savedBreakfast(Breakfast breakfast) throws Exception {
		
		try {
			Query query = entityManager.createNativeQuery("insert into breakfast (nome, cpf, item) values ( '"+
					breakfast.getNome()+"','"+ breakfast.getCpf()+"','"+ breakfast.getItem() +"' ) ");
			query.executeUpdate();
		} catch (Exception e) {
			throw new Exception("Não foi possível salvar os dados.");
		}
		return true;
	}

	@Transactional
	public Boolean updateBreakfast(Breakfast breakfast) {
		Query query = entityManager.createNativeQuery("update breakfast set nome = '"+ breakfast.getNome()+"' , cpf = "
				+"'"+ breakfast.getCpf()+"' , item = '"+ breakfast.getItem() +"'  where id = "+breakfast.getId()+" ");
		query.executeUpdate();
		return true;
	}

	@Transactional
	public Boolean deleteBreakfast(Breakfast breakfast) {
		entityManager.createNativeQuery("delete from breakfast where id = "+breakfast.getId()+" ").executeUpdate();
		return true;
	}

}
