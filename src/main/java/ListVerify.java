import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListVerify {

	public static void main(String[] args) {
		boolean b = false;
		List<Model> list1 = new ArrayList<>();
		Model model1 = new Model("Ali", "Gmail");
		Model model2 = new Model("Ahmed", "hotmail");
		Model model3 = new Model("Ali", "Yahoo");
		Model model4 = new Model("Saeed", "outlook");
		Model model5 = new Model("Ali", "hotmail");
		list1.add(model1);
		list1.add(model2);
		list1.add(model3);
		list1.add(model4);
		list1.add(model5);

		try {
			for (int i = 0; i < list1.size(); i++) {
				for (int a = 0; a < list1.size(); a++) {
					if (list1.get(i).getName().equalsIgnoreCase(list1.get(a).getName()) 
							&& i!=a && list1.get(i).getEmail().equalsIgnoreCase(list1.get(a).getEmail())) {

						b = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(b);
	}

}

class Model {

	private String name;
	private String email;

	public String getName() {
		return name;
	}

	public Model(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}