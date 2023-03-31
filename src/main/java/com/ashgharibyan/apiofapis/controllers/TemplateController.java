package com.ashgharibyan.apiofapis.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashgharibyan.apiofapis.models.Template;
import com.ashgharibyan.apiofapis.models.User;
import com.ashgharibyan.apiofapis.services.UserService;

@Controller
public class TemplateController {

	@Autowired
	private UserService userServ;

	@GetMapping("/create/API")
	public String index(@ModelAttribute("template") Template template) {

		return "/LoggedUserViews/createAPI.jsp";
	}

	@PostMapping("/create/API/process")
	public String createModel(@Valid @ModelAttribute("template") Template template, BindingResult result,
			HttpSession session) {
		User currentUser = userServ.findById((Long) session.getAttribute("user_id"));

		if (result.hasErrors()) {
			return "/LoggedUserViews/createAPI.jsp";
		}
		String currentUserName = currentUser.getUserName().toLowerCase();
		String projectName = "apiofapis";
		String classNameFirstUpper = template.getClassName().substring(0, 1).toUpperCase()
				+ template.getClassName().substring(1).toLowerCase();
		String classNameLowercase = template.getClassName().toLowerCase();

		String strAttr1FirstUpper = template.getStringAttribute1().substring(0, 1).toUpperCase()
				+ template.getStringAttribute1().substring(1);
		String strAttr1Lowercase = template.getStringAttribute1().toLowerCase();

		String intAttr1FirstUpper = template.getIntegerAttribute1().substring(0, 1).toUpperCase()
				+ template.getIntegerAttribute1().substring(1);
		String intAttr1Lowercase = template.getIntegerAttribute1().toLowerCase();

		// FILENAMES
		String modelFilename = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/java/com/ashgharibyan/apiofapis/models/%s.java",
				classNameFirstUpper);
		String repoFilename = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/java/com/ashgharibyan/apiofapis/repositories/%sRepository.java",
				classNameFirstUpper);
		String serviceFilename = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/java/com/ashgharibyan/apiofapis/services/%sService.java",
				classNameFirstUpper);
		String controllerFilename = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/java/com/ashgharibyan/apiofapis/controllers/%sController.java",
				classNameFirstUpper);
		String APIControllerFilename = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/java/com/ashgharibyan/apiofapis/controllers/%sAPIController.java",
				classNameFirstUpper);

		String JSPFolderName = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/webapp/WEB-INF/%s-%s",
				classNameLowercase, currentUser.getUserName().toLowerCase());
		String modelCreateJSP = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/webapp/WEB-INF/%s-%s/%sCreate.jsp",
				classNameLowercase, currentUser.getUserName().toLowerCase(), classNameLowercase);
		String modelEditJSP = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/webapp/WEB-INF/%s-%s/%sEdit.jsp",
				classNameLowercase, currentUser.getUserName().toLowerCase(), classNameLowercase);
		String modelShowAllJSP = String.format(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/webapp/WEB-INF/%s-%s/%sShowAll.jsp",
				classNameLowercase, currentUser.getUserName().toLowerCase(), classNameLowercase);

		// CREATING THE MODEL FILE
		try {
			File myObj = new File(modelFilename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.[MODEL FILE CREATION]");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.[MODEL FILE CREATION]");
			e.printStackTrace();
		}

		// CREATING THE REPOSITORY FILE
		try {

			File myObj = new File(repoFilename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.[REPO FILE CREATION]");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.[REPO FILE CREATION]");
			e.printStackTrace();
		}

		// CREATING THE SERVICE FILE
		try {
			File myObj = new File(serviceFilename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.[SERVICE FILE CREATION]");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.[SERVICE FILE CREATION]");
			e.printStackTrace();
		}

		// CREATING THE API CONTROLLER FILE
		try {
			File myObj = new File(APIControllerFilename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.[API CONTROLLER FILE CREATION]");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.[API CONTROLLER FILE CREATION]");
			e.printStackTrace();
		}

		// CREATING THE CONTROLLER FILE
		try {
			File myObj = new File(controllerFilename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.[CONTROLLER FILE CREATION]");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.[CONTROLLER FILE CREATION]");
			e.printStackTrace();
		}
//		

		// CREATING THE FOLDER FOR JSPS
		// specify an abstract pathname in the File object
		File jspFolder = new File(JSPFolderName);

		// check if the directory can be created
		// using the specified path name
		if (jspFolder.mkdir() == true) {
			System.out.println("JSP FOLDER has been created successfully");
		} else {
			System.out.println("JSP FOLDER cannot be created");
		}

		// CREATING THE CREATE JSP
		try {
			File myObj = new File(modelCreateJSP);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.[CREATE JSP FILE CREATION]");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.[CREATE JSP FILE CREATION]");
			e.printStackTrace();
		}

		// CREATING THE EDIT JSP
		try {
			File myObj = new File(modelEditJSP);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.[EDIT JSP FILE CREATION]");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.[EDIT JSP FILE CREATION]");
			e.printStackTrace();
		}
		// CREATING THE SHOW ALL JSP
		try {
			File myObj = new File(modelShowAllJSP);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.[SHOW ALL JSP FILE CREATION]");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.[SHOW ALL JSP FILE CREATION]");
			e.printStackTrace();
		}

		// WRITING TO THE MODEL FILE
		try {
			FileWriter myWriter = new FileWriter(modelFilename);

			String codeTemplate = "package com.ashgharibyan.%s.models;\n" + "\n" + "import java.util.Date;\n" + "\n"
					+ "import javax.persistence.Column;\n" + "import javax.persistence.Entity;\n"
					+ "import javax.persistence.GeneratedValue;\n" + "import javax.persistence.GenerationType;\n"
					+ "import javax.persistence.Id;\n" + "import javax.persistence.PrePersist;\n"
					+ "import javax.persistence.PreUpdate;\n" + "import javax.persistence.Table;\n"
					+ "import javax.validation.constraints.NotEmpty;\n" + "import javax.persistence.JoinColumn;\n"
					+ "import javax.persistence.ManyToOne;\n" + "import javax.persistence.FetchType;\n" + "" + "\n"
					+ "import org.springframework.format.annotation.DateTimeFormat;\n" + "\n" + "@Entity\n"
					+ "@Table(name = \"%s\")\n" + "public class %s {\n" + "\n" + "	@Id\n"
					+ "	@GeneratedValue(strategy = GenerationType.IDENTITY) // generates an auto incrementing\n"
					+ "	private Long id;\n" + "\n" + "	// String Attribute\n"
					+ "	@NotEmpty(message = \"%s name is required!\") // validation for strings\n"
					+ "	private String %s;\n" + "\n" + "	// Integer Attribute\n"
					+ "	@NotEmpty(message = \"%s name is required!\") // validation for strings\n"
					+ "	private String %s;\n" + "\n" + "	@Column(updatable = false)\n"
					+ "	@DateTimeFormat(pattern = \"yyyy-MM-dd\")\n" + "	private Date createdAt;\n" + "\n"
					+ "	@DateTimeFormat(pattern = \"yyyy-MM-dd\")\n" + "	private Date updatedAt;\n" + "\n"
					+ "    @ManyToOne(fetch = FetchType.LAZY)\n" + "    @JoinColumn(name=\"template_id\")\n"
					+ "    private Template template;" + "	public %s() {\n" + "\n" + "	}\n" + "\n"
					+ "	public %s(String %s, String %s){\n" + "		this.%s = %s;\n" + "		this.%s = %s;\n"
					+ "	}\n" + "\n" + "	@PrePersist // adds the created at date and time to sql on creation\n"
					+ "	protected void onCreate() {\n" + "		this.createdAt = new Date();\n" + "	}\n" + "\n"
					+ "	@PreUpdate // add the updated at date and time when being updated\n"
					+ "	protected void onUpdate() {\n" + "		this.updatedAt = new Date();\n" + "	}\n" + "	\n"
					+ "	public Long getId() {\n" + "		return id;\n" + "	}\n" + "\n"
					+ "	public void setId(Long id) {\n" + "		this.id = id;\n" + "	}\n" + "\n" + "\n"
					+ "	public String get%s() {\n" + "		return %s;\n" + "	}\n" + "\n"
					+ "	public void set%s(String %s) {\n" + "		this.%s = %s;\n" + "	}\n" + "\n"
					+ "	public String get%s() {\n" + "		return %s;\n" + "	}\n" + "\n"
					+ "	public void set%s(String %s) {\n" + "		this.%s = %s;\n" + "	}\n" + "	\n"
					+ "	public Date getCreatedAt() {\n" + "		return createdAt;\n" + "	}\n" + "\n"
					+ "	public Date getUpdatedAt() {\n" + "		return updatedAt;\n" + "	}\n" + "\n" + "}\n" + "";
			String codeToWrite = String.format(codeTemplate, projectName, template.getClassName().toLowerCase() + "s", // table
																														// name
					classNameFirstUpper, // class declaration
					strAttr1FirstUpper, // validation
					strAttr1Lowercase, // var name
					intAttr1FirstUpper, // validation
					intAttr1Lowercase, // var name
					classNameFirstUpper, // constructor
					classNameFirstUpper, // Argument constructor
					strAttr1Lowercase, // Arg constructor assignment
					intAttr1Lowercase, // Arg constructor assignment
					strAttr1Lowercase, // Arg constructor assignment
					strAttr1Lowercase, // Arg constructor assignment
					intAttr1Lowercase, // Arg constructor assignment
					intAttr1Lowercase, // Arg constructor assignment
					strAttr1FirstUpper, // getter/setter
					strAttr1Lowercase, // getter/setter
					strAttr1FirstUpper, // getter/setter
					strAttr1Lowercase, // getter/setter
					strAttr1Lowercase, // getter/setter
					strAttr1Lowercase, // getter/setter
					intAttr1FirstUpper, // getter/setter
					intAttr1Lowercase, // getter/setter
					intAttr1FirstUpper, // getter/setter
					intAttr1Lowercase, // getter/setter
					intAttr1Lowercase, // getter/setter
					intAttr1Lowercase);// getter/setter

			myWriter.write(codeToWrite);
			myWriter.close();
			System.out.println("Successfully wrote MODEL CODE to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred. [MODEL CODE WRITING]");
			e.printStackTrace();
		}

		// WRITING TO THE REPOSITORY FILE
		try {
			FileWriter myWriter = new FileWriter(repoFilename);

			String codeTemplate = "package com.ashgharibyan.%s.repositories;\n" + "\n"
					+ "import org.springframework.data.repository.CrudRepository;\n"
					+ "import org.springframework.stereotype.Repository;\n" + "\n"
					+ "import com.ashgharibyan.%s.models.%s;\n" + "\n" + "\n" + "@Repository\n"
					+ "public interface %sRepository extends CrudRepository<%s, Long>{\n" + "	\n" + "}\n" + "\n" + "";
			String codeToWrite = String.format(codeTemplate, projectName, projectName, classNameFirstUpper,
					classNameFirstUpper, classNameFirstUpper);

			myWriter.write(codeToWrite);
			myWriter.close();
			System.out.println("Successfully wrote REPO CODE to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred. [REPO CODE WRITING]");
			e.printStackTrace();
		}

		// WRITING TO THE SERVICE FILE
		try {
			FileWriter myWriter = new FileWriter(serviceFilename);

			String codeTemplate = "package com.ashgharibyan.%s.services;\n" + "\n" + "import java.util.List;\n"
					+ "import java.util.Optional;\n" + "\n" + "import org.springframework.stereotype.Service;\n" + "\n"
					+ "import com.ashgharibyan.%s.models.%s;\n"
					+ "import com.ashgharibyan.%s.repositories.%sRepository;\n" + "\n" + "@Service\n"
					+ "public class %sService {\n" + "    // adding the object repository as a dependency\n"
					+ "    private final %sRepository %sRepository;\n" + "\n"
					+ "    public %sService(%sRepository %sRepository) {\n"
					+ "        this.%sRepository = %sRepository;\n" + "    }\n" + "\n"
					+ "    // returns all the entries\n" + "    public List<%s> all%ss() {\n"
					+ "        return (List<%s>) %sRepository.findAll();\n" + "    }\n" + "\n" + "    // create\n"
					+ "    public %s create%s(%s b) {\n" + "        return %sRepository.save(b);\n" + "    }\n" + "\n"
					+ "    // retrieves by id\n" + "    public %s find%s(Long id) {\n"
					+ "        Optional<%s> optional%s = %sRepository.findById(id);\n"
					+ "        if (optional%s.isPresent()) {\n" + "            return optional%s.get();\n"
					+ "        } else {\n" + "            return null;\n" + "        }\n" + "    }\n" + "    \n"
					+ "    // updates\n" + "    public %s update%s(%s %s) {\n"
					+ "        return %sRepository.save(%s);\n" + "\n" + "    }\n" + "    \n"
					+ "    // return true if deleted, returns false if there is not entry with that id\n"
					+ "    public Boolean delete%s(Long id) {\n" + "        if (find%s(id) == null) {\n"
					+ "            return false;\n" + "        }\n" + "        %sRepository.deleteById(id);\n"
					+ "        return true;\n" + "    }\n" + "}\n" + "";
			String codeToWrite = String.format(codeTemplate, projectName, projectName, classNameFirstUpper, projectName,
					classNameFirstUpper, classNameFirstUpper, classNameFirstUpper, classNameLowercase,
					classNameFirstUpper, classNameFirstUpper, classNameLowercase, classNameLowercase,
					classNameLowercase, classNameFirstUpper, classNameFirstUpper, classNameFirstUpper,
					classNameLowercase, classNameFirstUpper, classNameFirstUpper, classNameFirstUpper,
					classNameLowercase, classNameFirstUpper, classNameFirstUpper, classNameFirstUpper,
					classNameFirstUpper, classNameLowercase, classNameFirstUpper, classNameFirstUpper,
					classNameFirstUpper, classNameFirstUpper, classNameFirstUpper, classNameLowercase,
					classNameLowercase, classNameLowercase, classNameFirstUpper, classNameFirstUpper,
					classNameLowercase);

			myWriter.write(codeToWrite);
			myWriter.close();
			System.out.println("Successfully wrote SERVICE CODE to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred. [SERVICE CODE WRITING]");
			e.printStackTrace();
		}

		// WRITING TO THE API CONTROLLER FILE
		try {
			FileWriter myWriter = new FileWriter(APIControllerFilename);

			String codeTemplate = "package com.ashgharibyan.%s.controllers;\n"
					+ "\n"
					+ "import java.util.HashMap;\n"
					+ "import java.util.List;\n"
					+ "\n"
					+ "import org.springframework.web.bind.annotation.PathVariable;\n"
					+ "import org.springframework.web.bind.annotation.RequestMapping;\n"
					+ "import org.springframework.web.bind.annotation.RequestMethod;\n"
					+ "import org.springframework.web.bind.annotation.RequestParam;\n"
					+ "import org.springframework.web.bind.annotation.RestController;\n"
					+ "\n"
					+ "import com.ashgharibyan.%s.models.%s;\n"
					+ "import com.ashgharibyan.%s.services.%sService;\n"
					+ "\n"
					+ "@RestController\n"
					+ "public class %sAPIController {\n"
					+ "	private final %sService %sService;\n"
					+ "\n"
					+ "	public %sAPIController(%sService %sService) {\n"
					+ "		this.%sService = %sService;\n"
					+ "	}\n"
					+ "\n"
					+ "	@RequestMapping(\"/api/%s/%ss\")\n"
					+ "	public List<%s> index() {\n"
					+ "		return %sService.all%ss();\n"
					+ "	}\n"
					+ "\n"
					+ "	@RequestMapping(value = \"/api/%s/%ss\", method = RequestMethod.POST)\n"
					+ "	public %s create(@RequestParam(value = \"%s\") String %s, @RequestParam(value = \"%s\") String %s) {\n"
					+ "		%s %s = new %s(%s, %s);\n"
					+ "		return %sService.create%s(%s);\n"
					+ "	}\n"
					+ "\n"
					+ "	@RequestMapping(\"/api/%s/%ss/{id}\")\n"
					+ "	public Object show(@PathVariable(\"id\") Long id) {\n"
					+ "		%s %s = %sService.find%s(id);\n"
					+ "		if(%s==null) {\n"
					+ "		    HashMap<String, String> err = new HashMap<String, String>();\n"
					+ "		    err.put(\"Error\", \"Not Found\");\n"
					+ "		    return err;\n"
					+ "		}\n"
					+ "		return %s;\n"
					+ "	}\n"
					+ "\n"
					+ "	@RequestMapping(value = \"/api/%s/%ss/{id}\", method = RequestMethod.PUT)\n"
					+ "	public %s update(@PathVariable(\"id\") Long id, @RequestParam(value = \"%s\") String %s, @RequestParam(value = \"%s\") String %s) {\n"
					+ "		\n"
					+ "		%s %s = new %s(%s,%s);\n"
					+ "		%s.setId(id);\n"
					+ "		%s = %sService.update%s(%s);\n"
					+ "		return %s;\n"
					+ "	}\n"
					+ "\n"
					+ "	@RequestMapping(value = \"/api/%s/%ss/{id}\", method = RequestMethod.DELETE)\n"
					+ "	public Object destroy(@PathVariable(\"id\") Long id) {\n"
					+ "		Boolean isDeleted = (Boolean) %sService.delete%s(id);\n"
					+ "		HashMap<String, String> msg = new HashMap<String, String>();\n"
					+ "	    if(isDeleted){\n"
					+ "	    	msg.put(\"Message\", \"Successfully deleted!\");\n"
					+ "	    }else {\n"
					+ "	    	msg.put(\"Error\", \"No entry at the given id!\");\n"
					+ "	    }\n"
					+ "		return msg; \n"
					+ "	}\n"
					+ "\n"
					+ "}\n"
					+ "";
			
			String codeToWrite = String.format(codeTemplate,
					projectName, 
					projectName,
					classNameFirstUpper,
					projectName,
					classNameFirstUpper,
					classNameFirstUpper, // classname
					classNameFirstUpper, // service
					classNameLowercase,
					classNameFirstUpper, //constructor
					classNameFirstUpper,
					classNameLowercase,
					classNameLowercase,
					classNameLowercase,
					currentUserName, //getmapping index
					classNameLowercase,
					classNameFirstUpper,
					classNameLowercase,
					classNameFirstUpper,
					currentUserName, //postmapping create
					classNameLowercase,
					classNameFirstUpper,
					strAttr1Lowercase,
					strAttr1Lowercase,
					intAttr1Lowercase,
					intAttr1Lowercase,
					classNameFirstUpper,
					classNameLowercase,
					classNameFirstUpper,
					strAttr1Lowercase,
					intAttr1Lowercase,
					classNameLowercase, 
					classNameFirstUpper,
					classNameLowercase,
					currentUserName, //getmapping show
					classNameLowercase,
					classNameFirstUpper,
					classNameLowercase, 
					classNameLowercase,
					classNameFirstUpper,
					classNameLowercase,
					classNameLowercase,
					currentUserName, //putmapping update
					classNameLowercase,
					classNameFirstUpper,
					strAttr1Lowercase,
					strAttr1Lowercase,
					intAttr1Lowercase,
					intAttr1Lowercase,
					classNameFirstUpper, classNameLowercase, classNameFirstUpper, strAttr1Lowercase, intAttr1Lowercase,
					classNameLowercase, classNameLowercase, classNameLowercase, classNameFirstUpper, classNameLowercase,
					classNameLowercase,
					currentUserName, //deletemapping delete
					classNameLowercase,
					classNameLowercase,
					classNameFirstUpper);
			
			myWriter.write(codeToWrite);
			myWriter.close();
			System.out.println("Successfully wrote API CONTROLLER CODE to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.[API CONTROLLER]");
			e.printStackTrace();
		}

		// WRITING TO THE CONTROLLER FILE
		try {
			FileWriter myWriter = new FileWriter(controllerFilename);

			String codeTemplate = "package com.ashgharibyan.%s.controllers;\n" + "\n"
					+ "import javax.servlet.http.HttpSession;\n" + "import javax.validation.Valid;\n" + "\n"
					+ "import org.springframework.beans.factory.annotation.Autowired;\n"
					+ "import org.springframework.stereotype.Controller;\n" + "import org.springframework.ui.Model;\n"
					+ "import org.springframework.validation.BindingResult;\n"
					+ "import org.springframework.web.bind.annotation.DeleteMapping;\n"
					+ "import org.springframework.web.bind.annotation.GetMapping;\n"
					+ "import org.springframework.web.bind.annotation.ModelAttribute;\n"
					+ "import org.springframework.web.bind.annotation.PathVariable;\n"
					+ "import org.springframework.web.bind.annotation.PostMapping;\n"
					+ "import org.springframework.web.bind.annotation.PutMapping;\n" + "\n"
					+ "import com.ashgharibyan.%s.models.%s;\n" + "import com.ashgharibyan.%s.services.%sService;\n"
					+ "import com.ashgharibyan.%s.services.UserService;\n" + "\n" + "@Controller\n"
					+ "public class %sController {\n" + "	\n" + "	private final %sService %sService;\n" + "	\n"
					+ "	@Autowired\n" + "	private UserService userServ;\n" + "\n"
					+ "	public %sController(%sService %sService) {\n" + "		this.%sService = %sService;\n" + "	}\n"
					+ "\n" + "	@GetMapping(\"/%s/%ss\")\n" + "	public String showAll(Model model) {\n"
					+ "		model.addAttribute(\"%ss\", %sService.all%ss());\n"
					+ "		return \"/%s-%s/%sShowAll.jsp\";\n" + "	}\n" + "	\n"
					+ "	@GetMapping(\"/%s/%s/create\")\n"
					+ "	public String createPage(@ModelAttribute(\"new%s\") %s %s) {\n"
					+ "		return \"/%s-%s/%sCreate.jsp\";\n" + "	}\n" + "	\n"
					+ "	@PostMapping(\"/%s/%s/new/process\")\n"
					+ "	public String create%sProcess(@Valid @ModelAttribute(\"new%s\") %s %s, BindingResult result, HttpSession session) {\n"
					+ "		if(result.hasErrors()) {\n" + "			return \"/%s-%s/%sCreate.jsp\";\n" + "		}\n"
					+ "		\n" + "		%sService.create%s(%s);\n" + "		return \"redirect:/%s/%ss\";\n" + "	}\n"
					+ "	\n" + "	@DeleteMapping(\"/%s/%s/{id}/delete\")\n"
					+ "	public String delete%s(@PathVariable(\"id\") Long id) {\n" + "		%sService.delete%s(id);\n"
					+ "		return \"redirect:/%s/%ss\";\n" + "	}\n" + "	\n" + "	@GetMapping(\"/%s/%s/{id}/edit\")\n"
					+ "	public String editPage(@PathVariable(\"id\") Long id, Model model) {\n"
					+ "		model.addAttribute(\"%s\", %sService.find%s(id));\n"
					+ "		return \"/%s-%s/%sEdit.jsp\";\n" + "	}\n" + "	\n"
					+ "	@PutMapping(\"/%s/%s/edit/{id}/process\")\n"
					+ "	public String edit%sProcess(@Valid @ModelAttribute(\"%s\") %s %s, BindingResult result) {\n"
					+ "		if(result.hasErrors()) {\n" + "			return \"/%s-%s/%sEdit.jsp\";\n" + "		}\n"
					+ "		%sService.update%s(%s);\n" + "		return \"redirect:/%s/%ss\";\n" + "	}\n" + "	\n"
					+ "}\n" + "";
			String codeToWrite = String.format(codeTemplate, projectName, projectName, classNameFirstUpper, projectName,
					classNameFirstUpper, projectName, // line 19
					classNameFirstUpper, classNameFirstUpper, classNameLowercase, classNameFirstUpper,
					classNameFirstUpper, classNameLowercase, classNameLowercase, classNameLowercase, // finish
																										// constructor
																										// line 29
					currentUserName, classNameLowercase, classNameLowercase, classNameLowercase, classNameFirstUpper,
					classNameLowercase, currentUserName, classNameLowercase, // finish get mapping showAll
					currentUserName, // start get mapping createPage
					classNameLowercase, classNameFirstUpper, classNameFirstUpper, classNameLowercase,
					classNameLowercase, currentUserName, classNameLowercase, // finish get mapping createPage line 41
					currentUserName, // start post mapping create process
					classNameLowercase, classNameFirstUpper, classNameFirstUpper, classNameFirstUpper,
					classNameLowercase, classNameLowercase, currentUserName, classNameLowercase, classNameLowercase,
					classNameFirstUpper, classNameLowercase, currentUserName, classNameLowercase, // finish post mapping
																									// create process
																									// line 51
					currentUserName, // start delete mapping
					classNameLowercase, classNameFirstUpper, classNameLowercase, classNameFirstUpper, currentUserName,
					classNameLowercase, // end delete mapping line 57
					currentUserName, // start get mapping editPage
					classNameLowercase, classNameLowercase, classNameLowercase, classNameFirstUpper, classNameLowercase,
					currentUserName, classNameLowercase, // end get mapping editPage
					currentUserName, // start put mapping edit process
					classNameLowercase, classNameFirstUpper, classNameLowercase, classNameFirstUpper,
					classNameLowercase, classNameLowercase, currentUserName, classNameLowercase, classNameLowercase,
					classNameFirstUpper, classNameLowercase, currentUserName, classNameLowercase// finish put mapping
																								// edit
			);

			myWriter.write(codeToWrite);
			myWriter.close();
			System.out.println("Successfully wrote CONTROLLER CODE to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.[CONTROLLER CODE]");
			e.printStackTrace();
		}

		// WRITING TO THE CREATE JSP FILE
		try {
			FileWriter myWriter = new FileWriter(modelCreateJSP);

			String codeTemplate = "<meta charset=\"UTF-8\">\n" + "<title>Create %s</title>\n" + "</head>\n" + "<body>\n"
					+ "\n" + "\n" + "	<div class=\"header\">\n" + "\n" + "		<!--Content before waves-->\n"
					+ "		<div class=\"inner-header flex\">\n" + "\n"
					+ "			<path fill=\"#FFFFFF\" stroke=\"#000000\" stroke-width=\"10\"\n"
					+ "				stroke-miterlimit=\"10\" d=\"M57,283\" />\n"
					+ "			<g> <path fill=\"#fff\"\n"
					+ "				d=\"M250.4,0.8C112.7,0.8,1,112.4,1,250.2c0,137.7,111.7,249.4,249.4,249.4c137.7,0,249.4-111.7,249.4-249.4\n"
					+ "C499.8,112.4,388.1,0.8,250.4,0.8z M383.8,326.3c-62,0-101.4-14.1-117.6-46.3c-17.1-34.1-2.3-75.4,13.2-104.1\n"
					+ "c-22.4,3-38.4,9.2-47.8,18.3c-11.2,10.9-13.6,26.7-16.3,45c-3.1,20.8-6.6,44.4-25.3,62.4c-19.8,19.1-51.6,26.9-100.2,24.6l1.8-39.7		c35.9,1.6,59.7-2.9,70.8-13.6c8.9-8.6,11.1-22.9,13.5-39.6c6.3-42,14.8-99.4,141.4-99.4h41L333,166c-12.6,16-45.4,68.2-31.2,96.2	c9.2,18.3,41.5,25.6,91.2,24.2l1.1,39.8C390.5,326.2,387.1,326.3,383.8,326.3z\" />\n"
					+ "			</g>\n" + "			</svg>\n" + "			<div>\n"
					+ "				<h1 class=\"display-3 mb-3\">Create %s</h1>\n"
					+ "				<form:form action=\"/%s/%s/new/process\" method=\"post\"\n"
					+ "					modelAttribute=\"new%s\">\n"
					+ "					<div class=\"form-group h3\">\n"
					+ "						<label class=\"form-label\">%s:</label>\n"
					+ "						<form:input path=\"%s\" class=\"form-control\" />\n"
					+ "						<form:errors path=\"%s\" class=\"text-danger\" />\n"
					+ "					</div>\n" + "					<div class=\"form-group h3\">\n"
					+ "						<label>%s:</label>\n"
					+ "						<form:input path=\"%s\" class=\"form-control\" />\n"
					+ "						<form:errors path=\"%s\" class=\"text-danger\" />\n"
					+ "					</div>\n" + "					<input type=\"submit\" value=\"Add %s\"\n"
					+ "						class=\"btn btn-outline-light btn-lg mt-3\" />\n"
					+ "				</form:form>\n"
					+ "				<a class=\"btn btn-outline-light mt-5\" href=\"/dashboard\">BACK\n"
					+ "					TO THE DASHBOARD</a>\n" + "\n" + "			</div>\n" + "\n"
					+ "		</div>\n" + "\n" + "		<!--Waves Container-->\n" + "		<div>\n"
					+ "			<svg class=\"waves\" xmlns=\"http://www.w3.org/2000/svg\"\n"
					+ "				xmlns:xlink=\"http://www.w3.org/1999/xlink\" viewBox=\"0 24 150 28\"\n"
					+ "				preserveAspectRatio=\"none\" shape-rendering=\"auto\">\n" + "<defs>\n"
					+ "<path id=\"gentle-wave\"\n"
					+ "					d=\"M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z\" />\n"
					+ "</defs>\n" + "<g class=\"parallax\">\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"0\" fill=\"rgba(255,255,255,0.7\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"3\" fill=\"rgba(255,255,255,0.5)\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"5\" fill=\"rgba(255,255,255,0.3)\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"7\" fill=\"#fff\" />\n" + "</g>\n" + "</svg>\n"
					+ "		</div>\n" + "		<!--Waves end-->\n" + "\n" + "	</div>\n" + "	<!--Header ends-->\n"
					+ "\n" + "	<!-- Footer content -->\n" + "	<div class=\" mt-5\">\n" + "\n"
					+ "		<footer class=\" text-center\">\n" + "			<!-- Grid container -->\n"
					+ "			<div class=\"container p-4 pb-0\">\n" + "				<!-- Section: Form -->\n"
					+ "				<section class=\"\">\n" + "					<form action=\"\">\n"
					+ "						<!--Grid row-->\n"
					+ "						<div class=\"row d-flex justify-content-center\"></div>\n"
					+ "						<!--Grid row-->\n" + "					</form>\n"
					+ "				</section>\n" + "				<!-- Section: Form -->\n" + "			</div>\n"
					+ "			<!-- Grid container -->\n" + "			<div class=\"text-center\">\n"
					+ "				<p>*Current version only takes in 2 String attributes</p>\n"
					+ "			</div>\n" + "			<!-- Copyright -->\n"
					+ "			<div class=\"text-center p-3 text-white\"\n"
					+ "				style=\"background-color: #ADD8E6;\">\n"
					+ "				© Developed by Ashot Gharibyan: <a class=\"text-dark\"\n"
					+ "					href=\"https://www.linkedin.com/in/ashgharibyan/\">Connect with\n"
					+ "					the Developer</a>\n" + "			</div>\n"
					+ "			<!-- Copyright -->\n" + "		</footer>\n" + "\n" + "	</div>\n"
					+ "	<!-- End of .container -->\n" + "\n" + "\n" + "</body>\n" + "</html>";
			String codeToWrite = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n"
					+ "	pageEncoding=\"UTF-8\"%>\n"
					+ "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n"
					+ "<%@ taglib prefix=\"form\" uri=\"http://www.springframework.org/tags/form\"%>\n" + "\n"
					+ "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<link rel=\"stylesheet\"\n"
					+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\"\n"
					+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\n"
					+ "	crossorigin=\"anonymous\">\n"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/header.css\" />\n" + "\n"
					+ String.format(codeTemplate, classNameFirstUpper, // title
							classNameFirstUpper, // h1 line 35
							currentUserName, // action link
							classNameLowercase, classNameFirstUpper, strAttr1FirstUpper, // form attributes
							strAttr1Lowercase, strAttr1Lowercase, intAttr1FirstUpper, intAttr1Lowercase,
							intAttr1Lowercase, classNameFirstUpper // button
					);

			myWriter.write(codeToWrite);
			myWriter.close();
			System.out.println("Successfully wrote CREATE JSP CODE to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.[CREATE JSP]");
			e.printStackTrace();
		}

		// WRITING TO THE EDIT JSP FILE
		try {
			FileWriter myWriter = new FileWriter(modelEditJSP);

			String codeTemplate = "<title>Edit %s</title>\n" + "</head>\n" + "<body>\n" + "\n"
					+ "	<div class=\"header\">\n" + "\n" + "		<!--Content before waves-->\n"
					+ "		<div class=\"inner-header flex\">\n" + "\n"
					+ "			<path fill=\"#FFFFFF\" stroke=\"#000000\" stroke-width=\"10\"\n"
					+ "				stroke-miterlimit=\"10\" d=\"M57,283\" />\n"
					+ "			<g> <path fill=\"#fff\"\n"
					+ "				d=\"M250.4,0.8C112.7,0.8,1,112.4,1,250.2c0,137.7,111.7,249.4,249.4,249.4c137.7,0,249.4-111.7,249.4-249.4\n"
					+ "C499.8,112.4,388.1,0.8,250.4,0.8z M383.8,326.3c-62,0-101.4-14.1-117.6-46.3c-17.1-34.1-2.3-75.4,13.2-104.1\n"
					+ "c-22.4,3-38.4,9.2-47.8,18.3c-11.2,10.9-13.6,26.7-16.3,45c-3.1,20.8-6.6,44.4-25.3,62.4c-19.8,19.1-51.6,26.9-100.2,24.6l1.8-39.7		c35.9,1.6,59.7-2.9,70.8-13.6c8.9-8.6,11.1-22.9,13.5-39.6c6.3-42,14.8-99.4,141.4-99.4h41L333,166c-12.6,16-45.4,68.2-31.2,96.2	c9.2,18.3,41.5,25.6,91.2,24.2l1.1,39.8C390.5,326.2,387.1,326.3,383.8,326.3z\" />\n"
					+ "			</g>\n" + "			</svg>\n" + "			<div>\n"
					+ "				<h1 class=\"display-3 mb-3\">Edit %s</h1>\n"
					+ "				<form:form action=\"/%s/%s/edit/${%s.id}/process\" method=\"post\"\n"
					+ "					modelAttribute=\"%s\">\n"
					+ "					<input type=\"hidden\" name=\"_method\" value=\"put\" />\n"
					+ "					<div class=\"form-group h3\">\n" + "						<label>%s</label>\n"
					+ "						<form:input path=\"%s\" class=\"form-control\" />\n"
					+ "						<form:errors path=\"%s\" class=\"text-danger\" />\n"
					+ "					</div>\n" + "					<div class=\"form-group h3\">\n"
					+ "						<label>%s</label>\n"
					+ "						<form:input path=\"%s\" class=\"form-control\" />\n"
					+ "						<form:errors path=\"%s\" class=\"text-danger\" />\n"
					+ "					</div>\n"
					+ "					<input type=\"submit\" value=\"Submit\" class=\"btn btn-outline-light btn-lg mt-3\"  />\n"
					+ "				</form:form>\n"
					+ "				<a class=\"btn btn-outline-light mt-5\" href=\"/dashboard\">BACK\n"
					+ "					TO THE DASHBOARD</a>\n" + "			</div>\n" + "\n" + "		</div>\n" + "\n"
					+ "		<!--Waves Container-->\n" + "		<div>\n"
					+ "			<svg class=\"waves\" xmlns=\"http://www.w3.org/2000/svg\"\n"
					+ "				xmlns:xlink=\"http://www.w3.org/1999/xlink\" viewBox=\"0 24 150 28\"\n"
					+ "				preserveAspectRatio=\"none\" shape-rendering=\"auto\">\n" + "<defs>\n"
					+ "<path id=\"gentle-wave\"\n"
					+ "					d=\"M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z\" />\n"
					+ "</defs>\n" + "<g class=\"parallax\">\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"0\" fill=\"rgba(255,255,255,0.7\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"3\" fill=\"rgba(255,255,255,0.5)\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"5\" fill=\"rgba(255,255,255,0.3)\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"7\" fill=\"#fff\" />\n" + "</g>\n" + "</svg>\n"
					+ "		</div>\n" + "		<!--Waves end-->\n" + "\n" + "	</div>\n" + "\n" + "\n" + "\n"
					+ "	<!-- Footer content -->\n" + "	<div class=\" mt-5\">\n" + "\n"
					+ "		<footer class=\" text-center\">\n" + "			<!-- Grid container -->\n"
					+ "			<div class=\"container p-4 pb-0\">\n" + "				<!-- Section: Form -->\n"
					+ "				<section class=\"\">\n" + "					<form action=\"\">\n"
					+ "						<!--Grid row-->\n"
					+ "						<div class=\"row d-flex justify-content-center\"></div>\n"
					+ "						<!--Grid row-->\n" + "					</form>\n"
					+ "				</section>\n" + "				<!-- Section: Form -->\n" + "			</div>\n"
					+ "			<!-- Grid container -->\n" + "			<div class=\"text-center\">\n"
					+ "				<p>*Current version only takes in 2 String attributes</p>\n"
					+ "			</div>\n" + "			<!-- Copyright -->\n"
					+ "			<div class=\"text-center p-3 text-white\"\n"
					+ "				style=\"background-color: #ADD8E6;\">\n"
					+ "				© Developed by Ashot Gharibyan: <a class=\"text-dark\"\n"
					+ "					href=\"https://www.linkedin.com/in/ashgharibyan/\">Connect with\n"
					+ "					the Developer</a>\n" + "			</div>\n"
					+ "			<!-- Copyright -->\n" + "		</footer>\n" + "\n" + "	</div>\n"
					+ "	<!-- End of .container -->\n" + "\n" + "</body>\n" + "</html>";
			String codeToWrite = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n"
					+ "	pageEncoding=\"UTF-8\"%>\n"
					+ "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n"
					+ "<%@ taglib prefix=\"form\" uri=\"http://www.springframework.org/tags/form\"%>\n"
					+ "<%@ page isErrorPage=\"true\"%>\n" + "\n" + "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n"
					+ "<link rel=\"stylesheet\"\n"
					+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\"\n"
					+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\n"
					+ "	crossorigin=\"anonymous\">\n"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/header.css\" />\n"
					+ "<meta charset=\"UTF-8\">\n" + String.format(codeTemplate, classNameFirstUpper, // title
							classNameFirstUpper, // h1 line 35
							currentUserName, // action link
							classNameLowercase, classNameLowercase, classNameLowercase, strAttr1FirstUpper, // form
																											// attributes
							strAttr1Lowercase, strAttr1Lowercase, intAttr1FirstUpper, intAttr1Lowercase,
							intAttr1Lowercase);

			myWriter.write(codeToWrite);
			myWriter.close();
			System.out.println("Successfully wrote EDIT JSP CODE to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.[EDIT JSP]");
			e.printStackTrace();
		}

		// WRITING TO THE SHOW ALL JSP FILE
		try {
			FileWriter myWriter = new FileWriter(modelShowAllJSP);

			String codeTemplate = "<title>All %ss</title>\n" + "</head>\n" + "<body>\n" + "\n" + "\n"
					+ "	<div class=\"header\">\n" + "\n" + "		<!--Content before waves-->\n"
					+ "		<div class=\"inner-header flex\">\n" + "\n"
					+ "			<path fill=\"#FFFFFF\" stroke=\"#000000\" stroke-width=\"10\"\n"
					+ "				stroke-miterlimit=\"10\" d=\"M57,283\" />\n"
					+ "			<g> <path fill=\"#fff\"\n"
					+ "				d=\"M250.4,0.8C112.7,0.8,1,112.4,1,250.2c0,137.7,111.7,249.4,249.4,249.4c137.7,0,249.4-111.7,249.4-249.4\n"
					+ "C499.8,112.4,388.1,0.8,250.4,0.8z M383.8,326.3c-62,0-101.4-14.1-117.6-46.3c-17.1-34.1-2.3-75.4,13.2-104.1\n"
					+ "c-22.4,3-38.4,9.2-47.8,18.3c-11.2,10.9-13.6,26.7-16.3,45c-3.1,20.8-6.6,44.4-25.3,62.4c-19.8,19.1-51.6,26.9-100.2,24.6l1.8-39.7		c35.9,1.6,59.7-2.9,70.8-13.6c8.9-8.6,11.1-22.9,13.5-39.6c6.3-42,14.8-99.4,141.4-99.4h41L333,166c-12.6,16-45.4,68.2-31.2,96.2	c9.2,18.3,41.5,25.6,91.2,24.2l1.1,39.8C390.5,326.2,387.1,326.3,383.8,326.3z\" />\n"
					+ "			</g>\n" + "			</svg>\n" + "			<div>\n"
					+ "			<h1 class=\"text-center my-5\">List of all the <span>%s</span></h1>\n"
					+ "			<a class=\"btn btn-lg btn-outline-light\" href=\"/dashboard\">BACK TO THE DASHBOARD</a>\n"
					+ "			</div>\n" + "\n" + "		</div>\n" + "\n" + "		<!--Waves Container-->\n"
					+ "		<div>\n" + "			<svg class=\"waves\" xmlns=\"http://www.w3.org/2000/svg\"\n"
					+ "				xmlns:xlink=\"http://www.w3.org/1999/xlink\" viewBox=\"0 24 150 28\"\n"
					+ "				preserveAspectRatio=\"none\" shape-rendering=\"auto\">\n" + "<defs>\n"
					+ "<path id=\"gentle-wave\"\n"
					+ "					d=\"M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z\" />\n"
					+ "</defs>\n" + "<g class=\"parallax\">\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"0\" fill=\"rgba(255,255,255,0.7\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"3\" fill=\"rgba(255,255,255,0.5)\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"5\" fill=\"rgba(255,255,255,0.3)\" />\n"
					+ "<use xlink:href=\"#gentle-wave\" x=\"48\" y=\"7\" fill=\"#fff\" />\n" + "</g>\n" + "</svg>\n"
					+ "		</div>\n" + "		<!--Waves end-->\n" + "\n" + "	</div>\n" + "\n"
					+ "<div class=\"my-5\">\n" + "	<table class=\"table table-hover text-center\">\n"
					+ "		<thead>\n" + "			<tr class=\"h2\" style=\"color:#03A7C0\">\n"
					+ "				<th>%s</th>\n" + "				<th>%s</th>\n" + "				<th>Delete</th>\n"
					+ "				<th>Edit</th>\n" + "			</tr>\n" + "		</thead>\n" + "		<tbody>\n"
					+ "			<c:forEach items=\"${%ss}\" var=\"%s\">\n" + "				<tr>\n"
					+ "					<td>${%s.%s}</td>\n" + "					<td>${%s.%s}</td>\n"
					+ "					<td>\n"
					+ "						<form action=\"/%s/%s/${%s.id}/delete\" method=\"post\">\n"
					+ "							<input type=\"hidden\" name=\"_method\" value=\"delete\" />\n"
					+ "							 <input class=\"btn btn-outline-primary\"\n"
					+ "								type=\"submit\" value=\"Delete\" />\n"
					+ "						</form>\n" + "					</td>\n" + "					<td>\n"
					+ "						<a class=\"btn btn-outline-primary\" href=\"/%s/%s/${%s.id}/edit\">Edit</a>\n"
					+ "					</td>\n" + "				</tr>\n" + "			</c:forEach>\n"
					+ "		</tbody>\n" + "	</table>\n" + "</div>\n" + "\n" + "<!-- Footer content -->\n"
					+ "	<div class=\" mt-5\">\n" + "\n" + "		<footer class=\" text-center\">\n"
					+ "			<!-- Grid container -->\n" + "			<div class=\"container p-4 pb-0\">\n"
					+ "				<!-- Section: Form -->\n" + "				<section class=\"\">\n"
					+ "					<form action=\"\">\n" + "						<!--Grid row-->\n"
					+ "						<div class=\"row d-flex justify-content-center\">							\n"
					+ "\n" + "							\n" + "						</div>\n"
					+ "						<!--Grid row-->\n" + "					</form>\n"
					+ "				</section>\n" + "				<!-- Section: Form -->\n" + "			</div>\n"
					+ "		\n" + "			<!-- Copyright -->\n"
					+ "			<div class=\"text-center p-3 text-white\"\n"
					+ "				style=\"background-color: #ADD8E6;\">\n"
					+ "				© Developed by Ashot Gharibyan: <a class=\"text-dark\"\n"
					+ "					href=\"https://www.linkedin.com/in/ashgharibyan/\">Connect with\n"
					+ "					the Developer</a>\n" + "			</div>\n"
					+ "			<!-- Copyright -->\n" + "		</footer>\n" + "\n" + "	</div>\n"
					+ "	<!-- End of .container -->\n" + "\n" + "\n" + "</body>\n" + "</html>";
			String codeToWrite = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n"
					+ "	pageEncoding=\"UTF-8\"%>\n"
					+ "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n" + "\n"
					+ "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<link rel=\"stylesheet\"\n"
					+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\"\n"
					+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\n"
					+ "	crossorigin=\"anonymous\">\n"
					+ "	<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/header.css\" />\n" + "	\n"
					+ "<meta charset=\"UTF-8\">\n" + String.format(codeTemplate, classNameFirstUpper, // title
							classNameFirstUpper, // title
							strAttr1FirstUpper, intAttr1FirstUpper, classNameLowercase, classNameLowercase,
							classNameLowercase, strAttr1Lowercase, classNameLowercase, intAttr1Lowercase,
							currentUserName, // delete link
							classNameLowercase, classNameLowercase, currentUserName, // edit link
							classNameLowercase, classNameLowercase);

			myWriter.write(codeToWrite);
			myWriter.close();
			System.out.println("Successfully wrote SHOW ALL JSP to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.[SHOW ALL JSP]");
			e.printStackTrace();
		}

		// WRITE TO USER.java to add the ONE TO MANY
		Path path = Paths.get(
				"/Users/coding/Desktop/CodingDojo/java-stack/JavaSpring/APIofAPIs/src/main/java/com/ashgharibyan/apiofapis/models/Template.java");
		List<String> lines = null;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int position = 50;
		String extraLine = String.format(
				"\n\n\n	@OneToMany(mappedBy=\"template\", fetch=FetchType.LAZY)\n" + "	private List<%s> %ss;",
				classNameFirstUpper, classNameLowercase);

		lines.add(position, extraLine);
		try {
			Files.write(path, lines, StandardCharsets.UTF_8);
			System.out.println("Successfuly added OneToMany to User!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Adding the class name to the user's API list
		ArrayList<String> updateAllAPIs;
		if (currentUser.getAllAPIs() == null) {
			updateAllAPIs = new ArrayList<String>();
		} else {
			updateAllAPIs = currentUser.getAllAPIs();
		}
		updateAllAPIs.add(classNameLowercase);
		currentUser.setAllAPIs(updateAllAPIs);

//		// Adding API Links
		ArrayList<HashMap<String, String>> updateAllAPILinks;
		if (currentUser.getAllAPILinks() == null) {
			updateAllAPILinks = new ArrayList<HashMap<String, String>>();
		} else {
			updateAllAPILinks = currentUser.getAllAPILinks();
		}
		HashMap<String, String> thisModel = new HashMap<String, String>();
		thisModel.put(classNameLowercase,
				"localhost:8080/api/" + currentUser.getUserName() + "/" + classNameLowercase + "s");
		updateAllAPILinks.add(thisModel);
		currentUser.setAllAPILinks(updateAllAPILinks);

		System.out.println("--------");
		System.out.println(updateAllAPIs);
		System.out.println("--------");
		System.out.println(updateAllAPILinks);
//
		userServ.updateUser(currentUser);
		
//		System.out.println(userServ.findById((Long)session.getAttribute("user_id")).getAllAPILinks());
		System.out.println("Successfuly created, written, and redirected!");
		return "redirect:/loading";
	}

	@GetMapping("/loading")
	public String success(HttpSession session) {
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://backend-omega-seven.vercel.app/api/getjoke"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String rspBody = response.body();
		rspBody=rspBody.substring(1, rspBody.length()-1);
		System.out.println(rspBody);
	

		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(rspBody);
			String question = (String) json.get("question");
			String punch = (String) json.get("punchline");
			
			session.setAttribute("question", question);
			session.setAttribute("punchline", punch);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/LoggedUserViews/loading.jsp";
	}
	
	
}
