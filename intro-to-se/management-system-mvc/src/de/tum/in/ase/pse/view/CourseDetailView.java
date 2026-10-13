package de.tum.in.ase.pse.view;

import de.tum.in.ase.pse.controller.Controller;
import de.tum.in.ase.pse.model.Course;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CourseDetailView extends Stage implements Observer {


	private final TextField idTextField;
	private final TextField nameTextField;
	private Controller controller;
	private Course course;

	// TODO: Update the constructor. Make sure that all parameters are set correctly and the view observes the given course
	public CourseDetailView(Controller controller, Course course) {
		this.course = course;
		this.idTextField = new TextField(course.getId());
		this.nameTextField = new TextField(course.getName());
		this.controller = controller;
		course.addObserver(this);
		if (controller == null) {
			save();
		}
	}

	// TODO: Implement save(). This method should update the course with the information entered by the user and save it using the controller
	// HINT: Use the attributes idTextField and nameTextField to retrieve the data
	private void save() {
        course.setId(idTextField.getText());
		course.setName(nameTextField.getText());
		controller.saveCourse(course);
	}



	// TODO: Implement update(). This method should update the view's text fields and title with the current values of the course
	// HINT: JavaFX provides a method setTitle(String)
	@Override
	public void update() {
        idTextField.setText(course.getId());
		nameTextField.setText(course.getName());
		setTitle(course.toString());


	}


}
