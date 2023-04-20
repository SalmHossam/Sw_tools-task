package calculation;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class calculations {
	public calculations() {
	}

	@PersistenceContext
	private EntityManager entityManager;
	int Result = 0;

	@GET
	@Path("test")
	public String test() {
		return "exist";
	}

	@Path("calc")
	@POST
	public int Calc(calculationEntity calc) {
		entityManager.persist(calc);

		if (calc.getOperation() == "+") {
			Result = calc.getNum1() + calc.getNum2();
		}
		if (calc.getOperation() == "*") {
			Result = calc.getNum1() * calc.getNum2();
		}
		if (calc.getOperation() == "-") {
			Result = calc.getNum1() - calc.getNum2();
		}
		if (calc.getOperation() == "/") {
			Result = calc.getNum1() / calc.getNum2();
		}
		return Result;
	}

	@Path("calculations")
	@GET
	public List<calculationEntity> Get_Calculation() {

		TypedQuery<calculationEntity> query = entityManager.createQuery("SELECT c FROM calculationEntity c",
				calculationEntity.class);
		List<calculationEntity> calculationsList = query.getResultList();
		return calculationsList;

	}
}
