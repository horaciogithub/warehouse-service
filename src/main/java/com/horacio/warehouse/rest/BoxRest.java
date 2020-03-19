package com.horacio.warehouse.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.horacio.warehouse.dto.BoxDTO;
import com.horacio.warehouse.model.Box;
import com.horacio.warehouse.service.IBoxService;

@RestController
@RequestMapping("/warehouse/boxes")
@CrossOrigin(origins = { "*" },  methods = {  RequestMethod.GET,  RequestMethod.POST, RequestMethod.PUT,  RequestMethod.DELETE })
public class BoxRest {
	
	@Autowired
	private IBoxService boxService;
	
	@GetMapping("/all")
	public List<BoxDTO> listBoxes() {
		List<BoxDTO> boxesDTO = new ArrayList<>();
		List<Box> boxes = boxService.findAll();
		
		for (Box box : boxes) {
			BoxDTO boxDTO = new BoxDTO();
			boxDTO.setRef(box.getRef());
			boxDTO.setUb(box.getUb());
			boxDTO.setDescription(box.getDescription());
			
			boxesDTO.add(boxDTO);
		}
		return boxesDTO;
	}
	
	@GetMapping("/box/{id}")
	public Box findBox(@PathVariable Long id) {
		return boxService.findById(id);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createBox(@RequestBody Box box) throws Exception {
		
		if(boxService.findByReference(box.getRef()) == null) {
			boxService.save(box);
		}
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Box editBox(@PathVariable Long id, @RequestBody Box box) throws Exception {
		Box boxDd = boxService.findById(id);
		
		if(!box.getRef().isEmpty()) {
			if (checkCodes(box.getRef().toUpperCase())) {
				boxDd.setRef(box.getRef().toUpperCase());
			} else {
				throw new Exception("Formato de referencia incorrecto " + box.getRef().charAt(0));
			}
		}
		
		if (!box.getUb().isEmpty()) {
			if (checkCodes(box.getUb().toUpperCase())) {
				boxDd.setUb(box.getUb().toUpperCase());
			} else {
				throw new Exception("Formato de ubicación incorrecto " + box.getUb().charAt(0));
			}
		}
		
		boxDd.setDescription(!box.getDescription().isEmpty() ? box.getDescription() : boxDd.getDescription());
		
		return boxService.save(boxDd);
	}
	
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	 
	private boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	
	private boolean checkCodes(String ref) {
		char letter = ref.charAt(0);
		String digits = ref.substring(1, ref.length());
		
		switch (letter) {
			case 'H': 
				if (ref.length() == 4 && isNumeric(digits)) return true;
			case 'E':
				if (ref.length() == 2 && isNumeric(digits))  return true;
		}
		
		return false;
		
	}

	@DeleteMapping("/box/delete/{id}")
	public void deleteItem(@PathVariable Long id) {
		boxService.deleteById(id);
	}
}
