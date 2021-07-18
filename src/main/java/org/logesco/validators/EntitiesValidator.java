/**
 * 
 */
package org.logesco.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

/**
 * @author cedrickiadjeu
 *
 */
@Component
public class EntitiesValidator {

	@Autowired
	private Validator validator;
	
	public <T> T validate(T target){
		if(target == null) return target;
		DataBinder binder = new DataBinder(target);
		BindingResult results = binder.getBindingResult();
		validator.validate(target, results);
		//l'objet results contient les erreurs de validation des entités
		return target;
	}

}
