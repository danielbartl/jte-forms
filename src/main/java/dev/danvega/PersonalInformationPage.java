package dev.danvega;

import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonalInformationPage extends WebPage {

	private static final Logger log = LoggerFactory.getLogger(PersonalInformationPage.class);

	@SpringBean
	private UserRepository repository;

	private Form<User> form;

	@Override
	protected void onInitialize() {

		super.onInitialize();

		form();
		firstName();

	}

	private void form() {

		form = new Form<>("form", Model.of(new User())) {

			@Override
			protected void onSubmit() {

				log.info("Saving User: {}", getModelObject());
				assert repository != null;
				//model.addAttribute("message", "User information saved successfully!");

			}

			@Override
			protected void onError() {

				log.info("Invalid User: {}", getModelObject());

			}
		};



		add(form);
	}

	private void firstName() {

		var firstName =
				new TextField<>(
						"firstName",
						new PropertyModel<>(form.getModel(), "firstName"));
		firstName.add(new PropertyValidator<>());
		form.add(firstName);

	}
}
