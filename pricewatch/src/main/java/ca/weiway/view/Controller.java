package ca.weiway.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.validator.UrlValidator;

import ca.weiway.model.VideoGame;
import ca.weiway.parser.FutureshopVideoGameParser;

@Named
@RequestScoped
public class Controller {
	
	@Inject
	private FutureshopVideoGameParser parser; 
	
	private FacesContext context = FacesContext.getCurrentInstance();
	
	private String url;
	
	private VideoGame result;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public VideoGame getResult() {
		return result;
	}

	public void setResult(VideoGame result) {
		this.result = result;
	}

	public String parse() {
		UrlValidator urlValidator = new UrlValidator();
		if(!urlValidator.isValid(url)) {
			FacesMessage msg = new FacesMessage(
					"URL Conversion error.", 
					"Invalid URL format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			return null;
		}
		
		try {
			result = parser.parse(url);
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage(
					"Error occurred.", 
					ex.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			return null;
		}
		
		return "result";
	}
}
