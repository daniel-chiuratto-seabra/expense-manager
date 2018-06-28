package br.com.danielseabra.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.danielseabra.formbean.FileUploadFormBean;

@Controller
public class IndexController {

	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index", "fileUploadFormBean", new FileUploadFormBean());
	}

}
